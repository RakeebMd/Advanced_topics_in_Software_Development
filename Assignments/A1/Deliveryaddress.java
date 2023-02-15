public class Deliveryaddress {
    public String name;
    public String street;
    public String city;
    public String province;
    public String postal_code;

    @Override
    public String toString() {
        return "Deliveryaddress{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postal_code='" + postal_code + '\'' +
                '}';
    }
}