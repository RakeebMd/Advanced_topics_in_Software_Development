package InterfaceSegregationPrinciple.bad;

public class Ostrich implements IBirds {

    @Override
    public void fly() {
        //Not supported method
        System.out.println("fly method cannot be implemented by Ostrich");
    }
    @Override
    public void layEggs() {

        System.out.println("Ostrich lays eggs");
    }
    @Override
    public void eatFood() {

        System.out.println("Ostrich eats food");
    }

}
