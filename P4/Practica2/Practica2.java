package P4.Practica2;

public class Practica2 {
    private static final int N = 100000 ;

    private static class Entero {
        private int n;
        public Entero (int n) {
            this.n = n;
        }
        private synchronized void increment() {
            n++;
        }
        private synchronized void decrement() {
            n--;
        }
        private int get() {
            return n;
        }
    }

    private static class Suma extends Thread {
        private Entero n;
        public Suma (Entero n) {
            this.n = n;
        }
        public void run() {
            for (int i=0; i<N; i++) {
                n.increment();
            }
        }
    }

    private static class Resta extends Thread {
        private Entero n;
        public Resta (Entero n) {
            this.n = n;
        }
        public  void run() {
            for (int i=0; i<N; i++) {
              n.decrement();
            }
        }
    }
  
  
    public static void main(String[] args) {
      try {
        Entero n = new Entero(0);

        Suma p0 = new Suma(n);
        Resta p1 = new Resta(n);

        p0.start();
        p1.start();
        p0.join();
        p1.join();
        
        System.out.println("n = " + n.get());
        
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
