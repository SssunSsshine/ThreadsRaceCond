package ru.vsu.Test;

import org.junit.jupiter.api.Test;
import ru.vsu.logic.LockSafeInt;
import ru.vsu.logic.SynchSafeInt;
import ru.vsu.logic.UnsafeInt;

import static org.junit.jupiter.api.Assertions.*;
import static ru.vsu.Data.Data.*;

public class ThreadTest {

    @Test
    public void incrementUnsafeInt() throws InterruptedException {
        THREADS_UNSAFE.forEach(Thread::start);
        for (Thread thread : THREADS_UNSAFE) {
            thread.join();
        }
        assertTrue(THREADS_AMOUNT * SynchSafeInt.ITERATIONS_AMOUNT >= UNSAFE_INT.getNumb());
    }
    @Test
    public void incrementLockSafeInt() throws InterruptedException {
        THREADS_LOCK.forEach(Thread::start);
        for (Thread thread : THREADS_LOCK) {
            thread.join();
        }
        assertEquals(THREADS_AMOUNT * LockSafeInt.ITERATIONS_AMOUNT, LOCK_SAFE_INT.getNumb());
    }
    @Test
    public void incrementSynchSafeInt() throws InterruptedException {
        THREAD_SYNCH.forEach(Thread::start);
        for (Thread thread : THREAD_SYNCH) {
            thread.join();
        }
        assertEquals(THREADS_AMOUNT * SynchSafeInt.ITERATIONS_AMOUNT, SYNCH_SAFE_INT.getNumb());
    }
    @Test
    public void incrementAtomicLong() throws InterruptedException {
        THREADS_ATOMIC_LONG.forEach(Thread::start);
        for (Thread thread : THREADS_ATOMIC_LONG) {
            thread.join();
        }
        assertEquals(THREADS_AMOUNT * UnsafeInt.ITERATIONS_AMOUNT, ATOMIC_LONG.get());
    }
}
