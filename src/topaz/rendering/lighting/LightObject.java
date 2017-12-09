package topaz.rendering.lighting;

import topaz.util.Color;

public class LightObject {

    private Color lightColor;
    private float lightIntensity;

    public LightObject(Color lightColor, float lightIntensity) {
        this.lightColor = lightColor;
        this.lightIntensity = lightIntensity;
    }

    public Color getLightColor() {
        return lightColor;
    }

    public void setLightColor(Color lightColor) {
        this.lightColor = lightColor;
    }

    public float getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(float lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
}