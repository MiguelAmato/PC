package P3.Parte2;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private Storage st;
    private Semaphore sP,sC;
    private int N;
    private Producto p;
    public Consumer (Storage st, Semaphore sP, Semaphore sC, int N) {
        this.st = st;
        this.sP = sP;
        this.sC = sC;
        this.N = N;
    }
    public void run() {
        for (int i = 0; i < N; i++) {
            //System.out.println(id+": in CS "+i);
            try {
                sC.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p = st.extraer();
            consumir();
            sP.release();
            //System.out.println(id+": done CS"); // UNLOCK
        }
    }

    private void consumir() {
      //  System.out.println("Consumiendo");
    }
}
