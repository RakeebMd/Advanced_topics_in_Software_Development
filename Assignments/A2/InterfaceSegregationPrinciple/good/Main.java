package InterfaceSegregationPrinciple.good;

public class Main {
    public static void main(String[] args) {

        Ostrich ostrich = new Ostrich();
        ostrich.layEggs();
        ostrich.eatFood();

        Peacock peacock = new Peacock();
        peacock.fly();
        peacock.eatFood();

        Pigeon pigeon = new Pigeon();
        pigeon.fly();
        pigeon.layEggs();
        pigeon.eatFood();
        }
}
