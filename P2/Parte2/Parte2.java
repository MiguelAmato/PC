package P2.Parte2;

public class Parte2 {
    private static final int N = 10;
    private static final int M = 10;

    public static class Entero {
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
        private LockBakery lock;
        public Hilo (int id, Entero n, LockBakery lock) {
            this.id = id;
            this.n = n;
            this.lock = lock;
        }
        public void run() {
            for (int i = 0; i < N; i++) {
                lock.takeLock(id);
                //System.out.println(id+": in CS "+i);
                if (id % 2 == 0) {
                    n.increment();
                }
                else {
                    n.decrement();
                }
                //System.out.println(id+": done CS"); // UNLOCK
                lock.releaseLock(id);
            }
        }
    }
  
    public static void main(String[] args) {
      try {
        Entero n = new Entero(0);
        LockBakery lock = new LockBakery(M);

        Hilo[] hilos = new Hilo[M];

        for (int i = 0; i < M; ++i) {
            hilos[i] = new Hilo(i, n, lock);
        }

        for (int i = 0; i < M; ++i) {
            hilos[i].start();
        }

        for (int i = 0; i < M; ++i) {
            hilos[i].join();
        }
        /* 
        Suma[] suma = new Suma[M];
        Resta[] resta = new Resta[M];

        for (int i=0; i<M; i++) {
          suma[i] = new Suma(i, n, lock);
          resta[i] = new Resta(i, n, lock);
        }

        for (int i=M; i<M; i++) {
          suma[i].start();
          resta[i].start();
        }

        for (int i=0; i<M; i++) {
          suma[i].join();
          resta[i].join();
        }
        */
        System.out.println("n = " + n.get());
        
     }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
