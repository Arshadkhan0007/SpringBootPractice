public class SimpleCoffee implements Coffee{

    @Override
    public double getCost() {
        return 30;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}
