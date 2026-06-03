import rayscal.*

@main def basic3D(): Unit =
  Window.withWindow(900, 520, "rayscal - basic 3D"):
    Window.setTargetFps(60)

    while !Window.shouldClose do
      Drawing.frame:
        val camera = Cameras.camera3D(
          position = Vector.vector3(6.0f, 5.0f, 6.0f),
          target = Vector.vector3(0.0f, 1.0f, 0.0f),
          up = Vector.vector3(0.0f, 1.0f, 0.0f),
          fovy = 45.0f,
          projection = CameraProjection.Perspective
        )

        Drawing.clear(Colors.RAYWHITE)
        Drawing.mode3D(camera):
          Shapes3D.grid(20, 1.0f)
          Shapes3D.cube(Vector.vector3(0.0f, 1.0f, 0.0f), 2.0f, 2.0f, 2.0f, Colors.BLUE)
          Shapes3D.cubeWires(Vector.vector3(0.0f, 1.0f, 0.0f), 2.0f, 2.0f, 2.0f, Colors.DARKBLUE)
          Shapes3D.sphere(Vector.vector3(-3.0f, 1.0f, 0.0f), 1.0f, Colors.ORANGE)
          Shapes3D.cylinder(Vector.vector3(3.0f, 1.0f, 0.0f), 0.7f, 0.7f, 2.0f, 32, Colors.GREEN)

        Drawing.text("Basic 3D: camera + cube + sphere + cylinder", 24, 24, 24, Colors.BLACK)
        Drawing.fps(820, 20)
