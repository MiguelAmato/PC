package P1.Parte2;

public class Parte2 {

    private static final int N = 1000;
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


    private static class  Suma extends Thread {
        private Entero n;
        public Suma (Entero n) {
            this.n = n;
        }
        public void run() {
            for (int i = 0; i < N; i++) 
                n.increment();
        }
    }  

    private static class Resta extends Thread {
        private Entero n;
        public Resta (Entero n) {
            this.n = n;
        }
        public void run() {
            for (int i = 0; i < N; i++) 
                n.decrement();
        }
    }              
    
    public static void main(String[] args) {
        Suma[] suma = new Suma[M];
        Resta[] resta = new Resta[M];

        Entero n = new Entero(0);

        for (int i = 0; i < M; i++) {
            suma[i] = new Suma(n);
            resta[i] = new Resta(n);
            suma[i].start();
            resta[i].start();
        }

        try {
            for (int i = 0; i < M; i++) {
                suma[i].join();
                resta[i].join();
            }
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El valor de n es: " + n.get());
    }
}
