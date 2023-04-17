package P4.Practica3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
    private Storage st;
    private int N;
    private Producto p;

    private final Lock l;

    private final Condition c;

    public Consumer (Storage st, int N) {
        l = new ReentrantLock(true);
        c = l.newCondition();
        this.st = st;
        this.N = N;
    }
    public void run() {
        for (int i = 0; i < N; i++) {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p = st.extraer();
            consumir();
            c.signalAll();
        }
    }

    private void consumir() {
        System.out.println("Consumiendo");
    }
}
