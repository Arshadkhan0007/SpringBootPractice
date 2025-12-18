public class Main implements Runnable{

    private final B lock = new B();
    private int count = 0;

    @Override
    public void run() {
        increment();
    }

    public void increment(){
        System.out.println("Inside run() of class Main");
        System.out.println("This is un-synchronized block of code");
//        synchronized (lock){
//            count++;
//            System.out.println("count has been incremented\n");
//        }
        count++;
        System.out.println("count has been incremented\n");
    }

    public void myMethod(){
        System.out.println("This method isn't synchronized!");
    }

    public int getCount() {
        return count;
    }
}

class A implements Runnable{
    private Main main;

    public A (Main main){
        this.main = main;
    }

    public static void main(String[] args) {
        Main main = new Main();

        B runnableB = new B(main);
        A runnableA = new A(main);

        Thread t1 = new Thread(main);
        Thread t2 = new Thread(runnableA);
        Thread t3 = new Thread(runnableB);

        t1.start();

        t2.start();

        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("count: " + main.getCount() + "\n");
    }

    @Override
    public void run() {
        System.out.println("Inside run() of class A");
        main.increment();
    }
}

class B implements Runnable{

    private Main main;

    public B(){

    }

    public B (Main main){
        this.main = main;
    }

    @Override
    public void run() {
        main.increment();
    }
}