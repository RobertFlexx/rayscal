#include "raylib.h"
#include "raymath.h"
#include <stdlib.h>

int rayscal_raylib_version_major(void) { return RAYLIB_VERSION_MAJOR; }
int rayscal_raylib_version_minor(void) { return RAYLIB_VERSION_MINOR; }
int rayscal_raylib_version_patch(void) { return RAYLIB_VERSION_PATCH; }

int rayscal_sizeof_Color(void) { return (int)sizeof(Color); }
int rayscal_sizeof_Vector2(void) { return (int)sizeof(Vector2); }
int rayscal_sizeof_Vector3(void) { return (int)sizeof(Vector3); }
int rayscal_sizeof_Vector4(void) { return (int)sizeof(Vector4); }
int rayscal_sizeof_Matrix(void) { return (int)sizeof(Matrix); }
int rayscal_sizeof_Rectangle(void) { return (int)sizeof(Rectangle); }
int rayscal_sizeof_Image(void) { return (int)sizeof(Image); }
int rayscal_sizeof_Texture2D(void) { return (int)sizeof(Texture2D); }
int rayscal_sizeof_RenderTexture2D(void) { return (int)sizeof(RenderTexture2D); }
int rayscal_sizeof_Shader(void) { return (int)sizeof(Shader); }
int rayscal_sizeof_Camera2D(void) { return (int)sizeof(Camera2D); }
int rayscal_sizeof_Camera3D(void) { return (int)sizeof(Camera3D); }
int rayscal_sizeof_Ray(void) { return (int)sizeof(Ray); }
int rayscal_sizeof_RayCollision(void) { return (int)sizeof(RayCollision); }
int rayscal_sizeof_BoundingBox(void) { return (int)sizeof(BoundingBox); }
int rayscal_sizeof_Wave(void) { return (int)sizeof(Wave); }
int rayscal_sizeof_AudioStream(void) { return (int)sizeof(AudioStream); }
int rayscal_sizeof_Sound(void) { return (int)sizeof(Sound); }
int rayscal_sizeof_Music(void) { return (int)sizeof(Music); }
int rayscal_sizeof_FilePathList(void) { return (int)sizeof(FilePathList); }

bool rayscal_validate_struct_layout(const Color *color, const Camera2D *camera, const Texture2D *texture, const Wave *wave, const Sound *sound) {
    return color->r == 11 && color->g == 22 && color->b == 33 && color->a == 44 &&
           camera->offset.x == 1.25f && camera->offset.y == -2.5f &&
           camera->target.x == 3.75f && camera->target.y == -4.5f &&
           camera->rotation == 12.5f && camera->zoom == 2.0f &&
           texture->id == 123u && texture->width == 456 && texture->height == 789 &&
           texture->mipmaps == 3 && texture->format == 7 &&
           wave->frameCount == 101u && wave->sampleRate == 44100u &&
           wave->sampleSize == 16u && wave->channels == 2u && wave->data == NULL &&
           sound->stream.buffer == NULL && sound->stream.processor == NULL &&
           sound->stream.sampleRate == 48000u && sound->stream.sampleSize == 32u &&
           sound->stream.channels == 2u && sound->frameCount == 777u;
}

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

void rayscal_LoadShader(Shader *out, const char *vsFileName, const char *fsFileName) {
    *out = LoadShader(vsFileName, fsFileName);
}

void rayscal_LoadShaderFromMemory(Shader *out, const char *vsCode, const char *fsCode) {
    *out = LoadShaderFromMemory(vsCode, fsCode);
}

bool rayscal_IsShaderValid(const Shader *shader) {
    return IsShaderValid(*shader);
}

void rayscal_BeginShaderMode(const Shader *shader) {
    BeginShaderMode(*shader);
}

int rayscal_GetShaderLocation(const Shader *shader, const char *uniformName) {
    return GetShaderLocation(*shader, uniformName);
}

int rayscal_GetShaderLocationAttrib(const Shader *shader, const char *attribName) {
    return GetShaderLocationAttrib(*shader, attribName);
}

void rayscal_SetShaderValueTexture(const Shader *shader, int locIndex, const Texture2D *texture) {
    SetShaderValueTexture(*shader, locIndex, *texture);
}

void rayscal_SetShaderValue(const Shader *shader, int locIndex, const void *value, int uniformType) {
    SetShaderValue(*shader, locIndex, value, uniformType);
}

void rayscal_SetShaderValueMatrix(const Shader *shader, int locIndex, const Matrix *matrix) {
    SetShaderValueMatrix(*shader, locIndex, *matrix);
}

void rayscal_UnloadShader(const Shader *shader) {
    UnloadShader(*shader);
}

