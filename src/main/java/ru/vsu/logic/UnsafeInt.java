package ru.vsu.logic;

public class UnsafeInt implements Runnable {
    public static final int ITERATIONS_AMOUNT = 100;

    public static final int MILLIS = 10;
    private long numb;

    public long getNumb() {
        return numb;
    }

    public void incrementNumb() {
        /*try {
            Thread.sleep(MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        numb++;
    }

    @Override
    public void run() {
        for (int i = 0; i < ITERATIONS_AMOUNT; i++) {
            this.incrementNumb();/*
            System.out.printf("Value for Thread %s after increment = %d%n", Thread.currentThread().getName(), this.getNumb());
        */
        }
    }
}
