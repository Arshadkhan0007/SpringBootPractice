public class SUVFactory extends CarFactory{
    @Override
    public Car createCar() {
        return new SUV();
    }
}
