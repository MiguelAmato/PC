package P3.Parte2;

import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    private Storage st;
    private Semaphore sP;
    private Semaphore sC;
    private int N;
    public Producer (Storage st, Semaphore sP, Semaphore sC, int N) {
        this.st = st;
        this.sP = sP;
        this.sC = sC;
        this.N = N;
    }
    public void run() {
        for (int i = 0; i < N; i++) {
            //System.out.println(id+": in CS "+i);
            try {
                sP.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            st.almacenar(new Producto());
            sC.release();
            //System.out.println(id+": done CS"); // UNLOCK
        }
    }

}