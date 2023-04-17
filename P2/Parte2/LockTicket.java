package P2.Parte2;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket {

    private volatile AtomicInteger ticket;
    private volatile int[] turns;
    private int turn;


    public LockTicket(int m) {
        turns = new int[m];
        turns = turns;
        ticket = new AtomicInteger(0);
        turn = 0;
    }

    public void takeLock(int id){
        turns[id] = ticket.getAndIncrement();
        turns = turns;
        while(turns[id] != turn);
    }

    public void releaseLock(){
        turn++;
    }
}
