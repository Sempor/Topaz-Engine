#version 430 core

in vec4 position;
in vec2 textureCoord;

uniform mat4 mvpMatrix;

out vec2 vTextureCoord;

void main()
{
    gl_Position = mvpMatrix * position;

    vTextureCoord = textureCoord;
}