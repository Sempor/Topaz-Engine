package topaz.rendering.lighting;

import topaz.util.Color4f;

public class LightObject {

    private Color4f lightColor;
    private float lightIntensity;

    public LightObject(Color4f lightColor, float lightIntensity) {
        this.lightColor = lightColor;
        this.lightIntensity = lightIntensity;
    }

    public Color4f getLightColor() {
        return lightColor;
    }

    public void setLightColor(Color4f lightColor) {
        this.lightColor = lightColor;
    }

    public float getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(float lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
}