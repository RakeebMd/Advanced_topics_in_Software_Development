import java.util.ArrayList;

public class MainTest {
    ConstantsTest testCasesValidation = new ConstantsTest();

    public void validateObjectsTest() {
        Main test = new Main();

        String errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.dealerIDCheck));
        if (errorMessageTest.equalsIgnoreCase("Not authorized")) {
            System.out.println("PASS --> Dealer ID Check");
        } else {
            System.out.println("FAIL --> Dealer ID Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.dealerAccessCheck));
        if (errorMessageTest.equalsIgnoreCase("Not authorized")) {
            System.out.println("PASS --> Dealer Access Check");
        } else {
            System.out.println("FAIL --> Dealer Access Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.dealerIDAndAccessCheck));
        if (errorMessageTest.equalsIgnoreCase("Not authorized")) {
            System.out.println("PASS --> Dealer ID and Access Check");
        } else {
            System.out.println("FAIL --> Dealer ID and Access Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.orderItemCheck));
        if (errorMessageTest.equalsIgnoreCase("Invalid order item list")) {
            System.out.println("PASS --> Invalid Order Items List Check");
        } else {
            System.out.println("FAIL --> Invalid Order Items List Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.orderItemEntryCheck));
        if (errorMessageTest.equalsIgnoreCase("Invalid order item entry")) {
            System.out.println("PASS --> Invalid Order Item Entry Check");
        } else {
            System.out.println("FAIL --> Invalid Order Item Entry Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.streetCheck));
        if (errorMessageTest.equalsIgnoreCase("Invalid delivery address")) {
            System.out.println("PASS --> Street value Check");
        } else {
            System.out.println("FAIL --> Street value Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.cityCheck));
        if (errorMessageTest.equalsIgnoreCase("Invalid delivery address")) {
            System.out.println("PASS --> City value Check");
        } else {
            System.out.println("FAIL --> City value Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.provinceCheck));
        if (errorMessageTest.equalsIgnoreCase("Invalid delivery address")) {
            System.out.println("PASS --> Province value Check");
        } else {
            System.out.println("FAIL --> Province value Check");
        }
        errorMessageTest = test.validateObjects(mockRequestTest(testCasesValidation.postalCheck));
        if (errorMessageTest.equalsIgnoreCase("Invalid delivery address")) {
            System.out.println("PASS --> Postal value Check");
        } else {
            System.out.println("FAIL --> Postal value Check");
        }
    }

    public void validatePartNumberTest() {
        Main test = new Main();
        ArrayList<String> partResponseTest = new ArrayList<String>();
        ArrayList<String> partResponseTest1 = new ArrayList<String>();
        Orders obj = mockRequestTest("");

        for (int iter_test = 0; iter_test < obj.orderitems.item.size(); iter_test++) {
            partResponseTest = test.validatePartNumbers(obj, obj.orderitems.item.get(iter_test));

            if (obj.orderitems.item.get(iter_test).part_number == 1234) {
                if (partResponseTest.get(0).equalsIgnoreCase("success")) {
                    System.out.println("PASS --> Valid Part Number Check");
                } else {
                    System.out.println("FAIL --> Valid Part Number Check");
                }
            }
            if (obj.orderitems.item.get(iter_test).part_number == 5678) {
                if (partResponseTest.get(0).equalsIgnoreCase("failure")) {
                    System.out.println("PASS --> Invalid Part Number Check");
                } else {
                    System.out.println("FAIL --> Invalid Part Number Check");
                }
            }
        }
        for (int iter_test1 = 0; iter_test1 < obj.orderitems.item.size(); iter_test1++) {
            if (obj.orderitems.item.get(iter_test1).part_number == 1234) {
                obj.orderitems.item.get(iter_test1).quantity = 50;
                partResponseTest1 = test.validatePartNumbers(obj, obj.orderitems.item.get(iter_test1));
                if (partResponseTest1.get(1).equalsIgnoreCase("OUT_OF_STOCK")) {
                    System.out.println("PASS --> Quantity Out of Stock Check");
                } else {
                    System.out.println("FAIL --> Quantity Out of Stock Check");
                }
            }
        }
    }

    private Orders mockRequestTest(String conditionTest) {
        Orders ord_test = new Orders();
        Orderitems ord_items_test = new Orderitems();
        Deliveryaddress deliveryaddress_test = new Deliveryaddress();
        Dealer dealer_test = new Dealer();
        Item item1_test = new Item();
        Item item2_test = new Item();
        ArrayList<Item> items_test = new ArrayList<Item>();

        deliveryaddress_test.name = "Mrs. Jane Smith";
        deliveryaddress_test.street = "35 Streetname";
        if (conditionTest.equalsIgnoreCase(testCasesValidation.streetCheck)) {
            deliveryaddress_test.street = "";
        }
        deliveryaddress_test.city = "Halifax";
        if (conditionTest.equalsIgnoreCase(testCasesValidation.cityCheck)) {
            deliveryaddress_test.city = "";
        }
        deliveryaddress_test.province = "NS";
        if (conditionTest.equalsIgnoreCase(testCasesValidation.provinceCheck)) {
            deliveryaddress_test.province = "";
        }
        deliveryaddress_test.postal_code = "B2T1A4";
        if (conditionTest.equalsIgnoreCase(testCasesValidation.postalCheck)) {
            deliveryaddress_test.postal_code = "";
        }

        dealer_test.dealer_ID = "XXX-1234-ABCD-1234";
        dealer_test.dealer_access_key = "kkklas8882kk23nllfjj88290";
        if (conditionTest.equalsIgnoreCase(testCasesValidation.dealerIDCheck)) {
            dealer_test.dealer_ID = "";
            dealer_test.dealer_access_key = "kkklas8882kk23nllfjj88290";
        } else if (conditionTest.equalsIgnoreCase(testCasesValidation.dealerAccessCheck)) {
            dealer_test.dealer_ID = "XXX-1234-ABCD-1234";
            dealer_test.dealer_access_key = "";
        } else if (conditionTest.equalsIgnoreCase(testCasesValidation.dealerIDAndAccessCheck)) {
            dealer_test.dealer_ID = "";
            dealer_test.dealer_access_key = "";
        }

        item1_test.part_number = 1234;
        item1_test.quantity = 2;

        item2_test.part_number = 5678;
        item2_test.quantity = 25;

        if (conditionTest.equalsIgnoreCase(testCasesValidation.orderItemEntryCheck)) {
            item1_test.quantity = 0;
            item2_test.quantity = 0;
        }
        items_test.add(item1_test);
        items_test.add(item2_test);
        if (conditionTest.equalsIgnoreCase(testCasesValidation.orderItemCheck)) {
            items_test.clear();
        }
        ord_items_test.item = items_test;
        ord_test.dealer = dealer_test;
        ord_test.orderitems = ord_items_test;
        ord_test.deliveryaddress = deliveryaddress_test;

        return ord_test;
    }
}

