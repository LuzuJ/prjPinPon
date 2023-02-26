import java.awt.event.KeyEvent;

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

    public void moveUp (double dt){
        if (rect.y - Constantes.PADDLE_SPEED * dt > Constantes.TOOLBAR_HEIGHT)
        this.rect.y -= Constantes.PADDLE_SPEED * dt;
    }

    public void moveDown (double dt){
        if ((rect.y + Constantes.PADDLE_SPEED * dt) + rect.height < Constantes.VENTANA_ANCHO - Constantes.INSETS_BOTTOM)
        this.rect.y += Constantes.PADDLE_SPEED * dt;
    }
}