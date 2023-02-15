package InterfaceSegregationPrinciple.good;

public class Peacock implements IFlyingBirds,IBirdsFood{
    @Override
    public void fly() {

        System.out.println("Peacock flies");
    }


    @Override
    public void eatFood() {

        System.out.println("Peacock eats food");
    }
}
