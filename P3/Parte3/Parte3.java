package P3.Parte3;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class Parte3 {
    public static void main(String[] args) {
        
        final int maxProducts = 10;

        int N = 5;
        int M = 10;
        Storage st = new Storage();
        Semaphore empty = new Semaphore(maxProducts);
        Semaphore full = new Semaphore(0);
        Semaphore sP = new Semaphore(1);
        Semaphore sC = new Semaphore(0);

        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        ArrayList<Producer> producers = new ArrayList<Producer>();
       
        for(int i = 0; i < M;i++){
            consumers.add(new Consumer(st,sP,sC,N,empty,full));
            producers.add(new Producer(st,sP,sC,N,empty,full));
        }

        for(int i = 0; i < M;i++){
            consumers.get(i).start();
            producers.get(i).start();
        }

        for(int i = 0; i < M;i++){
            try {
                consumers.get(i).join();
                producers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Size: "+st.size());

    }

}
