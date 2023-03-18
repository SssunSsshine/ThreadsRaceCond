package ru.vsu;

import ru.vsu.Benchmark.IntBenchmarkUtils;
import ru.vsu.logic.LockSafeInt;
import ru.vsu.logic.SynchSafeInt;
import ru.vsu.logic.UnsafeInt;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(IntBenchmarkUtils.runIncrementAtomicLong());
        System.out.println();

        UnsafeInt unsafeInt = new UnsafeInt();
        System.out.println(IntBenchmarkUtils.runIncrement(unsafeInt));
        System.out.println();

        SynchSafeInt synchSafeInt = new SynchSafeInt();
        System.out.println(IntBenchmarkUtils.runIncrement(synchSafeInt));
        System.out.println();

        LockSafeInt lockSafeInt = new LockSafeInt();
        System.out.println(IntBenchmarkUtils.runIncrement(lockSafeInt));

    }
}