public class SugarDecorator extends CoffeeDecorator{
    public SugarDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public double getCost(){
        return super.getCost() + 2;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + ", With sugar";
    }
}
