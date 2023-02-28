/**
 * Esta clase se utiliza para calculas el tiempo entre cuadros del juego, lo que a su vez se utiliza para
 * controlar la velocidad de los objetos en el juego y para sincronizarlos con la animación.
 */
public class FPS {
    public static double tiempoAct = System.nanoTime();

    public static double getTime(){
        return((System.nanoTime() - tiempoAct) * 1E-9); //  Se multiplica 1E-9 porque retorna un integer. 
     } 
}