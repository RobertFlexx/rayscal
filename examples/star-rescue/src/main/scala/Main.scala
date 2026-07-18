import rayscal.*
import scala.collection.mutable.ArrayBuffer
import scala.scalanative.unsafe.Zone

private object GameConfig:
  val Width = 960
  val Height = 540
  val FixedDt = 1.0f / 120.0f
  val MaxStepsPerFrame = 12
  val MaxBullets = 256
  val MaxEnemies = 128
  val MaxPickups = 32
  val MaxSparks = 512

private enum GameMode:
  case Title, Playing, GameOver

private enum EnemyKind:
  case Scout, Heavy

private enum PickupKind:
  case Rapid, Life, Bonus

private enum SparkKind:
  case Player, Shot, Reward

private final case class InputFrame(moveX: Float, moveY: Float, fire: Boolean)

private final class Player:
  var x = GameConfig.Width * 0.5f
  var y = GameConfig.Height - 70.0f
  var cooldown = 0.0f
  var invulnerable = 0.0f
  var rapid = 0.0f

private final class Bullet(var x: Float, var y: Float, val vx: Float, val vy: Float):
  var previousX = x
  var previousY = y
  var alive = true

private final class Enemy(
    var x: Float,
    var y: Float,
    val radius: Float,
    val speed: Float,
    var hp: Int,
    val kind: EnemyKind,
    var phase: Float
):
  var alive = true

private final class Pickup(var x: Float, var y: Float, val kind: PickupKind):
  var alive = true

private final class Spark(
    var x: Float,
    var y: Float,
    var vx: Float,
    var vy: Float,
    var life: Float,
    val maxLife: Float,
    val kind: SparkKind
)

private final class Star(var x: Float, var y: Float, val speed: Float, val size: Float)

private final class Rng(seed: Int):
  private var state = if seed == 0 then 0x6d2b79f5 else seed

  private def next(): Int =
    var value = state
    value ^= value << 13
    value ^= value >>> 17
    value ^= value << 5
    state = if value == 0 then 0x6d2b79f5 else value
    state

  def between(minimum: Int, maximum: Int): Int =
    require(maximum >= minimum)
    minimum + (next() & 0x7fffffff) % (maximum - minimum + 1)