void rayscal_GetScreenToWorld2D(Vector2 *out, const Vector2 *position, const Camera2D *camera) {
    *out = GetScreenToWorld2D(*position, *camera);
}

void rayscal_GetWorldToScreen2D(Vector2 *out, const Vector2 *position, const Camera2D *camera) {
    *out = GetWorldToScreen2D(*position, *camera);
}

void rayscal_GetScreenToWorldRay(Ray *out, const Vector2 *position, const Camera3D *camera) {
    *out = GetScreenToWorldRay(*position, *camera);
}

void rayscal_GetScreenToWorldRayEx(Ray *out, const Vector2 *position, const Camera3D *camera, int width, int height) {
    *out = GetScreenToWorldRayEx(*position, *camera, width, height);
}

void rayscal_GetWorldToScreen(Vector2 *out, const Vector3 *position, const Camera3D *camera) {
    *out = GetWorldToScreen(*position, *camera);
}

void rayscal_GetWorldToScreenEx(Vector2 *out, const Vector3 *position, const Camera3D *camera, int width, int height) {
    *out = GetWorldToScreenEx(*position, *camera, width, height);
}

void rayscal_UpdateCameraPro(Camera3D *camera, const Vector3 *movement, const Vector3 *rotation, float zoom) {
    UpdateCameraPro(camera, *movement, *rotation, zoom);
}

void rayscal_Fade(Color *out, const Color *color, float alpha) {
    *out = Fade(*color, alpha);
}

void rayscal_ColorTint(Color *out, const Color *color, const Color *tint) {
    *out = ColorTint(*color, *tint);
}

void rayscal_ColorBrightness(Color *out, const Color *color, float factor) {
    *out = ColorBrightness(*color, factor);
}

void rayscal_ColorFromHSV(Color *out, float hue, float saturation, float value) {
    *out = ColorFromHSV(hue, saturation, value);
}

void rayscal_ColorToHSV(Vector3 *out, const Color *color) {
    *out = ColorToHSV(*color);
}

int rayscal_ColorToInt(const Color *color) {
    return ColorToInt(*color);
}

