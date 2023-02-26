import javax.swing.JFrame;
import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;

public class Window extends JFrame implements Runnable {
    public Graphics2D g2;
     public KL keyListener = new KL();
     public Rect playerOne, ai, ballRect;
     public PlayerController playerController;
     public AIController aiController;
     public Ball ball;
     public Text leftScoreText, rightScoreText;
     public boolean isRunning = true; 
     
    // constructor Window 
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

    public void update (double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics  dbg = dbImage.getGraphics(); 
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);
       
    }

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
