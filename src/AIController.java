public class AIController {
    public PlayerController playerController;
    public Rect ball;
    public Rect rect;

    public AIController(PlayerController plrController, Rect ball){
        this.playerController = plrController;
        this.ball = ball;
        

    } 
    
    public void update(double dt) {
        playerController.update(dt);

        if (ball.y < playerController.rect.y) {
            if ((ball.y + 100 * dt ) +  ball.height < Constantes.VENTANA_LARGO) // Contolo que no se salga de la pantalla
            playerController.moveUp(dt);
        } else if (ball.y + ball.height > playerController.rect.y + playerController.rect.height) {
            if ((ball.y - 100 * dt )  > Constantes.TOOLBAR_HEIGHT)
            playerController.moveDown(dt);
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