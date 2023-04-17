package P3.Parte3;

import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    private Storage st;
    private Semaphore sP;
    private Semaphore sC;
    private Semaphore empty;
    private Semaphore full;
    private int N;
    public Producer (Storage st, Semaphore sP, Semaphore sC,int N,Semaphore empty, Semaphore full ) {
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
                empty.acquire();
                sP.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            st.almacenar(new Producto());
            System.out.println("Produciendo");
            full.release();
            sC.release();
            //System.out.println(id+": done CS"); // UNLOCK
        }
    }

}