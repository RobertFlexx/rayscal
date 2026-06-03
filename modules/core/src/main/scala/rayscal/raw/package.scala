package rayscal

package object raw:
  type Color = scala.scalanative.unsafe.CStruct4[
    scala.scalanative.unsafe.CUnsignedChar,
    scala.scalanative.unsafe.CUnsignedChar,
    scala.scalanative.unsafe.CUnsignedChar,
    scala.scalanative.unsafe.CUnsignedChar
  ]

  type Vector2 = scala.scalanative.unsafe.CStruct2[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type Vector3 = scala.scalanative.unsafe.CStruct3[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type Vector4 = scala.scalanative.unsafe.CStruct4[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type Quaternion = Vector4

  type Matrix = scala.scalanative.unsafe.CStruct16[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type Rectangle = scala.scalanative.unsafe.CStruct4[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type Image = scala.scalanative.unsafe.CStruct5[
    scala.scalanative.unsafe.Ptr[Byte],
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt
  ]

  type Texture = scala.scalanative.unsafe.CStruct5[
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt
  ]

  type Texture2D = Texture
  type TextureCubemap = Texture

  // Flatten nested raylib structs that are commonly passed or returned by value.
  // This avoids fragile nested-struct ABI lowering across Scala Native, LLVM, and C.
  type RenderTexture = scala.scalanative.unsafe.CStruct11[
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.CInt
  ]

  type RenderTexture2D = RenderTexture

  type Shader = scala.scalanative.unsafe.CStruct2[
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.Ptr[scala.scalanative.unsafe.CInt]
  ]

  type Camera3D = scala.scalanative.unsafe.CStruct11[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CInt
  ]

  type Camera = Camera3D

  type Camera2D = scala.scalanative.unsafe.CStruct6[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type Ray = scala.scalanative.unsafe.CStruct6[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type BoundingBox = scala.scalanative.unsafe.CStruct6[
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type RayCollision = scala.scalanative.unsafe.CStruct8[
    scala.scalanative.unsafe.CBool,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat,
    scala.scalanative.unsafe.CFloat
  ]

  type Wave = scala.scalanative.unsafe.CStruct5[
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.Ptr[Byte]
  ]

  type AudioStream = scala.scalanative.unsafe.CStruct5[
    scala.scalanative.unsafe.Ptr[Byte],
    scala.scalanative.unsafe.Ptr[Byte],
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CUnsignedInt
  ]

  type Sound = scala.scalanative.unsafe.CStruct2[
    AudioStream,
    scala.scalanative.unsafe.CUnsignedInt
  ]

  type Music = scala.scalanative.unsafe.CStruct5[
    AudioStream,
    scala.scalanative.unsafe.CUnsignedInt,
    scala.scalanative.unsafe.CBool,
    scala.scalanative.unsafe.CInt,
    scala.scalanative.unsafe.Ptr[Byte]
  ]
