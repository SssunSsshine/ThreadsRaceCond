package ru.vsu.logic;

public class SynchSafeInt extends UnsafeInt {
    @Override
    public synchronized void incrementNumb() {
        super.incrementNumb();
    }
}
