package ru.serdyuk.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class FifoMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();
    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);
        //Block while not first in queue or cannot acquire lock
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            if (Thread.interrupted()) //ignore interrupts while waiting
                wasInterrupted = true;
        }
        waiters.remove();
        if (wasInterrupted) // reassert interrupt status on exit
            current.interrupt();
    }

    public void unLock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
