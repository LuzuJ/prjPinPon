
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionListener;


public class ML extends MouseAdapter  {
    public boolean isPressed = false;
    public double x = 0.0, y = 0.0;


@Override
public void mouseMoved(MouseEvent e) {
    this.x = e.getX();
    this.y = e.getY();
}

@Override
public void mousePressed(MouseEvent e) {
    isPressed = true;
}

@Override
public void mouseReleased(MouseEvent e) {
    isPressed = false;
}

public double getMouseX(){
    return this.x;
   }

   public double getMousey(){
    return this.y;
   }
   public boolean isMousePressed (){
    return this.isPressed;
   }

}