bool rayscal_ColorIsEqual(const Color *left, const Color *right) {
    return ColorIsEqual(*left, *right);
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

void rayscal_DrawPixel(int x, int y, const Color *color) {
    DrawPixel(x, y, *color);
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

void rayscal_LoadImage(Image *out, const char *fileName) {
    *out = LoadImage(fileName);
}

void rayscal_ImageCopy(Image *out, const Image *image) {
    *out = ImageCopy(*image);
}

void rayscal_ImageColorTint(Image *image, const Color *color) {
    ImageColorTint(image, *color);
}

void rayscal_ImageCrop(Image *image, const Rectangle *crop) {
    ImageCrop(image, *crop);
}

bool rayscal_IsImageValid(const Image *image) {
    return IsImageValid(*image);
}

void rayscal_UnloadImage(const Image *image) {
    UnloadImage(*image);
}

void rayscal_GenImageColor(Image *out, int width, int height, const Color *color) {
    *out = GenImageColor(width, height, *color);
}

void rayscal_GenImageChecked(Image *out, int width, int height, int checksX, int checksY, const Color *col1, const Color *col2) {
    *out = GenImageChecked(width, height, checksX, checksY, *col1, *col2);
}

void rayscal_GenImageGradientLinear(Image *out, int width, int height, int direction, const Color *start, const Color *end) {
    *out = GenImageGradientLinear(width, height, direction, *start, *end);
}

void rayscal_GenImageGradientRadial(Image *out, int width, int height, float density, const Color *inner, const Color *outer) {
    *out = GenImageGradientRadial(width, height, density, *inner, *outer);
}

void rayscal_GenImagePerlinNoise(Image *out, int width, int height, int offsetX, int offsetY, float scale) {
    *out = GenImagePerlinNoise(width, height, offsetX, offsetY, scale);
}

void rayscal_GenImageCellular(Image *out, int width, int height, int tileSize) {
    *out = GenImageCellular(width, height, tileSize);
}

void rayscal_LoadTexture(Texture2D *out, const char *fileName) {
    *out = LoadTexture(fileName);
}

void rayscal_LoadTextureFromImage(Texture2D *out, const Image *image) {
    *out = LoadTextureFromImage(*image);
}

void rayscal_LoadTextureCubemap(TextureCubemap *out, const Image *image, int layout) {
    *out = LoadTextureCubemap(*image, layout);
}

bool rayscal_IsTextureValid(const Texture2D *texture) {
    return IsTextureValid(*texture);
}

void rayscal_UnloadTexture(const Texture2D *texture) {
    UnloadTexture(*texture);
}

void rayscal_UpdateTexture(const Texture2D *texture, const void *pixels) {
    UpdateTexture(*texture, pixels);
}

void rayscal_UpdateTextureRec(const Texture2D *texture, const Rectangle *rec, const void *pixels) {
    UpdateTextureRec(*texture, *rec, pixels);
}

void rayscal_SetTextureFilter(const Texture2D *texture, int filter) {
    SetTextureFilter(*texture, filter);
}

void rayscal_SetTextureWrap(const Texture2D *texture, int wrap) {
    SetTextureWrap(*texture, wrap);
}

void rayscal_DrawTexture(const Texture2D *texture, int x, int y, const Color *tint) {
    DrawTexture(*texture, x, y, *tint);
}

void rayscal_DrawTextureV(const Texture2D *texture, const Vector2 *position, const Color *tint) {
    DrawTextureV(*texture, *position, *tint);
}

void rayscal_DrawTextureEx(const Texture2D *texture, const Vector2 *position, float rotation, float scale, const Color *tint) {
    DrawTextureEx(*texture, *position, rotation, scale, *tint);
}

void rayscal_DrawTextureRec(const Texture2D *texture, const Rectangle *source, const Vector2 *position, const Color *tint) {
    DrawTextureRec(*texture, *source, *position, *tint);
}

void rayscal_DrawLine3D(const Vector3 *start, const Vector3 *end, const Color *color) {
    DrawLine3D(*start, *end, *color);
}

void rayscal_DrawPoint3D(const Vector3 *position, const Color *color) {
    DrawPoint3D(*position, *color);
}

void rayscal_DrawCircle3D(const Vector3 *center, float radius, const Vector3 *rotationAxis, float rotationAngle, const Color *color) {
    DrawCircle3D(*center, radius, *rotationAxis, rotationAngle, *color);
}

void rayscal_DrawTriangle3D(const Vector3 *v1, const Vector3 *v2, const Vector3 *v3, const Color *color) {
    DrawTriangle3D(*v1, *v2, *v3, *color);
}

void rayscal_DrawCube(const Vector3 *position, float width, float height, float length, const Color *color) {
    DrawCube(*position, width, height, length, *color);
}

void rayscal_DrawCubeV(const Vector3 *position, const Vector3 *size, const Color *color) {
    DrawCubeV(*position, *size, *color);
}

void rayscal_DrawCubeWires(const Vector3 *position, float width, float height, float length, const Color *color) {
    DrawCubeWires(*position, width, height, length, *color);
}

void rayscal_DrawCubeWiresV(const Vector3 *position, const Vector3 *size, const Color *color) {
    DrawCubeWiresV(*position, *size, *color);
}

void rayscal_DrawSphere(const Vector3 *center, float radius, const Color *color) {
    DrawSphere(*center, radius, *color);
}

void rayscal_DrawSphereEx(const Vector3 *center, float radius, int rings, int slices, const Color *color) {
    DrawSphereEx(*center, radius, rings, slices, *color);
}

void rayscal_DrawSphereWires(const Vector3 *center, float radius, int rings, int slices, const Color *color) {
    DrawSphereWires(*center, radius, rings, slices, *color);
}

void rayscal_DrawCylinder(const Vector3 *position, float radiusTop, float radiusBottom, float height, int slices, const Color *color) {
    DrawCylinder(*position, radiusTop, radiusBottom, height, slices, *color);
}

void rayscal_DrawCylinderEx(const Vector3 *start, const Vector3 *end, float startRadius, float endRadius, int sides, const Color *color) {
    DrawCylinderEx(*start, *end, startRadius, endRadius, sides, *color);
}

void rayscal_DrawCylinderWires(const Vector3 *position, float radiusTop, float radiusBottom, float height, int slices, const Color *color) {
    DrawCylinderWires(*position, radiusTop, radiusBottom, height, slices, *color);
}

void rayscal_DrawCylinderWiresEx(const Vector3 *start, const Vector3 *end, float startRadius, float endRadius, int sides, const Color *color) {
    DrawCylinderWiresEx(*start, *end, startRadius, endRadius, sides, *color);
}

void rayscal_DrawCapsule(const Vector3 *start, const Vector3 *end, float radius, int slices, int rings, const Color *color) {
    DrawCapsule(*start, *end, radius, slices, rings, *color);
}

void rayscal_DrawCapsuleWires(const Vector3 *start, const Vector3 *end, float radius, int slices, int rings, const Color *color) {
    DrawCapsuleWires(*start, *end, radius, slices, rings, *color);
}

void rayscal_DrawPlane(const Vector3 *center, const Vector2 *size, const Color *color) {
    DrawPlane(*center, *size, *color);
}

void rayscal_DrawRay(const Ray *ray, const Color *color) {
    DrawRay(*ray, *color);
}

void rayscal_DrawBoundingBox(const BoundingBox *box, const Color *color) { DrawBoundingBox(*box, *color); }

bool rayscal_CheckCollisionRecs(const Rectangle *a, const Rectangle *b) { return CheckCollisionRecs(*a, *b); }
bool rayscal_CheckCollisionCircles(const Vector2 *a, float radiusA, const Vector2 *b, float radiusB) { return CheckCollisionCircles(*a, radiusA, *b, radiusB); }
bool rayscal_CheckCollisionCircleRec(const Vector2 *center, float radius, const Rectangle *rectangle) { return CheckCollisionCircleRec(*center, radius, *rectangle); }
bool rayscal_CheckCollisionPointRec(const Vector2 *point, const Rectangle *rectangle) { return CheckCollisionPointRec(*point, *rectangle); }
bool rayscal_CheckCollisionPointCircle(const Vector2 *point, const Vector2 *center, float radius) { return CheckCollisionPointCircle(*point, *center, radius); }
bool rayscal_CheckCollisionPointTriangle(const Vector2 *point, const Vector2 *p1, const Vector2 *p2, const Vector2 *p3) { return CheckCollisionPointTriangle(*point, *p1, *p2, *p3); }
void rayscal_GetCollisionRec(Rectangle *out, const Rectangle *a, const Rectangle *b) { *out = GetCollisionRec(*a, *b); }
bool rayscal_CheckCollisionSpheres(const Vector3 *a, float radiusA, const Vector3 *b, float radiusB) { return CheckCollisionSpheres(*a, radiusA, *b, radiusB); }
bool rayscal_CheckCollisionBoxes(const BoundingBox *a, const BoundingBox *b) { return CheckCollisionBoxes(*a, *b); }
bool rayscal_CheckCollisionBoxSphere(const BoundingBox *box, const Vector3 *center, float radius) { return CheckCollisionBoxSphere(*box, *center, radius); }
void rayscal_GetRayCollisionSphere(RayCollision *out, const Ray *ray, const Vector3 *center, float radius) { *out = GetRayCollisionSphere(*ray, *center, radius); }
void rayscal_GetRayCollisionBox(RayCollision *out, const Ray *ray, const BoundingBox *box) { *out = GetRayCollisionBox(*ray, *box); }
void rayscal_GetRayCollisionTriangle(RayCollision *out, const Ray *ray, const Vector3 *p1, const Vector3 *p2, const Vector3 *p3) { *out = GetRayCollisionTriangle(*ray, *p1, *p2, *p3); }
void rayscal_GetRayCollisionQuad(RayCollision *out, const Ray *ray, const Vector3 *p1, const Vector3 *p2, const Vector3 *p3, const Vector3 *p4) { *out = GetRayCollisionQuad(*ray, *p1, *p2, *p3, *p4); }

Font *rayscal_LoadFont(const char *fileName, int fontSize) {
    if (!FileExists(fileName)) return NULL;
    Font *font = (Font *)malloc(sizeof(Font));
    if (font == NULL) return NULL;
    *font = LoadFontEx(fileName, fontSize, NULL, 0);
    return font;
}

bool rayscal_IsFontValid(const Font *font) {
    return font != NULL && IsFontValid(*font);
}

void rayscal_UnloadFont(Font *font) {
    if (font == NULL) return;
    if (IsFontValid(*font)) UnloadFont(*font);
    free(font);
}

void rayscal_DrawTextEx(const Font *font, const char *text, const Vector2 *position, float fontSize, float spacing, const Color *tint) {
    DrawTextEx(*font, text, *position, fontSize, spacing, *tint);
}

void rayscal_MeasureTextEx(Vector2 *out, const Font *font, const char *text, float fontSize, float spacing) {
    *out = MeasureTextEx(*font, text, fontSize, spacing);
}

void rayscal_LoadWave(Wave *out, const char *fileName) { *out = LoadWave(fileName); }
bool rayscal_IsWaveValid(const Wave *wave) { return IsWaveValid(*wave); }
void rayscal_UnloadWave(const Wave *wave) { UnloadWave(*wave); }

void rayscal_LoadSound(Sound *out, const char *fileName) { *out = LoadSound(fileName); }
void rayscal_LoadSoundFromWave(Sound *out, const Wave *wave) { *out = LoadSoundFromWave(*wave); }
void rayscal_LoadSoundAlias(Sound *out, const Sound *source) { *out = LoadSoundAlias(*source); }
bool rayscal_IsSoundValid(const Sound *sound) { return IsSoundValid(*sound); }
void rayscal_UnloadSound(const Sound *sound) { UnloadSound(*sound); }
void rayscal_UnloadSoundAlias(const Sound *sound) { UnloadSoundAlias(*sound); }
void rayscal_PlaySound(const Sound *sound) { PlaySound(*sound); }
void rayscal_StopSound(const Sound *sound) { StopSound(*sound); }
void rayscal_PauseSound(const Sound *sound) { PauseSound(*sound); }
void rayscal_ResumeSound(const Sound *sound) { ResumeSound(*sound); }
bool rayscal_IsSoundPlaying(const Sound *sound) { return IsSoundPlaying(*sound); }
void rayscal_SetSoundVolume(const Sound *sound, float volume) { SetSoundVolume(*sound, volume); }
void rayscal_SetSoundPitch(const Sound *sound, float pitch) { SetSoundPitch(*sound, pitch); }
void rayscal_SetSoundPan(const Sound *sound, float pan) { SetSoundPan(*sound, pan); }

void rayscal_LoadMusicStream(Music *out, const char *fileName) { *out = LoadMusicStream(fileName); }
bool rayscal_IsMusicValid(const Music *music) { return IsMusicValid(*music); }
void rayscal_UnloadMusicStream(const Music *music) { UnloadMusicStream(*music); }
void rayscal_PlayMusicStream(const Music *music) { PlayMusicStream(*music); }
void rayscal_UpdateMusicStream(const Music *music) { UpdateMusicStream(*music); }
void rayscal_StopMusicStream(const Music *music) { StopMusicStream(*music); }
void rayscal_PauseMusicStream(const Music *music) { PauseMusicStream(*music); }
void rayscal_ResumeMusicStream(const Music *music) { ResumeMusicStream(*music); }
bool rayscal_IsMusicStreamPlaying(const Music *music) { return IsMusicStreamPlaying(*music); }
void rayscal_SeekMusicStream(const Music *music, float position) { SeekMusicStream(*music, position); }
void rayscal_SetMusicVolume(const Music *music, float volume) { SetMusicVolume(*music, volume); }
void rayscal_SetMusicPitch(const Music *music, float pitch) { SetMusicPitch(*music, pitch); }
void rayscal_SetMusicPan(const Music *music, float pan) { SetMusicPan(*music, pan); }
float rayscal_GetMusicTimeLength(const Music *music) { return GetMusicTimeLength(*music); }
float rayscal_GetMusicTimePlayed(const Music *music) { return GetMusicTimePlayed(*music); }

typedef struct RayscalModel {
    Model value;
    Shader *originalShaders;
    unsigned char *shaderOverrides;
    Texture2D *originalTextures;
    unsigned char *textureOverrides;
} RayscalModel;

static int rayscal_model_map_count(void) { return MATERIAL_MAP_BRDF + 1; }

static RayscalModel *rayscal_wrap_model(Model value) {
    RayscalModel *model = (RayscalModel *)calloc(1, sizeof(RayscalModel));
    if (model == NULL) {
        if (IsModelValid(value)) UnloadModel(value);
        return NULL;
    }
    model->value = value;
    if (value.materialCount > 0) {
        size_t materials = (size_t)value.materialCount;
        size_t maps = materials*(size_t)rayscal_model_map_count();
        model->originalShaders = (Shader *)calloc(materials, sizeof(Shader));
        model->shaderOverrides = (unsigned char *)calloc(materials, 1);
        model->originalTextures = (Texture2D *)calloc(maps, sizeof(Texture2D));
        model->textureOverrides = (unsigned char *)calloc(maps, 1);
        if (model->originalShaders == NULL || model->shaderOverrides == NULL ||
            model->originalTextures == NULL || model->textureOverrides == NULL) {
            free(model->originalShaders);
            free(model->shaderOverrides);
            free(model->originalTextures);
            free(model->textureOverrides);
            if (IsModelValid(value)) UnloadModel(value);
            free(model);
            return NULL;
        }
    }
    return model;
}

RayscalModel *rayscal_LoadModel(const char *fileName) {
    return rayscal_wrap_model(LoadModel(fileName));
}

RayscalModel *rayscal_GenModelCube(float width, float height, float length) {
    return rayscal_wrap_model(LoadModelFromMesh(GenMeshCube(width, height, length)));
}

RayscalModel *rayscal_GenModelSphere(float radius, int rings, int slices) {
    return rayscal_wrap_model(LoadModelFromMesh(GenMeshSphere(radius, rings, slices)));
}

RayscalModel *rayscal_GenModelPlane(float width, float length, int resolutionX, int resolutionZ) {
    return rayscal_wrap_model(LoadModelFromMesh(GenMeshPlane(width, length, resolutionX, resolutionZ)));
}

RayscalModel *rayscal_GenModelCylinder(float radius, float height, int slices) {
    return rayscal_wrap_model(LoadModelFromMesh(GenMeshCylinder(radius, height, slices)));
}

RayscalModel *rayscal_GenModelTorus(float radius, float size, int radialSegments, int sides) {
    return rayscal_wrap_model(LoadModelFromMesh(GenMeshTorus(radius, size, radialSegments, sides)));
}

RayscalModel *rayscal_GenModelKnot(float radius, float size, int radialSegments, int sides) {
    return rayscal_wrap_model(LoadModelFromMesh(GenMeshKnot(radius, size, radialSegments, sides)));
}

bool rayscal_IsModelValid(const RayscalModel *model) {
    return model != NULL && IsModelValid(model->value);
}

void rayscal_UnloadModel(RayscalModel *model) {
    if (model == NULL) return;
    int mapCount = rayscal_model_map_count();
    for (int material = 0; material < model->value.materialCount; material++) {
        if (model->shaderOverrides[material]) {
            model->value.materials[material].shader = model->originalShaders[material];
        }
        for (int map = 0; map < mapCount; map++) {
            int index = material*mapCount + map;
            if (model->textureOverrides[index]) {
                model->value.materials[material].maps[map].texture = model->originalTextures[index];
            }
        }
    }
    if (IsModelValid(model->value)) UnloadModel(model->value);
    free(model->originalShaders);
    free(model->shaderOverrides);
    free(model->originalTextures);
    free(model->textureOverrides);
    free(model);
}

void rayscal_DrawModel(const RayscalModel *model, const Vector3 *position, float scale, const Color *tint) {
    DrawModel(model->value, *position, scale, *tint);
}

void rayscal_DrawModelEx(const RayscalModel *model, const Vector3 *position, const Vector3 *rotationAxis, float rotationAngle, const Vector3 *scale, const Color *tint) {
    DrawModelEx(model->value, *position, *rotationAxis, rotationAngle, *scale, *tint);
}

void rayscal_DrawModelWires(const RayscalModel *model, const Vector3 *position, float scale, const Color *tint) {
    DrawModelWires(model->value, *position, scale, *tint);
}

void rayscal_GetModelBoundingBox(BoundingBox *out, const RayscalModel *model) {
    *out = GetModelBoundingBox(model->value);
}

int rayscal_ModelMeshCount(const RayscalModel *model) { return model->value.meshCount; }
int rayscal_ModelMaterialCount(const RayscalModel *model) { return model->value.materialCount; }

bool rayscal_ModelSetMaterialShader(RayscalModel *model, int material, const Shader *shader) {
    if (model == NULL || material < 0 || material >= model->value.materialCount) return false;
    if (!model->shaderOverrides[material]) {
        model->originalShaders[material] = model->value.materials[material].shader;
        model->shaderOverrides[material] = 1;
    }
    model->value.materials[material].shader = *shader;
    return true;
}

bool rayscal_ModelSetMaterialColor(RayscalModel *model, int material, int map, const Color *color) {
    if (model == NULL || material < 0 || material >= model->value.materialCount || map < 0 || map >= rayscal_model_map_count()) return false;
    model->value.materials[material].maps[map].color = *color;
    return true;
}

bool rayscal_ModelSetMaterialValue(RayscalModel *model, int material, int map, float value) {
    if (model == NULL || material < 0 || material >= model->value.materialCount || map < 0 || map >= rayscal_model_map_count()) return false;
    model->value.materials[material].maps[map].value = value;
    return true;
}

bool rayscal_ModelSetMaterialTexture(RayscalModel *model, int material, int map, const Texture2D *texture) {
    if (model == NULL || material < 0 || material >= model->value.materialCount || map < 0 || map >= rayscal_model_map_count()) return false;
    int index = material*rayscal_model_map_count() + map;
    if (!model->textureOverrides[index]) {
        model->originalTextures[index] = model->value.materials[material].maps[map].texture;
        model->textureOverrides[index] = 1;
    }
    model->value.materials[material].maps[map].texture = *texture;
    return true;
}

void rayscal_GetRayCollisionModel(RayCollision *out, const Ray *ray, const RayscalModel *model) {
    RayCollision closest = { 0 };
    for (int i = 0; i < model->value.meshCount; i++) {
        RayCollision hit = GetRayCollisionMesh(*ray, model->value.meshes[i], model->value.transform);
        if (hit.hit && (!closest.hit || hit.distance < closest.distance)) closest = hit;
    }
    *out = closest;
}

FilePathList *rayscal_LoadDroppedFiles(void) {
    FilePathList *files = (FilePathList *)malloc(sizeof(FilePathList));
    if (files == NULL) return NULL;
    *files = LoadDroppedFiles();
    return files;
}

unsigned int rayscal_DroppedFileCount(const FilePathList *files) {
    return files == NULL ? 0 : files->count;
}

const char *rayscal_DroppedFilePath(const FilePathList *files, unsigned int index) {
    return files == NULL || index >= files->count ? NULL : files->paths[index];
}

void rayscal_UnloadDroppedFiles(FilePathList *files) {
    if (files == NULL) return;
    UnloadDroppedFiles(*files);
    free(files);
}

void rayscal_GetMouseDelta(Vector2 *out) { *out = GetMouseDelta(); }
void rayscal_GetMouseWheelMoveV(Vector2 *out) { *out = GetMouseWheelMoveV(); }
void rayscal_GetTouchPosition(Vector2 *out, int index) { *out = GetTouchPosition(index); }
void rayscal_GetGestureDragVector(Vector2 *out) { *out = GetGestureDragVector(); }
void rayscal_GetGesturePinchVector(Vector2 *out) { *out = GetGesturePinchVector(); }
void rayscal_GetWindowPosition(Vector2 *out) { *out = GetWindowPosition(); }
void rayscal_GetWindowScaleDPI(Vector2 *out) { *out = GetWindowScaleDPI(); }
void rayscal_GetMonitorPosition(Vector2 *out, int monitor) { *out = GetMonitorPosition(monitor); }
void rayscal_GetCameraMatrix(Matrix *out, const Camera *camera) { *out = GetCameraMatrix(*camera); }
void rayscal_GetCameraMatrix2D(Matrix *out, const Camera2D *camera) { *out = GetCameraMatrix2D(*camera); }

float rayscal_Clamp(float value, float minimum, float maximum) { return Clamp(value, minimum, maximum); }
float rayscal_Lerp(float start, float end, float amount) { return Lerp(start, end, amount); }
float rayscal_Remap(float value, float inStart, float inEnd, float outStart, float outEnd) { return Remap(value, inStart, inEnd, outStart, outEnd); }
float rayscal_Wrap(float value, float minimum, float maximum) { return Wrap(value, minimum, maximum); }

#define RAYSCAL_BINARY_OUT(type, name) void rayscal_##name(type *out, const type *a, const type *b) { *out = name(*a, *b); }
#define RAYSCAL_UNARY_OUT(type, name) void rayscal_##name(type *out, const type *value) { *out = name(*value); }
#define RAYSCAL_SCALE_OUT(type, name) void rayscal_##name(type *out, const type *value, float scale) { *out = name(*value, scale); }
#define RAYSCAL_BINARY_FLOAT(type, name) float rayscal_##name(const type *a, const type *b) { return name(*a, *b); }
#define RAYSCAL_UNARY_FLOAT(type, name) float rayscal_##name(const type *value) { return name(*value); }

RAYSCAL_BINARY_OUT(Vector2, Vector2Add)
RAYSCAL_BINARY_OUT(Vector2, Vector2Subtract)
RAYSCAL_BINARY_OUT(Vector2, Vector2Multiply)
RAYSCAL_BINARY_OUT(Vector2, Vector2Divide)
RAYSCAL_SCALE_OUT(Vector2, Vector2Scale)
RAYSCAL_UNARY_OUT(Vector2, Vector2Normalize)
void rayscal_Vector2Lerp(Vector2 *out, const Vector2 *a, const Vector2 *b, float amount) { *out = Vector2Lerp(*a, *b, amount); }
void rayscal_Vector2Rotate(Vector2 *out, const Vector2 *value, float angle) { *out = Vector2Rotate(*value, angle); }
RAYSCAL_BINARY_OUT(Vector2, Vector2Reflect)
void rayscal_Vector2Transform(Vector2 *out, const Vector2 *value, const Matrix *matrix) { *out = Vector2Transform(*value, *matrix); }
RAYSCAL_UNARY_FLOAT(Vector2, Vector2Length)
RAYSCAL_UNARY_FLOAT(Vector2, Vector2LengthSqr)
RAYSCAL_BINARY_FLOAT(Vector2, Vector2DotProduct)
RAYSCAL_BINARY_FLOAT(Vector2, Vector2Distance)

RAYSCAL_BINARY_OUT(Vector3, Vector3Add)
RAYSCAL_BINARY_OUT(Vector3, Vector3Subtract)
RAYSCAL_BINARY_OUT(Vector3, Vector3Multiply)
RAYSCAL_SCALE_OUT(Vector3, Vector3Scale)
RAYSCAL_BINARY_OUT(Vector3, Vector3CrossProduct)
RAYSCAL_UNARY_OUT(Vector3, Vector3Normalize)
void rayscal_Vector3Lerp(Vector3 *out, const Vector3 *a, const Vector3 *b, float amount) { *out = Vector3Lerp(*a, *b, amount); }
RAYSCAL_BINARY_OUT(Vector3, Vector3Reflect)
void rayscal_Vector3Transform(Vector3 *out, const Vector3 *value, const Matrix *matrix) { *out = Vector3Transform(*value, *matrix); }
void rayscal_Vector3RotateByQuaternion(Vector3 *out, const Vector3 *value, const Quaternion *quaternion) { *out = Vector3RotateByQuaternion(*value, *quaternion); }
RAYSCAL_UNARY_FLOAT(Vector3, Vector3Length)
RAYSCAL_UNARY_FLOAT(Vector3, Vector3LengthSqr)
RAYSCAL_BINARY_FLOAT(Vector3, Vector3DotProduct)
RAYSCAL_BINARY_FLOAT(Vector3, Vector3Distance)

RAYSCAL_BINARY_OUT(Vector4, Vector4Add)
RAYSCAL_BINARY_OUT(Vector4, Vector4Subtract)
RAYSCAL_SCALE_OUT(Vector4, Vector4Scale)
RAYSCAL_UNARY_OUT(Vector4, Vector4Normalize)
void rayscal_Vector4Lerp(Vector4 *out, const Vector4 *a, const Vector4 *b, float amount) { *out = Vector4Lerp(*a, *b, amount); }
RAYSCAL_UNARY_FLOAT(Vector4, Vector4Length)
RAYSCAL_BINARY_FLOAT(Vector4, Vector4DotProduct)

void rayscal_MatrixIdentity(Matrix *out) { *out = MatrixIdentity(); }
RAYSCAL_BINARY_OUT(Matrix, MatrixAdd)
RAYSCAL_BINARY_OUT(Matrix, MatrixSubtract)
RAYSCAL_BINARY_OUT(Matrix, MatrixMultiply)
RAYSCAL_UNARY_OUT(Matrix, MatrixTranspose)
RAYSCAL_UNARY_OUT(Matrix, MatrixInvert)
void rayscal_MatrixTranslate(Matrix *out, float x, float y, float z) { *out = MatrixTranslate(x, y, z); }
void rayscal_MatrixScale(Matrix *out, float x, float y, float z) { *out = MatrixScale(x, y, z); }
void rayscal_MatrixRotateX(Matrix *out, float angle) { *out = MatrixRotateX(angle); }
void rayscal_MatrixRotateY(Matrix *out, float angle) { *out = MatrixRotateY(angle); }
void rayscal_MatrixRotateZ(Matrix *out, float angle) { *out = MatrixRotateZ(angle); }
void rayscal_MatrixRotateXYZ(Matrix *out, const Vector3 *angle) { *out = MatrixRotateXYZ(*angle); }
void rayscal_MatrixLookAt(Matrix *out, const Vector3 *eye, const Vector3 *target, const Vector3 *up) { *out = MatrixLookAt(*eye, *target, *up); }
void rayscal_MatrixPerspective(Matrix *out, double fovY, double aspect, double nearPlane, double farPlane) { *out = MatrixPerspective(fovY, aspect, nearPlane, farPlane); }
void rayscal_MatrixOrtho(Matrix *out, double left, double right, double bottom, double top, double nearPlane, double farPlane) { *out = MatrixOrtho(left, right, bottom, top, nearPlane, farPlane); }
RAYSCAL_UNARY_FLOAT(Matrix, MatrixDeterminant)
RAYSCAL_UNARY_FLOAT(Matrix, MatrixTrace)

void rayscal_QuaternionIdentity(Quaternion *out) { *out = QuaternionIdentity(); }
RAYSCAL_BINARY_OUT(Quaternion, QuaternionAdd)
RAYSCAL_BINARY_OUT(Quaternion, QuaternionSubtract)
RAYSCAL_BINARY_OUT(Quaternion, QuaternionMultiply)
RAYSCAL_UNARY_OUT(Quaternion, QuaternionNormalize)
RAYSCAL_UNARY_OUT(Quaternion, QuaternionInvert)
void rayscal_QuaternionSlerp(Quaternion *out, const Quaternion *a, const Quaternion *b, float amount) { *out = QuaternionSlerp(*a, *b, amount); }
void rayscal_QuaternionFromEuler(Quaternion *out, float pitch, float yaw, float roll) { *out = QuaternionFromEuler(pitch, yaw, roll); }
void rayscal_QuaternionToEuler(Vector3 *out, const Quaternion *value) { *out = QuaternionToEuler(*value); }
void rayscal_QuaternionFromAxisAngle(Quaternion *out, const Vector3 *axis, float angle) { *out = QuaternionFromAxisAngle(*axis, angle); }
void rayscal_QuaternionToMatrix(Matrix *out, const Quaternion *value) { *out = QuaternionToMatrix(*value); }
