package exercise3c;

import java.util.Random;
import java.math.BigInteger;

public class Calculator {
    protected Random random = new Random();

    protected void randomDelay() {
        try {
            Thread.sleep(100 + random.nextInt(401));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    protected BigInteger factorial(int n) {
        if (n <= 1) return BigInteger.ONE;
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    protected long fibonacci(int n) {
        if (n <= 1) return n;
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}

