import rayscal.*

@main def basic3D(): Unit =
  Window.withWindow(900, 520, "rayscal - basic 3D"):
    Window.setTargetFps(60)

    while !Window.shouldClose do
      Drawing.frame:
        val orbit = (Time.elapsed * 0.35).toFloat
        val camera = Cameras.camera3D(
          position = Vector.vector3(math.cos(orbit).toFloat * 7.0f, 5.0f, math.sin(orbit).toFloat * 7.0f),
          target = Vector.vector3(0.0f, 1.0f, 0.0f),
          up = Vector.vector3(0.0f, 1.0f, 0.0f),
          fovy = 45.0f,
          projection = CameraProjection.Perspective
        )

        Drawing.clear(Colors.RAYWHITE)
        Drawing.mode3D(camera):
          Shapes3D.grid(20, 1.0f)
          Shapes3D.line(Vector.vector3(0.0f, 0.02f, 0.0f), Vector.vector3(2.0f, 0.02f, 0.0f), Colors.RED)
          Shapes3D.line(Vector.vector3(0.0f, 0.02f, 0.0f), Vector.vector3(0.0f, 2.0f, 0.0f), Colors.GREEN)
          Shapes3D.line(Vector.vector3(0.0f, 0.02f, 0.0f), Vector.vector3(0.0f, 0.02f, 2.0f), Colors.BLUE)
          Shapes3D.cube(Vector.vector3(0.0f, 1.0f, 0.0f), 2.0f, 2.0f, 2.0f, Colors.BLUE)
          Shapes3D.cubeWires(Vector.vector3(0.0f, 1.0f, 0.0f), 2.0f, 2.0f, 2.0f, Colors.DARKBLUE)
          Shapes3D.sphere(Vector.vector3(-3.0f, 1.0f, 0.0f), 1.0f, Colors.ORANGE)
          Shapes3D.cylinder(Vector.vector3(3.0f, 0.0f, 0.0f), 0.7f, 0.7f, 2.0f, 32, Colors.GREEN)

        Drawing.text("Basic 3D: orbiting perspective camera", 24, 24, 24, Colors.BLACK)
        Drawing.text("Red X | Green Y | Blue Z", 24, 56, 18, Colors.DARKGRAY)
        Drawing.fps(Window.screenWidth - 80, 20)
