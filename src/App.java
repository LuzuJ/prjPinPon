public class App {
    public static int state = 0;
    public static Thread mainThread;
    public static MainMenu menu;
    public static Window window;
    public static EndGame endScreen;
    public static Thread endThread;
    //public static boolean stateLoop = false;

    public static void main(String[] args) throws Exception {
        menu = new MainMenu();
        mainThread = new Thread(menu);
        mainThread.start();
    }

    public static void changeState(int newState) {
        boolean stateBucle = true; // inicializar a true para asegurar que entre al bucle al menos una vez
    
        while (stateBucle) {
            switch (newState) {
                case 0:
                    if (state == 1) {
                        window.stop();
                        endScreen.stop();
                        
                    }
                    menu = new MainMenu();
                    mainThread = new Thread(menu);
                    mainThread.start();
                    state = 0;
                    stateBucle = false;
                    break;
                case 1:
                    if (state == 0) {
                        menu.stop();
                    }
                    window = new Window();
                    mainThread = new Thread(window);
                    mainThread.start();
                    state = 1;
                    stateBucle = false;
                    break;
                case 2:
                    if (endScreen != null) {
                        endScreen.stop();
                    }
                    if (menu != null) {
                        menu.stop();
                    }
                    state = 2;
                    stateBucle = false;
                    break;
                case 3:
                    if (window != null) {
                        window.stop();
                        endScreen = new EndGame();
                        endThread = new Thread(endScreen);
                        endThread.start();
                        state = 1;
                        stateBucle = false;
                    }
                    break;
                case 4:
                    if (endScreen != null) {
                        endScreen.stop();
                    }
                    newState = 0;
                    stateBucle = false;
                    break;
            }
        }
    }
}