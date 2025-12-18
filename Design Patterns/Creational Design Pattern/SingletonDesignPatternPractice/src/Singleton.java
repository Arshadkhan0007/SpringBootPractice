public class Singleton {

    //Static member
    private static Singleton instance;

    //Private constructor
    private Singleton() {
        //Initialization code
        System.out.println("Singleton is initiated");
    }

    //Static factory method
    //If instance exists return it, or else create one are return it
    private static Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
}
