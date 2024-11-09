package exercise3c;

import java.util.concurrent.Callable;
import java.math.BigInteger;

public class FactorialTask extends Calculator implements Callable<String> {
    private final int position;

    public FactorialTask(int position) {
        this.position = position;
    }

    @Override
    public String call() {
        BigInteger result = factorial(position);
        randomDelay();
        return String.format("[%tT] Thread %d (%s): Factorial(%d) = %s",
                System.currentTimeMillis(),
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                position,
                result.toString());
    }
}
