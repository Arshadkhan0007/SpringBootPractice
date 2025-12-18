public abstract class CoffeeDecorator implements Coffee{
    protected final Coffee decoratedCoffee;

    protected CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public double getCost(){
        return decoratedCoffee.getCost();
    }

    @Override
    public String getDescription(){
        return decoratedCoffee.getDescription();
    }
}
