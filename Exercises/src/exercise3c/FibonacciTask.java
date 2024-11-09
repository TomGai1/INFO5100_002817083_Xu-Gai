package exercise3c;

import java.util.concurrent.Callable;

public class FibonacciTask extends Calculator implements Callable<String> {
    private final int position;

    public FibonacciTask(int position) {
        this.position = position;
    }

    @Override
    public String call() {
        long result = fibonacci(position);
        randomDelay();
        return String.format("[%tT] Thread %d (%s): Fibonacci(%d) = %d",
                System.currentTimeMillis(),
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                position,
                result);
    }
}
