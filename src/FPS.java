public class FPS {
    public static double tiempoAct = System.nanoTime();

    public static double getTime(){
        return((System.nanoTime() - tiempoAct) * 1E-9); //  Se multiplica 1E-9 porque retorna un integer. 
     } 
}