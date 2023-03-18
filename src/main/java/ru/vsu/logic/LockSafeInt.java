package ru.vsu.logic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSafeInt extends UnsafeInt {
    private final Lock lock;

    public LockSafeInt() {
        lock = new ReentrantLock();
    }

    @Override
    public void incrementNumb() {
        lock.lock();
        super.incrementNumb();
        lock.unlock();

    }
}
