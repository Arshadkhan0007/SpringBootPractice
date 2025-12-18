//Inner Static class

public class Singleton5 {

    private Singleton5(){}

    private static class SingletonInner{
        private static final Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance(){
        return SingletonInner.INSTANCE;
    }

    public void display(){
        System.out.println("This is a singleton object, created by implementing a static inner class ensuring thread safety with implicit synchronization and high performance");
    }
}
