public class Main{
    public static void main(String[] args){
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getClass());
        System.out.println("Cost: " + coffee.getCost() +
                "\nDescription: " + coffee.getDescription() + "\n");

        MilkDecorator coffee1 = new MilkDecorator(coffee);
        System.out.println(coffee1.getClass());
        System.out.println("Cost: " + coffee1.getCost() +
                "\nDescription: " + coffee1.getDescription() +
                "\nMilk content: " + coffee1.getMilkContent()
                + "\n");

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getClass());
        System.out.println("Cost: " + coffee.getCost() +
                "\nDescription: " + coffee.getDescription() + "\n");
    }
}