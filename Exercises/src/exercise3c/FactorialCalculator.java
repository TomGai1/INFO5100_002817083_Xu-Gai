package exercise3c;

import java.math.BigInteger;

public class FactorialCalculator extends Calculator implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            BigInteger result = factorial(i);
            System.out.printf("[%tT] Thread %d (%s): Factorial(%d) = %s%n",
                    System.currentTimeMillis(),
                    Thread.currentThread().getId(),
                    Thread.currentThread().getName(),
                    i,
                    result.toString());
            randomDelay();
        }
    }
}
