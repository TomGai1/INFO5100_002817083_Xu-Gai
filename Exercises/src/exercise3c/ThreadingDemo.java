package exercise3c;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadingDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Part A: Traditional Threading
        System.out.println("Part A: Starting traditional threading approach...\n");

        Thread primeThread = new Thread(new PrimeCalculator(), "PrimeCalculator");
        Thread fibonacciThread = new Thread(new FibonacciCalculator(), "FibonacciCalculator");
        Thread factorialThread = new Thread(new FactorialCalculator(), "FactorialCalculator");

        primeThread.start();
        fibonacciThread.start();
        factorialThread.start();

        primeThread.join();
        fibonacciThread.join();
        factorialThread.join();

        // Part B: Thread Pool
        System.out.println("\nPart B: Starting thread pool approach...\n");

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = new ArrayList<>();

        // Submit prime number tasks
        for (int i = 1; i <= 25; i++) {
            futures.add(executor.submit(new PrimeTask(i)));
        }

        // Submit Fibonacci tasks
        for (int i = 0; i < 50; i++) {
            futures.add(executor.submit(new FibonacciTask(i)));
        }

        // Submit factorial tasks
        for (int i = 0; i < 50; i++) {
            futures.add(executor.submit(new FactorialTask(i)));
        }

        // Get results
        for (Future<String> future : futures) {
            String result = future.get();
            if (result != null) {
                System.out.println(result);
            }
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}


