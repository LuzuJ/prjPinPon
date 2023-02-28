/**
 * Esta clase define los objetos y el funcionamiento de la pelota para el ping pong.
 */
public class Ball {
    public Rect  rect;
    public Rect  leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;
    private double vy = 400.0;
    private double vx = -150.0;
    // velocity x, y

    /**
     * Esta constuctor inicializa los valores de la bola dentro del ping pong.
     * 
     * @param rect: Representa la posicion y el tamaño de la bola
     * @param leftPaddle: Representa la paleta izquierda
     * @param rightPaddle: Representa la paleta izquierda
     * @param leftScoreText: Nos representa la puntuación por la izquierda
     * @param rightScoreText: Nos representa la puntuación por la derecha
     */
    public Ball (Rect rect, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText){
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;

    }

    /**
     * Este metodo calcula el ángulo de reflexión de la bola cuando golpea una paleta
     * @param paddle: Tenemos la pisición de la pelota
     * @return: EL angulo de la pelota al rebotar
     */
    public double calculateNewVelocityAngle (Rect paddle){
        double relativeIntersectY = (paddle.y + (paddle.height /2.0)) - (this.rect.y + (this.rect.height / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2.0);
        double theta = normalIntersectY * Constantes.MAX_ANGLE;
        return Math.toRadians(theta);
    }

    

    /**
     * Este metodo actualiza la posición de la bola en funcion de su velocidad detectando 
     * la colisión con las paletas y los limites de la pantalla.
     * 
     * @param dt: el tiempo transcurrido desde la ultima actualización, o la velocidad que adquiere la pelota.
     */
    public void update (double dt){
        if (vx < 0 ){
            if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x + this.rect.width >= this.leftPaddle.x &&
                this.rect.y  >= this.leftPaddle.y && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height){
                double theta = calculateNewVelocityAngle(leftPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constantes.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constantes.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;
            }else if (this.rect.x + this.rect.width < this.leftPaddle.x){
                System.out.println("You lost ");
            }
        }else if (vx > 0){
            if (this.rect.x + this.rect.width >= this.rightPaddle.x  && this.rect.x <= this.rightPaddle.x + this.rightPaddle.width &&
                this.rect.y  >= this.rightPaddle.y && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height){
                    double theta = calculateNewVelocityAngle(rightPaddle);
                    double newVx = Math.abs((Math.cos(theta)) * Constantes.BALL_SPEED);
                    double newVy = (-Math.sin(theta)) * Constantes.BALL_SPEED;
    
                    double oldSign = Math.signum(vx);
                    this.vx = newVx * (-1.0 * oldSign);
                    this.vy = newVy;
            }else if (this.rect.x + this.rect.width > this.rightPaddle.x + this.rightPaddle.width){
                System.out.println("AI has lost a point");
            }
        }

        if (vy > 0 ){
            if (this.rect.y + this.rect.height > Constantes.VENTANA_LARGO){
                this.vy *= -1;

            }
        } else if (vy < 0){
            if (this.rect.y < Constantes.TOOLBAR_HEIGHT){
                this.vy *= -1;
            }
        }
        // position = poistion  + velocity
        // velocity = velocity + aceleration
        this.rect.x +=  vx * dt;
        this.rect.y +=  vy * dt;

        if (this.rect.x + this.rect.width < leftPaddle.x) {
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore; 
            this.rect.x = Constantes.VENTANA_ANCHO / 2.0;
            this.rect.y = Constantes.VENTANA_LARGO / 2.0;
            this.vx = -150.0;
            this.vy = 10.0;
            if (rightScore == Constantes.WIN_SCORE){
                rightScoreText.text = "0";
                App.changeState(3);
            }

        } else if (this.rect.x > rightPaddle.x + rightPaddle.width) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore; 
            this.rect.x = Constantes.VENTANA_ANCHO / 2.0;
            this.rect.y = Constantes.VENTANA_LARGO / 2.0;
            this.vx = 10;
            this.vy = -150.0;
            if (leftScore == Constantes.WIN_SCORE){
                
                App.changeState(3);
            }
        }
    }
}
