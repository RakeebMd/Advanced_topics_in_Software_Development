package InterfaceSegregationPrinciple.good;

public class Ostrich implements  IBirdsFood,IOviporousBirds{

    @Override
    public void layEggs() {

        System.out.println("Ostrich lays eggs");
    }
    @Override
    public void eatFood() {

        System.out.println("Ostrich eats food");
    }
}