private final class StarRescueGame:
  import GameConfig.*

  val player = new Player
  val bullets = new ArrayBuffer[Bullet](MaxBullets)
  val enemies = new ArrayBuffer[Enemy](MaxEnemies)
  val pickups = new ArrayBuffer[Pickup](MaxPickups)
  val sparks = new ArrayBuffer[Spark](MaxSparks)
  val stars = new ArrayBuffer[Star](120)

  var mode = GameMode.Title
  var paused = false
  var score = 0L
  var highScore = 0L
  var lives = 3
  var wave = 1
  var shake = 0.0f
  var simulationTime = 0.0f

  private var gameplayRng = new Rng(0x51a7cafe)
  private val cosmeticRng = new Rng(0x17b4d00d)
  private var spawnTimer = 0.5f
  private var waveTimer = 0.0f

  for _ <- 0 until 120 do
    stars += new Star(
      cosmeticRng.between(0, Width).toFloat,
      cosmeticRng.between(0, Height).toFloat,
      cosmeticRng.between(25, 140).toFloat,
      cosmeticRng.between(1, 3).toFloat
    )

  def start(): Unit =
    gameplayRng = new Rng(0x51a7cafe)
    bullets.clear()
    enemies.clear()
    pickups.clear()
    sparks.clear()
    player.x = Width * 0.5f
    player.y = Height - 70.0f
    player.cooldown = 0.0f
    player.invulnerable = 1.4f
    player.rapid = 0.0f
    score = 0L
    lives = 3
    wave = 1
    spawnTimer = 0.5f
    waveTimer = 0.0f
    shake = 0.0f
    simulationTime = 0.0f
    paused = false
    mode = GameMode.Playing

  def togglePause(): Unit =
    if mode == GameMode.Playing then paused = !paused

  def advanceStars(dt: Float): Unit =
    val speedScale = if mode == GameMode.Playing && !paused then 1.0f else 0.35f
    for star <- stars do
      star.y += star.speed * speedScale * dt
      if star.y > Height then
        star.y = -4.0f
        star.x = cosmeticRng.between(0, Width).toFloat

  def step(input: InputFrame, dt: Float): Unit =
    if mode != GameMode.Playing || paused then return

    simulationTime += dt
    updateTimers(dt)
    updatePlayer(input, dt)
    if input.fire then shoot()
    spawnEnemies(dt)
    integrateEntities(dt)
    resolveBulletHits()
    resolveEscapes()
    if mode != GameMode.Playing then
      compactEntities()
      return
    resolvePlayerHits()
    if mode != GameMode.Playing then
      compactEntities()
      return
    resolvePickups()
    compactEntities()

  private def updateTimers(dt: Float): Unit =
    player.cooldown -= dt
    player.invulnerable = math.max(0.0f, player.invulnerable - dt).toFloat
    player.rapid = math.max(0.0f, player.rapid - dt).toFloat
    shake = math.max(0.0f, shake - dt).toFloat
    waveTimer += dt
    while waveTimer >= 18.0f do
      waveTimer -= 18.0f
      wave += 1
      addSparks(Width * 0.5f, 88.0f, 20, SparkKind.Reward)

  private def updatePlayer(input: InputFrame, dt: Float): Unit =
    player.x = clamp(player.x + input.moveX * 320.0f * dt, 28.0f, Width - 28.0f)
    player.y = clamp(player.y + input.moveY * 320.0f * dt, 82.0f, Height - 36.0f)

  private def shoot(): Unit =
    if player.cooldown > 0.0f || bullets.size >= MaxBullets then return
    val rapid = player.rapid > 0.0f
    val delay = if rapid then 0.08f else 0.18f
    player.cooldown += delay
    bullets += new Bullet(player.x, player.y - 26.0f, 0.0f, -520.0f)
    if rapid && bullets.size <= MaxBullets - 2 then
      bullets += new Bullet(player.x - 16.0f, player.y - 18.0f, -70.0f, -480.0f)
      bullets += new Bullet(player.x + 16.0f, player.y - 18.0f, 70.0f, -480.0f)

  private def spawnEnemies(dt: Float): Unit =
    spawnTimer -= dt
    val interval = math.max(0.24f, 0.95f - math.min(wave, 14) * 0.055f).toFloat
    var spawned = 0
    while spawnTimer <= 0.0f && spawned < 3 do
      spawnTimer += interval
      spawnEnemy()
      spawned += 1

  private def spawnEnemy(): Unit =
    if enemies.size >= MaxEnemies then return
    val difficulty = math.min(wave, 20)
    val kind = if wave >= 4 && gameplayRng.between(0, 99) < 26 then EnemyKind.Heavy else EnemyKind.Scout
    val radius = if kind == EnemyKind.Heavy then 23.0f else 17.0f
    val hp = if kind == EnemyKind.Heavy then 3 + difficulty / 3 else 1 + difficulty / 5
    enemies += new Enemy(
      gameplayRng.between(radius.toInt + 20, Width - radius.toInt - 20).toFloat,
      -radius - 12.0f,
      radius,
      math.min(270.0f, gameplayRng.between(70, 130).toFloat + difficulty * 8.0f).toFloat,
      hp,
      kind,
      gameplayRng.between(0, 628).toFloat / 100.0f
    )

  private def integrateEntities(dt: Float): Unit =
    for bullet <- bullets do
      bullet.previousX = bullet.x
      bullet.previousY = bullet.y
      bullet.x += bullet.vx * dt
      bullet.y += bullet.vy * dt
      if bullet.y < -24.0f || bullet.x < -24.0f || bullet.x > Width + 24.0f then bullet.alive = false

    for enemy <- enemies do
      enemy.phase = (enemy.phase + 2.4f * dt) % 6.2831855f
      val wiggle = math.sin(enemy.phase).toFloat * (if enemy.kind == EnemyKind.Heavy then 36.0f else 18.0f)
      enemy.x = clamp(enemy.x + wiggle * dt, enemy.radius, Width - enemy.radius)
      enemy.y += enemy.speed * dt

    for pickup <- pickups do
      pickup.y += 120.0f * dt
      if pickup.y > Height + 20.0f then pickup.alive = false

    val damping = math.pow(0.985, dt / (1.0f / 60.0f)).toFloat
    for spark <- sparks do
      spark.x += spark.vx * dt
      spark.y += spark.vy * dt
      spark.vx *= damping
      spark.vy *= damping
      spark.life -= dt

  private def resolveBulletHits(): Unit =
    for bullet <- bullets if bullet.alive do
      var closestEnemy: Enemy | Null = null
      var closestTime = Float.PositiveInfinity
      for enemy <- enemies if enemy.alive do
        val hitTime = segmentCircleTime(
          bullet.previousX,
          bullet.previousY,
          bullet.x,
          bullet.y,
          enemy.x,
          enemy.y,
          enemy.radius + 5.0f
        )
        if hitTime >= 0.0f && hitTime < closestTime then
          closestTime = hitTime
          closestEnemy = enemy

      if closestEnemy != null then
        val enemy = closestEnemy.asInstanceOf[Enemy]
        bullet.alive = false
        enemy.hp -= 1
        addSparks(bullet.x, bullet.y, 4, SparkKind.Shot)
        if enemy.hp <= 0 then destroyEnemy(enemy)

  private def destroyEnemy(enemy: Enemy): Unit =
    enemy.alive = false
    score += (if enemy.kind == EnemyKind.Heavy then 80L else 35L)
    addSparks(enemy.x, enemy.y, if enemy.kind == EnemyKind.Heavy then 28 else 16, if enemy.kind == EnemyKind.Heavy then SparkKind.Reward else SparkKind.Shot)
    if pickups.size < MaxPickups && gameplayRng.between(0, 99) < 13 then
      pickups += new Pickup(enemy.x, enemy.y, PickupKind.fromOrdinal(gameplayRng.between(0, 2)))

  private def resolveEscapes(): Unit =
    var index = 0
    while index < enemies.size && mode == GameMode.Playing do
      val enemy = enemies(index)
      if enemy.alive && enemy.y - enemy.radius > Height then
        enemy.alive = false
        damagePlayer(ignoreInvulnerability = true)
      index += 1

  private def resolvePlayerHits(): Unit =
    if player.invulnerable > 0.0f then return
    var index = 0
    var hit = false
    while index < enemies.size && !hit do
      val enemy = enemies(index)
      val radius = enemy.radius + 17.0f
      if enemy.alive && distanceSquared(player.x, player.y, enemy.x, enemy.y) <= radius * radius then
        enemy.alive = false
        damagePlayer(ignoreInvulnerability = false)
        hit = true
      index += 1

  private def damagePlayer(ignoreInvulnerability: Boolean): Unit =
    if !ignoreInvulnerability && player.invulnerable > 0.0f then return
    lives -= 1
    player.invulnerable = 1.8f
    shake = 0.35f
    addSparks(player.x, player.y, 30, SparkKind.Player)
    if lives <= 0 then
      highScore = math.max(highScore, score)
      paused = false
      mode = GameMode.GameOver

  private def resolvePickups(): Unit =
    for pickup <- pickups if pickup.alive do
      if distanceSquared(player.x, player.y, pickup.x, pickup.y) <= 30.0f * 30.0f then
        pickup.alive = false
        pickup.kind match
          case PickupKind.Rapid => player.rapid = 7.0f
          case PickupKind.Life => lives = math.min(5, lives + 1)
          case PickupKind.Bonus => score += 150L
        addSparks(pickup.x, pickup.y, 18, SparkKind.Reward)

  private def addSparks(x: Float, y: Float, requested: Int, kind: SparkKind): Unit =
    val amount = math.min(requested, MaxSparks - sparks.size)
    for _ <- 0 until amount do
      val angle = cosmeticRng.between(0, 359).toFloat * 0.017453292f
      val speed = cosmeticRng.between(80, 280).toFloat
      val life = cosmeticRng.between(45, 95).toFloat / 100.0f
      sparks += new Spark(x, y, math.cos(angle).toFloat * speed, math.sin(angle).toFloat * speed, life, life, kind)

  private def compactEntities(): Unit =
    bullets.filterInPlace(_.alive)
    enemies.filterInPlace(_.alive)
    pickups.filterInPlace(_.alive)
    sparks.filterInPlace(_.life > 0.0f)

  private def clamp(value: Float, minimum: Float, maximum: Float): Float =
    math.max(minimum, math.min(maximum, value)).toFloat

  private def distanceSquared(ax: Float, ay: Float, bx: Float, by: Float): Float =
    val dx = ax - bx
    val dy = ay - by
    dx * dx + dy * dy

  private def segmentCircleTime(x1: Float, y1: Float, x2: Float, y2: Float, cx: Float, cy: Float, radius: Float): Float =
    val dx = x2 - x1
    val dy = y2 - y1
    val fx = x1 - cx
    val fy = y1 - cy
    val a = dx * dx + dy * dy
    if a <= 0.000001f then
      if fx * fx + fy * fy <= radius * radius then 0.0f else -1.0f
    else
      val b = 2.0f * (fx * dx + fy * dy)
      val c = fx * fx + fy * fy - radius * radius
      val discriminant = b * b - 4.0f * a * c
      if discriminant < 0.0f then -1.0f
      else
        val root = math.sqrt(discriminant.toDouble).toFloat
        val first = (-b - root) / (2.0f * a)
        val second = (-b + root) / (2.0f * a)
        if first >= 0.0f && first <= 1.0f then first
        else if second >= 0.0f && second <= 1.0f then second
        else -1.0f

