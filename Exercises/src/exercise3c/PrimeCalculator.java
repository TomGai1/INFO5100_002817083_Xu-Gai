package exercise3c;

public class PrimeCalculator extends Calculator implements Runnable {
    @Override
    public void run() {
        int count = 0;
        int num = 2;
        while (count < 25) {
            if (isPrime(num)) {
                System.out.printf("[%tT] Thread %d (%s): %dth prime number is %d%n",
                        System.currentTimeMillis(),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName(),
                        count + 1,
                        num);
                count++;
                randomDelay();
            }
            num++;
        }
    }
}

