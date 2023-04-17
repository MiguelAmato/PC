package P2.Parte1;

public class Parte1 {

    private static final int N = 100000 ;

    private static volatile boolean[] flag = {false, false};
    private static volatile int turn = 0;

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

    private static class Suma extends Thread {
        private int id;
        private Entero n;
        public Suma (int id, Entero n) {
            this.id = id;
            this.n = n;
        }
        public void run() {
            int j = 1 - id;
            for (int i=0; i<N; i++) {
                System.out.println(id+": want CS"); // LOCK
                flag[id] = true; // 1
                flag = flag;
                turn = j;       // 2
                while (flag[j] && turn == j); // 3
                
                System.out.println(id+": in CS"+i);
                n.increment();
                System.out.println(id+": done CS"); // UNLOCK
                flag[id] = false; // 5
                flag = flag;
            }
        }
    }

    private static class Resta extends Thread {
        int id;
        private Entero n;
        public Resta (int id, Entero n) {
            this.id = id;
            this.n = n;
        }
        public void run() {
            int j = 1 - id;
            for (int i=0; i<N; i++) {
              System.out.println(id+": want CS"); // LOCK
              flag[id] = true; // 1
              flag = flag;
              turn = j;       // 2
              while (flag[j] && turn == j); // 3
              System.out.println(id+": in CS"+i);
              n.decrement();
              System.out.println(id+": done CS"); // UNLOCK
              flag[id] = false; // 5
              flag = flag;
            }
        }
    }
  
    public static void main(String[] args) {
      try {
        Entero n = new Entero(0);

        Suma p0 = new Suma(0, n);
        Resta p1 = new Resta(1, n);

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

