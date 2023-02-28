import javax.swing.JFrame;
import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;

/**
 * Esta clase representa la ventana principal del juego, y es
 *  responsable de mostrar el menú principal al usuario
 */
public class MainMenu extends JFrame implements Runnable {
    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Text startGame, exitGame, pong;  
    public boolean isRunning = true; 

    // constructor Window 
    
    public MainMenu(){
        this.setSize(Constantes.VENTANA_ANCHO, Constantes.VENTANA_LARGO);
        this.setTitle(Constantes.VENTANA_TITULO);
        this.setResizable(false); // el usuario no modifique el tamaño de la ventana
        this.setVisible(true); // hace visible la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permite cerrar la ventana. 
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.startGame = new Text("Start Game", new Font ("Times New Roman", Font.PLAIN,  40), new Color(255, 128, 0), Constantes.VENTANA_ANCHO / 2.0 - 75.0, Constantes.VENTANA_LARGO / 2.0, Color.WHITE);
        this.exitGame = new Text("Exit Game", new Font ("Times New Roman", Font.PLAIN,  40), new Color(50, 50, 255), Constantes.VENTANA_ANCHO / 2.0 - 70.0, Constantes.VENTANA_LARGO / 2.0 + 60, Color.WHITE);
        this.pong = new Text("PING PONG GAME", new Font ("Times New Roman", Font.PLAIN,  40), Color.PINK, Constantes.VENTANA_ANCHO / 2.0 - 155.0, 200, Color.WHITE);
        g2 = (Graphics2D)getGraphics();
    }

    /**
     * Este metodo es responsable de actualizar la ventana con la imagen del menú principal y detectar si el usuario ha hecho
     * clic en uno de los botones.
     * 
     * @param dt: el tiempo transcurrido desde la ultima actualización.
     */
    public void update (double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics  dbg = dbImage.getGraphics(); 
        super.update(dbg);
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);  

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width &&
            mouseListener.getMousey() > startGame.y - startGame.height/2 && mouseListener.getMousey() < startGame.y + startGame.height/2.0){
            
                startGame.color = new Color (0, 255, 34);

                if (mouseListener.isMousePressed()){
                    App.changeState (1);
                }
        }else {
                startGame.color = Color.WHITE;
        }

        if (mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width &&
            mouseListener.getMousey() > exitGame.y - exitGame.height/2 && mouseListener.getMousey() < exitGame.y + exitGame.height/2.0){
            
                exitGame.color = new Color (255, 0, 0);
                if (mouseListener.isMousePressed()){
                    App.changeState(2);
                }
        }else {
                exitGame.color = Color.WHITE;
        }
    }

    /**
     * Se encarga de dibujar los elementos del menú principal en la ventana. 
     * 
     * @param g: Asigna los rectangulos a cada opción.
     */
    public void  draw (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_LARGO); 
        

        startGame.draw(g2);
        exitGame.draw(g2);
        pong.draw(g2);
    
    }
    /**
     * En caso de detener el juego se asignará  automaticamente el 'false'
     */
    public void stop(){
        isRunning = false; 
    }

    /**
     * Implementa un bucle de juego que llama la metodo 'update', en cada interación, usamos los 
     * metodos "FPS.getTime()", para calcular el tiempo transcurrido entre las interaciones y asegurar
     * una tasa de actualización constante 
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
