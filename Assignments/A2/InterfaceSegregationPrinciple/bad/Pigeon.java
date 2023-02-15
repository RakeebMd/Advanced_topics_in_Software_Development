package InterfaceSegregationPrinciple.bad;

public class Pigeon implements IBirds {
    @Override
    public void fly() {

        System.out.println("Pigeon flies");
    }

    @Override
    public void layEggs() {

        System.out.println("Pigeon lays eggs");
    }

    @Override
    public void eatFood() {
        System.out.println("Pigeon eats food");
    }
}
