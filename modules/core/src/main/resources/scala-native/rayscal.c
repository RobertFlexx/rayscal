#include "raylib.h"

void rayscal_LoadRenderTexture(RenderTexture2D *out, int width, int height) {
    *out = LoadRenderTexture(width, height);
}

bool rayscal_IsRenderTextureValid(const RenderTexture2D *target) {
    return IsRenderTextureValid(*target);
}

void rayscal_UnloadRenderTexture(const RenderTexture2D *target) {
    UnloadRenderTexture(*target);
}

void rayscal_BeginMode2D(const Camera2D *camera) {
    BeginMode2D(*camera);
}

void rayscal_BeginMode3D(const Camera3D *camera) {
    BeginMode3D(*camera);
}

void rayscal_BeginTextureMode(const RenderTexture2D *target) {
    BeginTextureMode(*target);
}

void rayscal_GetScreenToWorld2D(Vector2 *out, const Vector2 *position, const Camera2D *camera) {
    *out = GetScreenToWorld2D(*position, *camera);
}

void rayscal_GetWorldToScreen2D(Vector2 *out, const Vector2 *position, const Camera2D *camera) {
    *out = GetWorldToScreen2D(*position, *camera);
}

void rayscal_ClearBackground(const Color *color) {
    ClearBackground(*color);
}

void rayscal_DrawText(const char *text, int x, int y, int size, const Color *color) {
    DrawText(text, x, y, size, *color);
}

void rayscal_DrawLine(int x1, int y1, int x2, int y2, const Color *color) {
    DrawLine(x1, y1, x2, y2, *color);
}

void rayscal_DrawLineV(const Vector2 *start, const Vector2 *end, const Color *color) {
    DrawLineV(*start, *end, *color);
}

void rayscal_DrawLineEx(const Vector2 *start, const Vector2 *end, float thick, const Color *color) {
    DrawLineEx(*start, *end, thick, *color);
}

void rayscal_DrawCircle(int x, int y, float radius, const Color *color) {
    DrawCircle(x, y, radius, *color);
}

void rayscal_DrawCircleV(const Vector2 *center, float radius, const Color *color) {
    DrawCircleV(*center, radius, *color);
}

void rayscal_DrawCircleLines(int x, int y, float radius, const Color *color) {
    DrawCircleLines(x, y, radius, *color);
}

void rayscal_DrawEllipse(int x, int y, float radiusH, float radiusV, const Color *color) {
    DrawEllipse(x, y, radiusH, radiusV, *color);
}

void rayscal_DrawEllipseLines(int x, int y, float radiusH, float radiusV, const Color *color) {
    DrawEllipseLines(x, y, radiusH, radiusV, *color);
}

void rayscal_DrawRing(const Vector2 *center, float innerRadius, float outerRadius, float startAngle, float endAngle, int segments, const Color *color) {
    DrawRing(*center, innerRadius, outerRadius, startAngle, endAngle, segments, *color);
}

void rayscal_DrawRectangle(int x, int y, int width, int height, const Color *color) {
    DrawRectangle(x, y, width, height, *color);
}

void rayscal_DrawRectangleV(const Vector2 *position, const Vector2 *size, const Color *color) {
    DrawRectangleV(*position, *size, *color);
}

void rayscal_DrawRectangleRec(const Rectangle *rec, const Color *color) {
    DrawRectangleRec(*rec, *color);
}

void rayscal_DrawRectangleLines(int x, int y, int width, int height, const Color *color) {
    DrawRectangleLines(x, y, width, height, *color);
}

void rayscal_DrawRectangleLinesEx(const Rectangle *rec, float thick, const Color *color) {
    DrawRectangleLinesEx(*rec, thick, *color);
}

void rayscal_DrawTriangle(const Vector2 *v1, const Vector2 *v2, const Vector2 *v3, const Color *color) {
    DrawTriangle(*v1, *v2, *v3, *color);
}

void rayscal_DrawTriangleLines(const Vector2 *v1, const Vector2 *v2, const Vector2 *v3, const Color *color) {
    DrawTriangleLines(*v1, *v2, *v3, *color);
}

void rayscal_DrawPoly(const Vector2 *center, int sides, float radius, float rotation, const Color *color) {
    DrawPoly(*center, sides, radius, rotation, *color);
}

void rayscal_DrawPolyLines(const Vector2 *center, int sides, float radius, float rotation, const Color *color) {
    DrawPolyLines(*center, sides, radius, rotation, *color);
}

void rayscal_DrawPolyLinesEx(const Vector2 *center, int sides, float radius, float rotation, float thick, const Color *color) {
    DrawPolyLinesEx(*center, sides, radius, rotation, thick, *color);
}

void rayscal_DrawTexturePro(const Texture2D *texture, const Rectangle *source, const Rectangle *dest, const Vector2 *origin, float rotation, const Color *tint) {
    DrawTexturePro(*texture, *source, *dest, *origin, rotation, *tint);
}
