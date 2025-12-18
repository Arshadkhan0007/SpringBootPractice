import java.util.concurrent.CountDownLatch;

public class CountDownLatchPractice {
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(new Worker("Worker-1", latch)).start();
        new Thread(new Worker("Worker-2", latch)).start();
        new Thread(new Worker("Worker-3", latch)).start();

        System.out.println("Main thread has called await() method and is now waiting for workers to finish their tasks");

        try {
            latch.await();
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }

        System.out.println("All workers have finished their task, main thread continues");
    }
}

class Worker implements Runnable{
    private String name;
    private CountDownLatch latch;

    public Worker(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run(){
        System.out.println(name + " is performing it's task!");

        try{
            Thread.sleep(5000);
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }

        System.out.println(name + " has finished performing it's task");
        latch.countDown();
    }
}
