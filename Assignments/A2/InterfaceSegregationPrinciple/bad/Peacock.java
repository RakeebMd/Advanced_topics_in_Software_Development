package InterfaceSegregationPrinciple.bad;

public class Peacock implements IBirds{
    @Override
    public void fly() {

        System.out.println("Peacock flies");
    }

    @Override
    public void layEggs() {
        //Not supported method
        System.out.println("Peacock cannot implement  layEggs. Its a male bird");
    }

    @Override
    public void eatFood() {

        System.out.println("Peacock eats food");
    }
}
