package ru.vsu.Benchmark;

public class BenchmarkResult {
    private final long millisecond;
    private final String info;

    public BenchmarkResult(long millisecond, String info) {
        this.millisecond = millisecond;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Benchmark{" +
                "time " + millisecond +
                " info " + info + '\'' +
                "}";
    }
}
