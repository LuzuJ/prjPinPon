import java.awt.*;
import java.awt.Color;

/**
 * Esta clase define un objeto texto en la pantalla
 */
public class Text{
    public String text;
    public Font font; 
    public double x, y;
    public double width, height;
    Color color = Color.WHITE;

    /**
     * Este constructor define los atributos de un objeto texto para representarlo en la pantalla.
     * 
     * @param text: Texto en la pantalla
     * @param font: formato de las letras
     * @param c: color
     * @param x: posicion en x
     * @param y: posicion en y
     * @param color: color en pantalla
     */
    public Text(String text, Font font, Color c, double x, double y, Color color) {
        this.font = font;
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = font.getSize() * text.length();
        this.height = font.getSize();
    }

    /**
     *  Este constructor define las puntuaciones en la pantalla
     * @param text: tamaño de letra
     * @param font: formato de la letra
     * @param x: posicion en "X"
     * @param y: posicion en "Y"
     */
    public Text(int text, Font font, double x, double y) {
        this.font = font;
        this.text = "" + text;
        this.x = x;
        this.y = y;
    }

    /**
     * Dibuja el texto en la pantalla
     * @param g2: Dibuja el grafico proporcionado
     */
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.setFont(font);
        g2.drawString(text, (float) x, (float) y);


    }
}