private object StarRescueRenderer:
  import GameConfig.*

  def draw(game: StarRescueGame)(using Zone): Unit =
    game.mode match
      case GameMode.Title => drawTitle(game)
      case GameMode.Playing => drawGame(game)
      case GameMode.GameOver => drawGameOver(game)

  private def drawBackground(game: StarRescueGame, offsetX: Float, offsetY: Float)(using Zone): Unit =
    Drawing.clear(Colors.rgba(8, 11, 28, 255))
    Shapes.circle((Width - 80 + offsetX).round, (70 + offsetY).round, 180.0f, Colors.rgba(16, 29, 64, 255))
    Shapes.circle((120 + offsetX).round, (Height - 50 + offsetY).round, 220.0f, Colors.rgba(38, 18, 60, 255))
    for star <- game.stars do
      val size = math.max(1, star.size.round)
      Shapes.rectangle((star.x + offsetX).round, (star.y + offsetY).round, size, size, Colors.rgba(210, 230, 255, 255))

  private def drawPlayer(game: StarRescueGame, offsetX: Float, offsetY: Float)(using Zone): Unit =
    val player = game.player
    val nose = Vector.vector2(player.x + offsetX, player.y - 28.0f + offsetY)
    val left = Vector.vector2(player.x - 22.0f + offsetX, player.y + 22.0f + offsetY)
    val right = Vector.vector2(player.x + 22.0f + offsetX, player.y + 22.0f + offsetY)
    Shapes.triangle(nose, left, right, Colors.rgba(80, 220, 255, 255))
    Shapes.triangleLines(nose, left, right, Colors.WHITE)
    Shapes.circle(Vector.vector2(player.x + offsetX, player.y + 10.0f + offsetY), 6.0f, Colors.ORANGE)
    if player.invulnerable > 0.0f then Shapes.circleLines((player.x + offsetX).round, (player.y + offsetY).round, 39.0f, Colors.WHITE)
    if player.rapid > 0.0f then Shapes.circleLines((player.x + offsetX).round, (player.y + offsetY).round, 34.0f, Colors.GOLD)

  private def drawGame(game: StarRescueGame)(using Zone): Unit =
    val offsetX = if game.shake > 0.0f then math.sin(game.simulationTime * 90.0f).toFloat * game.shake * 12.0f else 0.0f
    val offsetY = if game.shake > 0.0f then math.cos(game.simulationTime * 80.0f).toFloat * game.shake * 9.0f else 0.0f
    drawBackground(game, offsetX, offsetY)

    for bullet <- game.bullets do
      Shapes.circle(Vector.vector2(bullet.x + offsetX, bullet.y + offsetY), 4.0f, Colors.SKYBLUE)
      Shapes.line((bullet.x + offsetX).round, (bullet.y + 8.0f + offsetY).round, (bullet.x + offsetX).round, (bullet.y + 20.0f + offsetY).round, Colors.SKYBLUE)

    for enemy <- game.enemies do
      val fill = if enemy.kind == EnemyKind.Heavy then Colors.rgba(210, 65, 230, 255) else Colors.rgba(250, 85, 70, 255)
      val outline = if enemy.kind == EnemyKind.Heavy then Colors.VIOLET else Colors.MAROON
      Shapes.circle(Vector.vector2(enemy.x + offsetX, enemy.y + offsetY), enemy.radius, fill)
      Shapes.circleLines((enemy.x + offsetX).round, (enemy.y + offsetY).round, enemy.radius, outline)
      Shapes.circle(Vector.vector2(enemy.x + offsetX, enemy.y + offsetY), enemy.radius * 0.32f, Colors.rgba(20, 14, 34, 255))

    for pickup <- game.pickups do
      val color = pickup.kind match
        case PickupKind.Rapid => Colors.GOLD
        case PickupKind.Life => Colors.LIME
        case PickupKind.Bonus => Colors.SKYBLUE
      Shapes.ring(Vector.vector2(pickup.x + offsetX, pickup.y + offsetY), 8.0f, 14.0f, 0.0f, 360.0f, 24, color)
      Shapes.circle(Vector.vector2(pickup.x + offsetX, pickup.y + offsetY), 4.0f, Colors.WHITE)

    for spark <- game.sparks do
      val color = spark.kind match
        case SparkKind.Player => Colors.ORANGE
        case SparkKind.Shot => Colors.SKYBLUE
        case SparkKind.Reward => Colors.GOLD
      val radius = math.max(2.0f, 3.0f + 8.0f * (spark.life / spark.maxLife)).toFloat
      Shapes.circle(Vector.vector2(spark.x + offsetX, spark.y + offsetY), radius, color)

    drawPlayer(game, offsetX, offsetY)
    Shapes.rectangle(0, 0, Width, 58, Colors.BLACK)
    Drawing.text(s"SCORE ${game.score}", 22, 18, 24, Colors.WHITE)
    Drawing.text(s"SESSION ${game.highScore}", 190, 18, 24, Colors.LIGHTGRAY)
    Drawing.text(s"WAVE ${game.wave}", 405, 18, 24, Colors.GOLD)
    Drawing.text(s"LIVES ${game.lives}", 545, 18, 24, if game.lives <= 1 then Colors.RED else Colors.LIME)
    if game.player.rapid > 0.0f then Drawing.text("RAPID", 690, 18, 24, Colors.SKYBLUE)
    Drawing.text("WASD/ARROWS move  SPACE/MOUSE shoot  P pause", 22, Height - 30, 18, Colors.LIGHTGRAY)
    Drawing.fps(Width - 90, 18)

    if game.paused then
      Shapes.rectangle(0, 0, Width, Height, Colors.rgba(0, 0, 0, 190))
      Drawing.text("PAUSED", Width / 2 - 68, Height / 2 - 20, 36, Colors.WHITE)
      Drawing.text("Press P to continue", Width / 2 - 102, Height / 2 + 26, 20, Colors.LIGHTGRAY)

  private def drawTitle(game: StarRescueGame)(using Zone): Unit =
    drawBackground(game, 0.0f, 0.0f)
    Drawing.text("STAR RESCUE", 270, 120, 56, Colors.WHITE)
    Drawing.text("A complete fixed-step arcade example", 292, 188, 22, Colors.SKYBLUE)
    Drawing.text("Move, shoot, collect powerups, and survive the waves.", 240, 250, 22, Colors.LIGHTGRAY)
    Drawing.text("ENTER, SPACE, or left click to start", 284, 330, 28, Colors.GOLD)
    Drawing.text("Controls: WASD/Arrows, Space or Mouse, P to pause", 260, 376, 18, Colors.GRAY)
    Drawing.text(s"Session high score: ${game.highScore}", 370, 424, 20, Colors.LIGHTGRAY)

  private def drawGameOver(game: StarRescueGame)(using Zone): Unit =
    drawGame(game)
    Shapes.rectangle(0, 0, Width, Height, Colors.rgba(0, 0, 0, 205))
    Drawing.text("GAME OVER", 315, 150, 52, Colors.RED)
    Drawing.text(s"Score: ${game.score}", 406, 224, 28, Colors.WHITE)
    Drawing.text(s"Session high: ${game.highScore}", 360, 264, 24, Colors.GOLD)
    Drawing.text("ENTER, SPACE, or left click to play again", 250, 340, 24, Colors.SKYBLUE)

