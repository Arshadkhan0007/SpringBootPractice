public class Main{

    public static void printMessage(){
        for(int i = 0; i < 5; i++){
            System.out.println("Hello from lambda!");
        }
    }

    public static void main(String[] args){

//        for(int i = 0; i < 20; i++) {
//            MyRunnable r1 = new MyRunnable();
//            Thread t1 = new Thread(r1);
//            Thread t2 = new Thread(r1);
//
//            t1.start();
//            t2.start();
//
//            try {
//                t1.join();
//                t2.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            System.out.println(Counter.getCount());
//            Counter.setCount(0);
//        }

        for(int i = 0; i < 15; i++) {
            MyRunnable1 r1 = new MyRunnable1();
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r1);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println(Counter1.getCount());
            Counter1.setCount(0);
        }
    }
}

//Creating threads by extending thread class
class MyThread extends Thread{
    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("Hello from Thread!");
        }
    }
}

//Creating threads by implementing runnable interface
class MyRunnable implements Runnable{
    Counter counter = new Counter();
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            counter.increment();
        }
    }
}

class MyRunnable1 implements Runnable{
    Counter1 counter1 = new Counter1();
    @Override
    public void run(){
        for(int i = 0; i < 1000; i++){
            counter1.increment();
        }
    }
}

class Counter{
    private static int count = 0;

    public synchronized void increment(){
        count++;
    }

    public static int getCount(){
        return count;
    }
    public static void setCount(int i) {
        count = i;
    }
}

class Counter1{
    private static int count = 0;
    private final Object lock = new Object();

    public void increment(){
        synchronized (lock){
            count++;
        }
    }

    public static int getCount(){
        return count;
    }

    public static void setCount(int i){
        count = i;
    }
}