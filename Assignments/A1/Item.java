public class Item {
    public int part_number;
    public int quantity;

    @Override
    public String toString() {
        return "Item{" +
                "part_number='" + part_number + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}