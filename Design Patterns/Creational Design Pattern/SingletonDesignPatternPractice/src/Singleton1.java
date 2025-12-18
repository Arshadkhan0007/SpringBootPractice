//Classic (non - thread safe) implementation

public class Singleton1 {

    private static Singleton1 instance;

    private Singleton1() {}

    public static Singleton1 getInstance(){
        if(instance == null)
            instance = new Singleton1();
        return instance;
    }

    public void display(){
        System.out.println("This is a singleton object, created using classic (non-thread safe) implementation");
    }
}
