#version 430 core

layout(location = 0) in vec4 position;
layout(location = 1) in vec4 color;

uniform mat4 mvpMatrix;

out vec4 vColor;

void main()
{
    gl_Position = mvpMatrix * position;

    vColor = color;
}