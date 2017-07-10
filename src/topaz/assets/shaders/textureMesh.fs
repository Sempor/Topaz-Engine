#version 430 core

uniform sampler2D textureDiffuse;

in vec2 vTextureCoord;

out vec4 fragColor;

void main()
{
    fragColor = texture(textureDiffuse, vTextureCoord);
}