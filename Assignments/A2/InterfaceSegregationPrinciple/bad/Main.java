package InterfaceSegregationPrinciple.bad;

public class Main {
    public static void main(String[] args) {
        IBirds ostrich = new Ostrich();
        ostrich.fly();
        ostrich.layEggs();
        ostrich.eatFood();

        IBirds peacock = new Peacock();
        peacock.fly();
        peacock.layEggs();
        peacock.eatFood();

        IBirds pigeon = new Pigeon();
        pigeon.fly();
        pigeon.layEggs();
        pigeon.eatFood();

    }
}
