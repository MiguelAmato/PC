package P1.Parte1;

public class Hilo extends Thread {
    // Milisegundos que tendra que esperar el hilo
    private static final long T = 3000;

    private int id = 0; 

    public Hilo(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Hola, soy el hilo: " + id);
        try {
            sleep(T);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hola, soy el hilo: " + id);
    }
}
