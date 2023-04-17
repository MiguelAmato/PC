package P3.Parte3;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private Storage st;
    private Semaphore sP,sC,empty,full;
    private int N;
    private Producto p;
    public Consumer (Storage st, Semaphore sP, Semaphore sC,int N,Semaphore empty, Semaphore full ) {
        this.st = st;
        this.sP = sP;
        this.sC = sC;
        this.N = N;
        this.empty = empty;
        this.full = full;
    }
    public void run() {
        for (int i = 0; i < N; i++) {
            //System.out.println(id+": in CS "+i);
            try {
                full.acquire();
                sC.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p = st.extraer();
            consumir();
            empty.release();
            sP.release();
            //System.out.println(id+": done CS"); // UNLOCK
        }
    }

    private void consumir() {
        System.out.println("Consumiendo");
    }
}
