package topaz.rendering;

import java.nio.FloatBuffer;
import org.joml.Matrix4f;

public class RenderingUtils {

    public static Matrix4f storeMatrixInBuffer(FloatBuffer matrixBuffer, Matrix4f matrix) {
        matrixBuffer.put(matrix.m00());
        matrixBuffer.put(matrix.m01());
        matrixBuffer.put(matrix.m02());
        matrixBuffer.put(matrix.m03());
        matrixBuffer.put(matrix.m10());
        matrixBuffer.put(matrix.m11());
        matrixBuffer.put(matrix.m12());
        matrixBuffer.put(matrix.m13());
        matrixBuffer.put(matrix.m20());
        matrixBuffer.put(matrix.m21());
        matrixBuffer.put(matrix.m22());
        matrixBuffer.put(matrix.m23());
        matrixBuffer.put(matrix.m30());
        matrixBuffer.put(matrix.m31());
        matrixBuffer.put(matrix.m32());
        matrixBuffer.put(matrix.m33());
        return matrix;
    }
}