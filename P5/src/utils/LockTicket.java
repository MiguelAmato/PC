package utils;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket {

    private AtomicInteger nextTicket;
    private AtomicInteger currentTicket;

    public LockTicket() {
        this.nextTicket = new AtomicInteger(0);
        this.currentTicket = new AtomicInteger(0);
    }

    public void takeLock() {
        int myTicket = nextTicket.getAndIncrement();
        while (myTicket != currentTicket.get());
    }

    public void releaseLock() {
        currentTicket.getAndIncrement();
    }

}

