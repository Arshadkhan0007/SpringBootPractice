package TaskInstances;

import java.util.concurrent.*;

public class CallableTask {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> futureResult = executor.submit(() -> {
            System.out.println("This is an Callable task, it returns something");
            return 100;
        });

        if(!futureResult.isDone()){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(futureResult.isDone()){
            try {
                Integer result = futureResult.get();
                System.out.println("Result: " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdownNow();
    }
}
