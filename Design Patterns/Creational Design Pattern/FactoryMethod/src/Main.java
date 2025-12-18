public class Main{
    public static void main(String[] args){
        CarFactory sedanFactory = new SedanFactory();
        Car sedan = sedanFactory.createCar();
        sedan.drive();

        CarFactory SUVFactory = new SUVFactory();
        Car SUV = SUVFactory.createCar();
        SUV.drive();
    }
}