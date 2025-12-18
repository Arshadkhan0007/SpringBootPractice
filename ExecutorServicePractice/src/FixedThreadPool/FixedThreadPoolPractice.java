package FixedThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolPractice {
    public static void main(String[] args){

        ExecutorService executor = Executors.newFixedThreadPool(2);
        System.out.println("Submitting 5 tasks to fixed thread pool of 3");
        for(int i = 1; i <= 5; i++){
            int taskIndx = i;
            executor.submit(() -> {
                System.out.println("Task: " + taskIndx + " running on " + Thread.currentThread().getName());
                try {
                    Thread.currentThread().sleep(3000);
                    System.out.println("Task: " + taskIndx + " has been completed by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        shutdownAndAwaitTermination(executor);
        System.out.println("All tasks finished!");
    }

    public static void shutdownAndAwaitTermination(ExecutorService pool){
        pool.shutdown();

        try{
            if(!pool.awaitTermination(60, TimeUnit.SECONDS));{
                pool.shutdownNow();
                if(!pool.awaitTermination(60, TimeUnit.SECONDS)){
                    System.err.println("Thread pool did not terminate");
                }
            }

        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
