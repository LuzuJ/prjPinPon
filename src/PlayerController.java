import java.awt.event.KeyEvent;
/**
 * Esta clase recibe un triangulo que representa la posicion y tamaño del jugador en la pantalla,
 * a su vez es responsable de detectar las teclas presionadas por el usuario.
 */
public class PlayerController {
    public Rect rect;
    public KL keyListener;
    
    public PlayerController(Rect rect, KL keyListener){
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public PlayerController(Rect rect ){
        this.rect = rect;
        this.keyListener = null;
    }


    /**
     * Este metodo , es el que actualiza la posición del jugador en la pantalla en función de las teclas
     * presionadas por el usuario. Si la tecla presionada es la flecha hacia arriba, entonces el jugador 
     * se moverá hacia arriba, en caso de ser la flecha hacia abajo, el jugador se moverá hacia abajo.
     * Esto siempre y cuando su posición no exceda la posición en la pantalla 
     * 
     * @param dt: el tiempo transcurrido desde la ultima actualización.
     */
    public void update (double dt) {
        if (keyListener != null) {
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            if ((rect.y + 100 * dt ) +  rect.height < Constantes.VENTANA_LARGO)
            moveDown(dt);
        } else if(keyListener.isKeyPressed(KeyEvent.VK_UP)){
            if ((rect.y - 100 * dt )  > Constantes.TOOLBAR_HEIGHT)
            moveUp(dt);
        }
    }
}

/**
 * Este metodo se encarga de mover el rectangulo del jugador hacia arriba, sin que se salga de la ventana.
 * @param dt: el tiempo transcurrido desde la ultima actualización
 */
    public void moveUp (double dt){
        if (rect.y - Constantes.PADDLE_SPEED * dt > Constantes.TOOLBAR_HEIGHT)
        this.rect.y -= Constantes.PADDLE_SPEED * dt;
    }

    /**
     * Este metodo se encarga de mover el rectangulo del jugador hacia abajo, sin que se salga de la ventada
     * @param dt: el tiempo transcurrido desde la ultima actualización
     */
    public void moveDown (double dt){
        if ((rect.y + Constantes.PADDLE_SPEED * dt) + rect.height < Constantes.VENTANA_ANCHO - Constantes.INSETS_BOTTOM)
        this.rect.y += Constantes.PADDLE_SPEED * dt;
    }
}