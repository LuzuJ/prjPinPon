import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Font;

/**
 * Esta clase es responsable de crear la pantalla cuando el juego termina, a su vez enseña dos posibles
 * opciones que son: "TRY AGAIN" y "EXIT GAME"
 */
public class EndGame extends JFrame implements Runnable{

    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Text tryAgain, exitGame2;  
    public boolean isRunning = true; 
   
   /**
    * En este constructor se establece el tamaño y el titulo de la ventada, se hace visible y se establece que al cerrar 
    * la ventana el programa termine, así mismo detecta los eventos del teclado y del mouse respectivamente. 
    * El objeto "text" esta creado para cada opción que se muestre en pantalla y se establecen sus propiedades como fuente, posicion y color. 
    */ 
    public EndGame (){
        this.setSize(Constantes.VENTANA_ANCHO, Constantes.VENTANA_LARGO);
        this.setTitle(Constantes.VENTANA_TITULO);
        this.setResizable(false); // el usuario no modifique el tamaño de la ventana
        this.setVisible(true); // hace visible la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permite cerrar la ventana. 
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.tryAgain = new Text("Try Again", new Font ("Times New Roman", Font.PLAIN,  40), new Color(255, 128, 0), Constantes.VENTANA_ANCHO / 2.0 - 75.0, Constantes.VENTANA_LARGO / 2.0, Color.WHITE);
        this.exitGame2 = new Text("Exit Game", new Font ("Times New Roman", Font.PLAIN,  40), new Color(50, 50, 255), Constantes.VENTANA_ANCHO / 2.0 - 70.0, Constantes.VENTANA_LARGO / 2.0 + 60, Color.WHITE);
        g2 = (Graphics2D)getGraphics();
    }
    /**
     * Este metodo crea una imagen de doble búder para evitar el parpadeo al actualizar la pantalla. Luego dibuja el "Try Again" y "Exit Game" en la pantalla.
     * Así mismo si el mouse se encuentra sobre algunas de las opciones, su color cambia.
     * "Try Again": Reinicia el juego
     * "Exit game": Cierra el juego
     * @param dt: el tiempo transcurrido desde la ultima actualización.
     */
    public void update (double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics  dbg = dbImage.getGraphics(); 
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);  

        if (mouseListener.getMouseX() > tryAgain.x && mouseListener.getMouseX() < tryAgain.x + tryAgain.width &&
            mouseListener.getMousey() > tryAgain.y - tryAgain.height/2 && mouseListener.getMousey() < tryAgain.y + tryAgain.height/2.0){
            
                tryAgain.color = new Color (0, 255, 34);

                if (mouseListener.isMousePressed()){
                    App.changeState (0);
                }
        }else {
            tryAgain.color = Color.WHITE;
        }

        if (mouseListener.getMouseX() > exitGame2.x && mouseListener.getMouseX() < exitGame2.x + exitGame2.width &&
            mouseListener.getMousey() > exitGame2.y - exitGame2.height/2 && mouseListener.getMousey() < exitGame2.y + exitGame2.height/2.0){
            
                exitGame2.color = new Color (255, 0, 0);
                if (mouseListener.isMousePressed()){
                    App.changeState(2);
                }
        }else {
            exitGame2.color = Color.WHITE;
        }
    }

    /**
     * Este método dibuja un rectángulo negro en toda la pantalla y se dibuja las opciones "Try Again", "Exit Game"
     * 
     * @param g: Asigna los rectangulos a cada opción
     */
    public void  draw (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constantes.VENTANA_ANCHO, Constantes.VENTANA_LARGO); 
        

        tryAgain.draw(g2);
        exitGame2.draw(g2);
       
    
    }
    /**
     * Este metodo cambia la variable a 'false', lo que detiene el ciclo del programa en el metodo "run"
     */
    public void stop(){
        isRunning = false; 
    }

    /**
     * Este metodo, por cada interación calcula el tiempo transcurrido desde la última interación
     * y llamamos al metodo "update", con ese valor de tiepo. Luego dibuja la pantalla y se espera hasta el 
     * siguiente fotograma. Cuando se detiene le ciclo principal, se elimina la ventama de la pantalla y el método retorna.
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
