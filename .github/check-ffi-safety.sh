#!/usr/bin/env bash
# Fails CI if friendly public API appears to return raw CStruct/Ptr-backed values
# or still aliases public types to rayscal.raw CStructs.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
FRIENDLY="$ROOT/modules/core/src/main/scala/rayscal"
FAILED=0

fail() {
  echo "FAIL: $*" >&2
  FAILED=1
}

# Public package must not alias value types to raw CStructs.
if rg -n 'type (Color|Vector2|Vector3|Vector4|Quaternion|Matrix|Rectangle|Camera2D|Camera3D|BoundingBox|Ray|RayCollision)\s*=' \
  "$FRIENDLY/package.scala" >/tmp/rayscal-alias-check.txt 2>/dev/null; then
  fail "public package.scala still aliases value types to other types:"
  cat /tmp/rayscal-alias-check.txt >&2
fi

# Friendly wrappers must not return deref of stackalloc/alloc.
while IFS= read -r file; do
  if rg -n 'stackalloc\[|!\s*out\b|return\s+!|:\s*Ptr\[' "$file" >/tmp/rayscal-ptr-check.txt 2>/dev/null; then
    # Allow Ptr usage inside private helpers / TextureView.native / resource ptr fields
    if [[ "$(basename "$file")" == "TextureView.scala" ]]; then
      continue
    fi
    if [[ "$(basename "$file")" == "ManagedResources.scala" ]]; then
      continue
    fi
    if [[ "$(basename "$file")" == "NativeMarshal.scala" ]]; then
      continue
    fi
    if [[ "$(basename "$file")" == "RaylibAbi.scala" ]]; then
      continue
    fi
    # Flag methods that return !out or stackalloc of public value types
    if rg -n 'stackalloc\[(Color|Vector2|Vector3|Vector4|Rectangle|Camera2D|Camera3D|BoundingBox|Ray|RayCollision|Matrix)\]|!\s*out\s*$' "$file" >/tmp/hit.txt 2>/dev/null; then
      fail "temporary native value may escape in $file:"
      cat /tmp/hit.txt >&2
    fi
  fi
done < <(find "$FRIENDLY" -maxdepth 1 -name '*.scala' -type f)

# Raw Raylib must not declare struct-by-value externs for known unsafe types as params/returns
# (heuristic: non-Ptr Color/Vector2/... in Raylib.scala signatures)
if rg -n 'def .*\(([^)]*\b(Color|Vector2|Vector3|Vector4|Rectangle|Matrix|Camera2D|Camera3D|BoundingBox|Ray|RayCollision|Image|Texture2D|Shader|Wave|Sound|Music|RenderTexture2D)\b[^)]*)\)|: (Color|Vector2|Vector3|Vector4|Rectangle|Matrix|Camera2D|Camera3D|BoundingBox|Ray|RayCollision|Image|Texture2D|Shader|Wave|Sound|Music|RenderTexture2D)\s*=' \
  "$FRIENDLY/raw/Raylib.scala" 2>/dev/null | rg -v 'Ptr\[' >/tmp/rayscal-byvalue.txt; then
  if [[ -s /tmp/rayscal-byvalue.txt ]]; then
    fail "Raylib.scala still appears to declare struct-by-value FFI:"
    cat /tmp/rayscal-byvalue.txt >&2
  fi
fi

if [[ "$FAILED" -ne 0 ]]; then
  echo "ffi public-api safety check failed" >&2
  exit 1
fi

echo "ffi public-api safety check passed"
