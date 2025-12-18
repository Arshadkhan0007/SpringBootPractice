public class WaitMethodPractice {
    public static int bullets = 40;

    public synchronized void fire(int noOfBulletsToFire){
        for(int i = 1; i <= noOfBulletsToFire; i++){
            if(bullets == 0) {
                System.out.println("\n" + (i - 1) + " bullets have been fired, remaining " + bullets + " bullets");

                System.out.println("waiting for the gun to be reloaded, wait() method is being called");
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            bullets--;
            System.out.print(i + "pew! ");
        }
    }

    public synchronized void reload(){
        bullets = 40;
        try{
            System.out.println("Gun is being reloaded");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Gun has been reloaded and notify() is being called");
        notify();
    }

    public static void main(String[] args) {

        WaitMethodPractice obj = new WaitMethodPractice();

        Thread fire = new Thread(() -> obj.fire(60));
        fire.setPriority(10);
        Thread reload = new Thread(obj::reload);
        reload.setPriority(5);

        fire.start();
        reload.start();

        try {
            fire.join();
            reload.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}


