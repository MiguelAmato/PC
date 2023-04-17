package P4.Practica3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
    private Storage st;
    private int N;

    private final Lock l;
    private final Condition c;

    public Producer (Storage st,int N) {
        l = new ReentrantLock(true);
        c = l.newCondition();
        this.N = N;
    }
    public void run() {
        for (int i = 0; i < N; i++) {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           
            st.almacenar(new Producto());
            System.out.println("Produciendo");
            
            c.notifyAll();
        }
    }

}