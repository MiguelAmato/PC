package P3.Parte1;

import java.util.concurrent.Semaphore;

public class Parte1 {
    private static final int N = 100;
    private static final int M = 500;

    private static class Entero {
        private int n;
        public Entero (int n) {
            this.n = n;
        }
        public void increment() {
            n++;
        }
        public void decrement() {
            n--;
        }
        public int get() {
            return n;
        }
    }

    private static class Hilo extends Thread {
        private int id;
        private Entero n;
        Semaphore s;
        public Hilo (int id, Entero n, Semaphore s) {
            this.id = id;
            this.n = n;
            this.s = s;
        }
        public void run() {
            for (int i = 0; i < N; i++) {
                //System.out.println(id+": in CS "+i);
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (id % 2 == 0) {
                    n.increment();
                }
                else {
                    n.decrement();
                }
                s.release();
                //System.out.println(id+": done CS"); // UNLOCK
            }
        }
    }

    public static void main(String[] args) {
        try {
          Entero n = new Entero(0);

          Hilo[] hilos = new Hilo[M+M];

          Semaphore s = new Semaphore(1);
  
          for (int i = 0; i < M+M; ++i) {
              hilos[i] = new Hilo(i, n, s);
          }
  
          for (int i = 0; i < M+M; ++i) {
              hilos[i].start();
          }
  
          for (int i = 0; i < M+M; ++i) {
              hilos[i].join();
          }

          System.out.println("n = " + n.get());
          
       }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
}
