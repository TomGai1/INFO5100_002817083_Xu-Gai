package exercise3c;

public class FibonacciCalculator extends Calculator implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            long result = fibonacci(i);
            System.out.printf("[%tT] Thread %d (%s): Fibonacci(%d) = %d%n",
                    System.currentTimeMillis(),
                    Thread.currentThread().getId(),
                    Thread.currentThread().getName(),
                    i,
                    result);
            randomDelay();
        }
    }
}

