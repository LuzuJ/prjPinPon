/**
 * Esta clase la utilizamos para controlar el movimiento de un jugador usando la computadora.
 */
public class AIController {
    public PlayerController playerController;
    public Rect ball;
    public Rect rect;//representa el area del juego que se controla por computadora

    /**
     * Este constuctor inicializa los campos solicitados
     * 
     * @param plrController: para mantener una referencia del objeto
     * @param ball: representa la pelota del juego
     */
    public AIController(PlayerController plrController, Rect ball){
        this.playerController = plrController;
        this.ball = ball;
        

    } 

    /**
     * Este metodo se utiliza para actualizar el estado del juego, actualizar la posicion de la pelota
     * 
     * @param dt: controla la velocidad de la pelota
     */
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

    /**
     * Controla si la pelota se encuentra por encima del jugador, hace que la pelota se mueva hacia arriba
     * 
     * @param dt: el tiempo transcurrido desde la ultima actualización o su velocidad.
     */
    public void moveUp (double dt){
        if (rect.y - Constantes.PADDLE_SPEED * dt > Constantes.TOOLBAR_HEIGHT)
        this.rect.y -= Constantes.PADDLE_SPEED * dt;
    }

    /**
     * Controla si la pelota se mueve por debajo del jugador
     * 
     * @param dt: el tiempo transcurrido desde la ultima actualización, o su velocidad.s
     */
    public void moveDown (double dt){
        if ((rect.y + Constantes.PADDLE_SPEED * dt) + rect.height < Constantes.VENTANA_ANCHO - Constantes.INSETS_BOTTOM)
        this.rect.y += Constantes.PADDLE_SPEED * dt;
    }
}