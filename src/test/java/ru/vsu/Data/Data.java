package ru.vsu.Data;

import ru.vsu.logic.LockSafeInt;
import ru.vsu.logic.SynchSafeInt;
import ru.vsu.logic.UnsafeInt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Data {

    public static final Long THREADS_AMOUNT = 5000L;
    public static final UnsafeInt UNSAFE_INT = new UnsafeInt();
    public static final SynchSafeInt SYNCH_SAFE_INT = new SynchSafeInt();
    public static final LockSafeInt LOCK_SAFE_INT = new LockSafeInt();
    public static final AtomicLong ATOMIC_LONG = new AtomicLong();
    public static final List<Thread> THREADS_UNSAFE = initializeThreads(UNSAFE_INT);
    public static final List<Thread> THREAD_SYNCH = initializeThreads(SYNCH_SAFE_INT);
    public static final List<Thread> THREADS_LOCK = initializeThreads(LOCK_SAFE_INT);
    public static final List<Thread> THREADS_ATOMIC_LONG = initializeThreadsAtomicLong(ATOMIC_LONG);

    private static List<Thread> initializeThreads(UnsafeInt integer) {
        List<Thread> threads = new ArrayList<>();
        for (Long i = 0L; i < THREADS_AMOUNT; i++) {
            threads.add(new Thread(integer, i.toString()));
        }
        return threads;
    }

    private static List<Thread> initializeThreadsAtomicLong(AtomicLong atomicLong) {
        List<Thread> threads = new ArrayList<>();
        for (long i = 0L; i < THREADS_AMOUNT; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < UnsafeInt.ITERATIONS_AMOUNT; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }
        return threads;
    }
}
