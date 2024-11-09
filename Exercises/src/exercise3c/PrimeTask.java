package exercise3c;

import java.util.concurrent.Callable;

public class PrimeTask extends Calculator implements Callable<String> {
    private final int position;

    public PrimeTask(int position) {
        this.position = position;
    }

    @Override
    public String call() {
        int count = 0;
        int num = 2;
        while (count < position) {
            if (isPrime(num)) {
                count++;
                if (count == position) {
                    randomDelay();
                    return String.format("[%tT] Thread %d (%s): %dth prime number is %d",
                            System.currentTimeMillis(),
                            Thread.currentThread().getId(),
                            Thread.currentThread().getName(),
                            position,
                            num);
                }
            }
            num++;
        }
        return null;
    }
}
