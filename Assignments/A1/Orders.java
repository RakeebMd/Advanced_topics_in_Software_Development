public class Orders {
        public Dealer dealer;
        public Orderitems orderitems;
        public Deliveryaddress deliveryaddress;

        @Override
        public String toString() {
                return "Orders{" +
                        "dealer=" + dealer +
                        ", orderitems=" + orderitems +
                        ", deliveryaddress=" + deliveryaddress +
                        '}';
        }
}