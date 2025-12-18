package ScheduledThreadPool;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolPractice {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        System.out.println("Scheduling a one-shot to run in 2 seconds. Current time: " + LocalDateTime.now());
        executor.schedule(() -> {
            System.out.println("One-shot task executed at " + LocalDateTime.now());
        }, 2, TimeUnit.SECONDS);

        System.out.println("Scheduling a repeating task to run every 3 seconds");
        Runnable repeatingTask = () -> System.out.println("Repeating task running at " + LocalDateTime.now());
        executor.scheduleAtFixedRate(repeatingTask, 1, 3, TimeUnit.SECONDS);

        Thread.sleep(10000); //Let the main thread be active for a while to see scheduled threads in action

        shutdownAndAwaitTermination(executor);
        System.out.println("Scheduler Finished!!");
    }

    public static void shutdownAndAwaitTermination(ExecutorService pool){
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.out.println("Thread pool couldn't be terminated");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
