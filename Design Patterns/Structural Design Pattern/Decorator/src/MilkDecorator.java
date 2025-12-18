public class MilkDecorator extends CoffeeDecorator{
    public MilkDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public double getCost(){
        return super.getCost() + 5;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + ", With milk";
    }

    public String getMilkContent(){
        return "40 Grams";
    }
}
