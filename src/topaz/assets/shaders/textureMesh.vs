#version 430 core

layout(location = 0) in vec4 position;
layout(location = 1) in vec2 textureCoord;

uniform mat4 mvpMatrix;

out vec2 vTextureCoord;

void main()
{
    gl_Position = mvpMatrix * position;

    vTextureCoord = textureCoord;
}