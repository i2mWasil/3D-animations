package src.objects;

import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

public class Polygon3d {
    private List<src.objects.Point3d> vertices;
    private Color color;

    public Polygon3d(List<src.objects.Point3d> vertices, Color color) {
        this.vertices = vertices;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void draw(Graphics g, float[][] projectionMatrix, boolean wireframeMode) {
        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            src.objects.Point3d transformed = vertices.get(i).transform(projectionMatrix);
            xPoints[i] = (int) (transformed.x * 100 + 400); // Centering
            yPoints[i] = (int) (transformed.y * 100 + 300); // Centering
        }

        g.setColor(color);

        if (wireframeMode) {
            g.setColor(Color.ORANGE);
            g.drawPolygon(xPoints, yPoints, vertices.size());
        } else {
            g.fillPolygon(xPoints, yPoints, vertices.size());
            g.setColor(Color.BLACK);
            g.drawPolygon(xPoints, yPoints, vertices.size());
        }
    }

    public float getAverageZ(float[][] projectionMatrix) {
        float sum = 0;
        for (src.objects.Point3d vertex : vertices) {
            src.objects.Point3d transformedVertex = vertex.transform(projectionMatrix);
            sum += transformedVertex.z;
        }
        return sum / vertices.size();
    }
}
