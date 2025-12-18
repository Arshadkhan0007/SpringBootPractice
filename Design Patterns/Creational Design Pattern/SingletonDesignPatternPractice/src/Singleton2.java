//Thread safe implementation using synchronized method

public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2(){}

    public static synchronized Singleton2 getInstance(){
        if(instance == null)
            instance = new Singleton2();
        return instance;
    }

    public void display(){
        System.out.println("This is a singleton object, created by implementing a synchronized method");
    }
}
