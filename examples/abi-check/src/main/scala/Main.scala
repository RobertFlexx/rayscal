import rayscal.RaylibAbi

@main def abiCheck(): Unit =
  RaylibAbi.validate()
  println(s"raylib ${RaylibAbi.raylibVersion} ABI size and field-layout checks passed")
