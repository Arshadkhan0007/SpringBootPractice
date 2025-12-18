package SingleThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorPractice {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.println("Submitting 3 sequential tasks");
        for(int i = 1; i <= 5; i++){
            int taskIndx = i;
            executor.submit(() -> {
                System.out.println("Task: " + taskIndx + " is running on " + Thread.currentThread().getName());
                try{
                    Thread.currentThread().sleep(3000);
                    System.out.println("Task: " + taskIndx + " has been executed by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        shutdownAndAwaitTermination(executor);
        System.out.println("All tasks have been executed Successfully!");
    }

    public static void shutdownAndAwaitTermination(ExecutorService pool){
        pool.shutdown();
        try{
            if(!pool.awaitTermination(60, TimeUnit.SECONDS)){
                pool.shutdownNow();
                if(!pool.awaitTermination(60, TimeUnit.SECONDS)){
                    System.out.println("Pool couldn't be terminated");
                }
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
