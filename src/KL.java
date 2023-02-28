import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Este metodo se utiliza para detectar si una tecla esta siendo presionada en el teclado.
 * 
 */
public class KL implements KeyListener{
    /**
     * Esta clase tiene un arreglo de booleanos que se utiliza para rastrear el estado de las teclas
     */
    private boolean keyPressed[] = new boolean[128];
    @Override
    public void keyTyped(KeyEvent keyEvent) {
      
    }

    /**
     * Cuando se presiona una tecla, se establece la entrada correpondiente al arreglo dando 'true'.
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] =true;
    }

    /**
     * Al soltar la recha la función se define como false
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
       keyPressed[keyEvent.getKeyCode()] = false;
    }
    /**
     * Esta funcion la utilizamos para comprobar si una tecla en particular está siendo presionada
     * @param keyCode: La tecla presionada en cuestion
     * @return: El valor asignado para dicha tecla
     */
    public boolean isKeyPressed (int keyCode){
        return keyPressed[keyCode]; 
    }
   
    
}
