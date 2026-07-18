package rayscal.raw

import scala.scalanative.unsafe.*

@extern
object RaymathNative:
  def rayscal_Clamp(value: CFloat, minimum: CFloat, maximum: CFloat): CFloat = extern
  def rayscal_Lerp(start: CFloat, end: CFloat, amount: CFloat): CFloat = extern
  def rayscal_Remap(value: CFloat, inStart: CFloat, inEnd: CFloat, outStart: CFloat, outEnd: CFloat): CFloat = extern
  def rayscal_Wrap(value: CFloat, minimum: CFloat, maximum: CFloat): CFloat = extern

  def rayscal_Vector2Add(out: Ptr[Vector2], a: Ptr[Vector2], b: Ptr[Vector2]): Unit = extern
  def rayscal_Vector2Subtract(out: Ptr[Vector2], a: Ptr[Vector2], b: Ptr[Vector2]): Unit = extern
  def rayscal_Vector2Multiply(out: Ptr[Vector2], a: Ptr[Vector2], b: Ptr[Vector2]): Unit = extern
  def rayscal_Vector2Divide(out: Ptr[Vector2], a: Ptr[Vector2], b: Ptr[Vector2]): Unit = extern
  def rayscal_Vector2Scale(out: Ptr[Vector2], value: Ptr[Vector2], scale: CFloat): Unit = extern
  def rayscal_Vector2Normalize(out: Ptr[Vector2], value: Ptr[Vector2]): Unit = extern
  def rayscal_Vector2Lerp(out: Ptr[Vector2], a: Ptr[Vector2], b: Ptr[Vector2], amount: CFloat): Unit = extern
  def rayscal_Vector2Rotate(out: Ptr[Vector2], value: Ptr[Vector2], angle: CFloat): Unit = extern
  def rayscal_Vector2Reflect(out: Ptr[Vector2], value: Ptr[Vector2], normal: Ptr[Vector2]): Unit = extern
  def rayscal_Vector2Transform(out: Ptr[Vector2], value: Ptr[Vector2], matrix: Ptr[Matrix]): Unit = extern
  def rayscal_Vector2Length(value: Ptr[Vector2]): CFloat = extern
  def rayscal_Vector2LengthSqr(value: Ptr[Vector2]): CFloat = extern
  def rayscal_Vector2DotProduct(a: Ptr[Vector2], b: Ptr[Vector2]): CFloat = extern
  def rayscal_Vector2Distance(a: Ptr[Vector2], b: Ptr[Vector2]): CFloat = extern

  def rayscal_Vector3Add(out: Ptr[Vector3], a: Ptr[Vector3], b: Ptr[Vector3]): Unit = extern
  def rayscal_Vector3Subtract(out: Ptr[Vector3], a: Ptr[Vector3], b: Ptr[Vector3]): Unit = extern
  def rayscal_Vector3Multiply(out: Ptr[Vector3], a: Ptr[Vector3], b: Ptr[Vector3]): Unit = extern
  def rayscal_Vector3Scale(out: Ptr[Vector3], value: Ptr[Vector3], scale: CFloat): Unit = extern
  def rayscal_Vector3CrossProduct(out: Ptr[Vector3], a: Ptr[Vector3], b: Ptr[Vector3]): Unit = extern
  def rayscal_Vector3Normalize(out: Ptr[Vector3], value: Ptr[Vector3]): Unit = extern
  def rayscal_Vector3Lerp(out: Ptr[Vector3], a: Ptr[Vector3], b: Ptr[Vector3], amount: CFloat): Unit = extern
  def rayscal_Vector3Reflect(out: Ptr[Vector3], value: Ptr[Vector3], normal: Ptr[Vector3]): Unit = extern
  def rayscal_Vector3Transform(out: Ptr[Vector3], value: Ptr[Vector3], matrix: Ptr[Matrix]): Unit = extern
  def rayscal_Vector3RotateByQuaternion(out: Ptr[Vector3], value: Ptr[Vector3], quaternion: Ptr[Quaternion]): Unit = extern
  def rayscal_Vector3Length(value: Ptr[Vector3]): CFloat = extern
  def rayscal_Vector3LengthSqr(value: Ptr[Vector3]): CFloat = extern
  def rayscal_Vector3DotProduct(a: Ptr[Vector3], b: Ptr[Vector3]): CFloat = extern
  def rayscal_Vector3Distance(a: Ptr[Vector3], b: Ptr[Vector3]): CFloat = extern

  def rayscal_Vector4Add(out: Ptr[Vector4], a: Ptr[Vector4], b: Ptr[Vector4]): Unit = extern
  def rayscal_Vector4Subtract(out: Ptr[Vector4], a: Ptr[Vector4], b: Ptr[Vector4]): Unit = extern
  def rayscal_Vector4Scale(out: Ptr[Vector4], value: Ptr[Vector4], scale: CFloat): Unit = extern
  def rayscal_Vector4Normalize(out: Ptr[Vector4], value: Ptr[Vector4]): Unit = extern
  def rayscal_Vector4Lerp(out: Ptr[Vector4], a: Ptr[Vector4], b: Ptr[Vector4], amount: CFloat): Unit = extern
  def rayscal_Vector4Length(value: Ptr[Vector4]): CFloat = extern
  def rayscal_Vector4DotProduct(a: Ptr[Vector4], b: Ptr[Vector4]): CFloat = extern

  def rayscal_MatrixIdentity(out: Ptr[Matrix]): Unit = extern
  def rayscal_MatrixAdd(out: Ptr[Matrix], a: Ptr[Matrix], b: Ptr[Matrix]): Unit = extern
  def rayscal_MatrixSubtract(out: Ptr[Matrix], a: Ptr[Matrix], b: Ptr[Matrix]): Unit = extern
  def rayscal_MatrixMultiply(out: Ptr[Matrix], a: Ptr[Matrix], b: Ptr[Matrix]): Unit = extern
  def rayscal_MatrixTranspose(out: Ptr[Matrix], value: Ptr[Matrix]): Unit = extern
  def rayscal_MatrixInvert(out: Ptr[Matrix], value: Ptr[Matrix]): Unit = extern
  def rayscal_MatrixTranslate(out: Ptr[Matrix], x: CFloat, y: CFloat, z: CFloat): Unit = extern
  def rayscal_MatrixScale(out: Ptr[Matrix], x: CFloat, y: CFloat, z: CFloat): Unit = extern
  def rayscal_MatrixRotateX(out: Ptr[Matrix], angle: CFloat): Unit = extern
  def rayscal_MatrixRotateY(out: Ptr[Matrix], angle: CFloat): Unit = extern
  def rayscal_MatrixRotateZ(out: Ptr[Matrix], angle: CFloat): Unit = extern
  def rayscal_MatrixRotateXYZ(out: Ptr[Matrix], angle: Ptr[Vector3]): Unit = extern
  def rayscal_MatrixLookAt(out: Ptr[Matrix], eye: Ptr[Vector3], target: Ptr[Vector3], up: Ptr[Vector3]): Unit = extern
  def rayscal_MatrixPerspective(out: Ptr[Matrix], fovY: CDouble, aspect: CDouble, nearPlane: CDouble, farPlane: CDouble): Unit = extern
  def rayscal_MatrixOrtho(out: Ptr[Matrix], left: CDouble, right: CDouble, bottom: CDouble, top: CDouble, nearPlane: CDouble, farPlane: CDouble): Unit = extern
  def rayscal_MatrixDeterminant(value: Ptr[Matrix]): CFloat = extern
  def rayscal_MatrixTrace(value: Ptr[Matrix]): CFloat = extern

  def rayscal_QuaternionIdentity(out: Ptr[Quaternion]): Unit = extern
  def rayscal_QuaternionAdd(out: Ptr[Quaternion], a: Ptr[Quaternion], b: Ptr[Quaternion]): Unit = extern
  def rayscal_QuaternionSubtract(out: Ptr[Quaternion], a: Ptr[Quaternion], b: Ptr[Quaternion]): Unit = extern
  def rayscal_QuaternionMultiply(out: Ptr[Quaternion], a: Ptr[Quaternion], b: Ptr[Quaternion]): Unit = extern
  def rayscal_QuaternionNormalize(out: Ptr[Quaternion], value: Ptr[Quaternion]): Unit = extern
  def rayscal_QuaternionInvert(out: Ptr[Quaternion], value: Ptr[Quaternion]): Unit = extern
  def rayscal_QuaternionSlerp(out: Ptr[Quaternion], a: Ptr[Quaternion], b: Ptr[Quaternion], amount: CFloat): Unit = extern
  def rayscal_QuaternionFromEuler(out: Ptr[Quaternion], pitch: CFloat, yaw: CFloat, roll: CFloat): Unit = extern
  def rayscal_QuaternionToEuler(out: Ptr[Vector3], value: Ptr[Quaternion]): Unit = extern
  def rayscal_QuaternionFromAxisAngle(out: Ptr[Quaternion], axis: Ptr[Vector3], angle: CFloat): Unit = extern
  def rayscal_QuaternionToMatrix(out: Ptr[Matrix], value: Ptr[Quaternion]): Unit = extern
