import java.util.concurrent.Semaphore;

public class SemaphoresPractice {
    private static final Semaphore SEMAPHORE = new Semaphore(3, true);

    public static void main(String[] args){
        for(int i = 101; i < 107; i++){
            final int USERID = i;
            new Thread(() -> {
                try{
                    System.out.println("User: " + USERID + " is trying to connect");

                    SEMAPHORE.acquire();
                    System.out.println("User: " + USERID + " has acquired connection");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    SEMAPHORE.release();
                    System.out.println("User: " + USERID + " has released connection");
                }
            }).start();
        }
    }
}