private def sampleInput(): InputFrame =
  val horizontal =
    (if Keyboard.isDown(Keys.Right) || Keyboard.isDown(Keys.D) then 1.0f else 0.0f) -
      (if Keyboard.isDown(Keys.Left) || Keyboard.isDown(Keys.A) then 1.0f else 0.0f)
  val vertical =
    (if Keyboard.isDown(Keys.Down) || Keyboard.isDown(Keys.S) then 1.0f else 0.0f) -
      (if Keyboard.isDown(Keys.Up) || Keyboard.isDown(Keys.W) then 1.0f else 0.0f)
  val length = math.sqrt(horizontal * horizontal + vertical * vertical).toFloat
  val scale = if length > 0.0f then 1.0f / length else 0.0f
  InputFrame(horizontal * scale, vertical * scale, Keyboard.isDown(Keys.Space) || Mouse.isDown(MouseButtons.Left))

@main def starRescue(): Unit =
  import GameConfig.*

  Window.withWindow(Width, Height, "rayscal - Star Rescue"):
    Window.setTargetFps(60)
    val game = new StarRescueGame
    var accumulator = 0.0f

    while !Window.shouldClose do
      val startPressed = Keyboard.isPressed(Keys.Enter) || Keyboard.isPressed(Keys.Space) || Mouse.isPressed(MouseButtons.Left)
      val pausePressed = Keyboard.isPressed(Keys.P)
      val input = sampleInput()
      val rawFrameTime = Time.frameTime
      val frameTime = if rawFrameTime.isNaN || rawFrameTime < 0.0f then 0.0f else math.min(rawFrameTime, 0.1f).toFloat

      if (game.mode == GameMode.Title || game.mode == GameMode.GameOver) && startPressed then
        game.start()
        accumulator = 0.0f
      else if game.mode == GameMode.Playing && pausePressed then
        game.togglePause()
        accumulator = 0.0f

      game.advanceStars(frameTime)
      if game.mode == GameMode.Playing && !game.paused then
        accumulator = math.min(accumulator + frameTime, FixedDt * MaxStepsPerFrame).toFloat
        var steps = 0
        while accumulator >= FixedDt && steps < MaxStepsPerFrame && game.mode == GameMode.Playing do
          game.step(input, FixedDt)
          accumulator -= FixedDt
          steps += 1
      else accumulator = 0.0f

      Drawing.frame:
        StarRescueRenderer.draw(game)
