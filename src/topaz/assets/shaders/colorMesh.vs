#version 430 core

in vec4 position;
in vec4 color;

uniform mat4 mvpMatrix;

out vec4 vColor;

void main()
{
    gl_Position = mvpMatrix * position;

    vColor = color;
}