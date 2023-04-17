package P4.Practica3;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Parte3 {

  
    public static void main(String[] args) {
      

        final int maxProducts = 10;

        int N = 5;
        int M = 10;
        Storage st = new Storage();

        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        ArrayList<Producer> producers = new ArrayList<Producer>();
       
        for(int i = 0; i < M;i++){
            consumers.add(new Consumer(st,N));
            producers.add(new Producer(st,N));
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
