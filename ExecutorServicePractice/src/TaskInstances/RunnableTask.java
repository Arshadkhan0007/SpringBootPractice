package TaskInstances;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableTask {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> System.out.println("Hello this is a runnable task, it does not return anything"));
        executor.shutdownNow();
    }
}
