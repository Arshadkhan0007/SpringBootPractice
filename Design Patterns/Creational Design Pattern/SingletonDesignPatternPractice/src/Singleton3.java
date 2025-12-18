//Eager Instantiation

public class Singleton3 {

    private static Singleton3 instance = new Singleton3();

    private Singleton3(){}

    public static Singleton3 getInstance(){
        return instance;
    }

    public void display(){
        System.out.println("This is a singleton object that implements eager initialization");
    }
}
