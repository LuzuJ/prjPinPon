import javax.swing.JFrame;
import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;

/**
 * Esta clase establece controla todo lo que se mostrará en pantalla desde el texto hasta las raquetas del juego.
 */
public class Window extends JFrame implements Runnable {
    public Graphics2D g2;//objeto para dibujar en la ventana
     public KL keyListener = new KL(); //objeto que maneja los eventos por teclado
     public Rect playerOne, ai, ballRect; // objeto que representa la raqueta y la pelota
     public PlayerController playerController; // objeto que controla la raqueta del jugador
     public AIController aiController; //objeto que controla la raqueta IA
     public Ball ball; //objeto que controla la pelota
     public Text leftScoreText, rightScoreText; //objeto para mostrar las puntuaciones 
     public boolean isRunning = true;  
     
    /**
     * Este metodo nos permite mostrar todo lo que se enucuentra en la pantalla, desde el texto hasta la 
     * implementacion de la IA, que jugará con el usuario, de igual manera se encuentra las puntuaciones, de igual
     * manera encontraremos rectangulos y la manera en como jugaremos
     */
    public Window(){
        this.setSize(Constantes.VENTANA_ANCHO, Constantes.VENTANA_LARGO);
        this.setTitle(Constantes.VENTANA_TITULO);
        this.setResizable(false); // el usuario no modifique el tamaño de la ventana
        this.setVisible(true); // hace visible la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permite cerrar la ventana. 
        this.addKeyListener(keyListener);
        Constantes.TOOLBAR_HEIGHT = this.getInsets().top;
        Constantes.INSETS_BOTTOM = this.getInsets().bottom; 
        g2 = (Graphics2D)this.getGraphics();

        leftScoreText = new Text (0, new Font("Times New Roman",  Font.PLAIN,Constantes.TEXT_SIZE), Constantes.TEXT_X_POS,Constantes.TEXT_Y_POS);
        rightScoreText = new Text (0, new Font("Times New Roman",  Font.PLAIN, Constantes.TEXT_SIZE), Constantes.VENTANA_ANCHO - Constantes.TEXT_X_POS - Constantes.TEXT_SIZE, Constantes.TEXT_Y_POS);

        playerOne = new Rect(Constantes.HZ_PADDING,40,Constantes.PADDLE_WIDTH, Constantes.PADDLE_HEIGHT, Constantes.PADDLE_COLOR);
        playerController = new PlayerController (playerOne, keyListener);

        ai = new Rect(Constantes.VENTANA_ANCHO - Constantes.PADDLE_WIDTH - Constantes.HZ_PADDING , 40, Constantes.PADDLE_WIDTH, Constantes.PADDLE_HEIGHT, Constantes.PADDLE_COLOR);
        ballRect = new Rect (Constantes.VENTANA_ANCHO / 2, Constantes.VENTANA_LARGO / 2, Constantes.BALL_WIDTH, Constantes.BALL_WIDTH, Constantes.PADDLE_COLOR);
        ball = new Ball (ballRect, playerOne, ai, leftScoreText, rightScoreText);
        aiController = new AIController(new PlayerController(ai) , ballRect);

    }

    /**
     * Este metodo actualiza la posición, y la velocidad de los objetos del jugador, bola 
     * e inteligencia artificial.
     * @param dt: el tiempo transcurrido desde la ultima actualización, o la velocidad que adquiere la pelota.
     */
    public void update (double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics  dbg = dbImage.getGraphics(); 
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);
       
    }

    /**
     * Dibuja todos los objetos gráficos de la ventana
     * @param g: todos los objetos en la ventana 2D
     */
    public void  draw (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_LARGO); 
    
        leftScoreText.draw(g2);
        rightScoreText.draw(g2);
        playerOne.draw(g2);
        ai.draw(g2);
        ballRect.draw(g2);
    }

    public void stop (){
        isRunning = false;
    }

    /**
     * Ejecuta un bucle de juego en cada interacción y luego llama a "draw", para actualizar
     * la pantalla, cuando el juego cierra se usa el metodo "dispose()"
     */
    public void run (){
        double frameFinalTiempo = 0.0;
        while (isRunning) {
            double tiempo = FPS.getTime();
            double tiempoDelta = tiempo - frameFinalTiempo;
            frameFinalTiempo = tiempo;
            update(tiempoDelta);
            // haga lo que se solicita
            
        }
        this.dispose();
        return;

    }

}
