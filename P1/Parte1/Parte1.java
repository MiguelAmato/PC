package P1.Parte1;

public class Parte1 {
    // Constantes
    private static final int N = 5;

    public static void main(String[] args) {
        // Creamos los hilos
        Hilo[] hilos = new Hilo[N];

        for (int i = 0; i < N; i++) {
            hilos[i] = new Hilo(i);
            hilos[i].start();
        }

        // Esperamos a que terminen
        for (int i = 0; i < N; i++) {
            try {
                hilos[i].join();
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los hilos han terminado");
    }
}
