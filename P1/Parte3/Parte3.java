package P1.Parte3;

public class Parte3 {
    private static final int N = 3;

    private static int m1[][] = new int[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private static int m2[][] = new int[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};

    private class multiplicarFila extends Thread{
        private int fila;
        private int resultado[] = new int[N];

        public multiplicarFila(int fila){
            this.fila = fila;
        }

        public void run(){
            for(int i = 0; i < N; i++){
                resultado[i] = 0;
                for(int j = 0; j < N; j++){
                    resultado[i] += m1[fila][j] * m2[j][i];
                }
            }
        }

        public int[] getResultado(){
            return resultado;
        }
    }

    public static void main(String[] args) {
        Parte3 p3 = new Parte3();
        p3.multiplicarMatrices();
    }

    public void multiplicarMatrices(){
        multiplicarFila[] hilos = new multiplicarFila[N];

        for(int i = 0; i < N; i++){
            hilos[i] = new multiplicarFila(i);
            hilos[i].start();
        }

        for(int i = 0; i < N; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(hilos[i].getResultado()[j] + " ");
            }
            System.out.println();
        }
    }
}