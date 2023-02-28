
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionListener;

/**
 * Esta clase esta diseñada para manejar los eventos realizados por el mouse, 
 */
public class ML extends MouseAdapter  {
    public boolean isPressed = false;
    public double x = 0.0, y = 0.0;

/**
 * Este metodo maneja el movimineto del mouse
 */
@Override
public void mouseMoved(MouseEvent e) {
    this.x = e.getX();
    this.y = e.getY();
}

/**
 * Este metodo maneja los clic realizados por el mouse
 */
@Override
public void mousePressed(MouseEvent e) {
    isPressed = true;
}

/**
 * Este metodo maneja la liberacion del clic por parte del mouse
 */
@Override
public void mouseReleased(MouseEvent e) {
    isPressed = false;
}

/**
 * Este metodo nos retorna la posicion en "X", del mouse
 * @return: posicion en "X", del mouse
 */
public double getMouseX(){
    return this.x;
   }

   /**
    * Este metodo retorna la posicion en "Y" del mouse
    * @return: posicion en "Y", del mouse
    */
   public double getMousey(){
    return this.y;
   }
   /**
    * Este metodo nos retorna si el botón del mouse se encuentra presionado.
    * @return: el estado del botón del mouse
    */
   public boolean isMousePressed (){
    return this.isPressed;
   }

}
