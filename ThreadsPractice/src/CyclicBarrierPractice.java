import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierPractice {
    public static void main(String[] args){
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Barrier tripped(all parties have called await()), proceed to the next step");
        });

        new Thread(new Workers("Worker-1", barrier)).start();
        new Thread(new Workers("Worker-2", barrier)).start();
        new Thread(new Workers("Worker-3", barrier)).start();
    }
}

class Workers implements Runnable{
    private String name;
    private CyclicBarrier barrier;

    public Workers(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }

    @Override
    public void run(){
        System.out.println(name + " is performing it's first task");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }

        System.out.println(name + " has completed performing it's first task and is about to call await()");
        try {
            barrier.await();
        } catch (BrokenBarrierException | InterruptedException ex){
            throw new RuntimeException(ex);
        }

        System.out.println(name + " is performing it's second task");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}
