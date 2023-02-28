import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * Es una clase para representar un triangulo en un espacio 2D
 */
public class Rect {
    public double x, y, width, height;
    private Color color;

    
    /**
     *  Este metodo asigna los valores para un rectangulo en 2D
     * @param x: coordenadas en "X"
     * @param y: coordenadas en "Y"
     * @param width: ancho
     * @param height: alto
     * @param color: color 
     */
    public Rect (double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Este metodo permite dibujar el rectangulo en un objeto 2D, usando cierto color
     * @param g2: el rectangulo dibujado
     */
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
