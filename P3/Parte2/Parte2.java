package P3.Parte2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class Parte2 {
    public static void main(String[] args) {
        
        int N = 50;
        int M = 100;
        Storage st = new Storage();
        Semaphore sP = new Semaphore(1);
        Semaphore sC = new Semaphore(0);

        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        ArrayList<Producer> producers = new ArrayList<Producer>();
       
        for(int i = 0; i < M;i++){
            consumers.add(new Consumer(st,sP,sC,N));
            producers.add(new Producer(st,sP,sC,N));
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
