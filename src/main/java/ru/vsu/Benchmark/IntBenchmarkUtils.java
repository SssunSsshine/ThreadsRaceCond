package ru.vsu.Benchmark;

import ru.vsu.logic.UnsafeInt;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class IntBenchmarkUtils {

    public static final Long THREADS_AMOUNT = 50_000L;

    public static BenchmarkResult runIncrement(UnsafeInt integer) throws InterruptedException {
        String info = String.format("Testing increment of %s. Amount of threads = %d, amount of iterations = %d, ", integer.getClass().getName(), THREADS_AMOUNT, UnsafeInt.ITERATIONS_AMOUNT);
        List<Thread> threads = initializeThreads(integer);
        long startTime = System.currentTimeMillis();
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        NumberFormat numberFormat = NumberFormat.getInstance();
        return new BenchmarkResult(endTime - startTime, String.format("%s value = %s", info, numberFormat.format(integer.getNumb())));
    }

    public static BenchmarkResult runIncrementAtomicLong() throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();
        String info = String.format("Testing increment of %s. Amount of threads = %d, amount of iterations = %d", atomicLong.getClass().getName(), THREADS_AMOUNT, UnsafeInt.ITERATIONS_AMOUNT);
        List<Thread> threads = new ArrayList<>();
        for (long i = 0L; i < THREADS_AMOUNT; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < UnsafeInt.ITERATIONS_AMOUNT; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }
        long startTime = System.currentTimeMillis();
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        NumberFormat numberFormat = NumberFormat.getInstance();
        return new BenchmarkResult(endTime - startTime, String.format("%s value = %s", info, numberFormat.format(atomicLong.get())));

    }

    private static List<Thread> initializeThreads(UnsafeInt integer) {
        List<Thread> threads = new ArrayList<>();
        for (Long i = 0L; i < THREADS_AMOUNT; i++) {
            threads.add(new Thread(integer, i.toString()));
        }
        return threads;
    }
}
