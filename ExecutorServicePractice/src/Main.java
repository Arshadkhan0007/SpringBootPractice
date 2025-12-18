import java.util.concurrent.*;

public class Main{
    public static void main(String[] args) {
        //1. Create Thread Pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //2. Submit a Callable task that returns a value
        Callable<String> tasks = () -> {
            System.out.println("Task running in thread: " + Thread.currentThread().getName());
            Thread.sleep(3000); //simulate work
            return "Task completed successfully!";
        };

        Future<String> future = executorService.submit(tasks);
        System.out.println("Task submitted waiting for the result...");

        try{

            //3. Get result from future object
            String result  = future.get();
            System.out.println("Result from task: " + result);

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        System.out.println("Main Thread Finished");

    }
}