import java.util.HashMap;
import java.util.Map;
public class PARTMANAGERMock implements PARTMANAGER {
    Map<Integer, Integer> itemDetails = new HashMap<Integer, Integer>();
    public void updateQuantity() {
        itemDetails.put(1234, 4);
        itemDetails.put(7777, -1);
    }
    public PARTMANAGERMock() {
        updateQuantity();
    }
    @Override
    public PartResponse SubmitPartForManufactureAndDelivery(int partNumber, int quantity, Deliveryaddress deliveryAddress) {
        if (itemDetails.get(partNumber) == -1) {
            return PartResponse.NO_LONGER_MANUFACTURED;
        } else if (quantity <= itemDetails.get(partNumber)) {
            itemDetails.put(partNumber, itemDetails.get(partNumber) - quantity);
            return PartResponse.SUCCESS;
        } else {
            return PartResponse.OUT_OF_STOCK;
        }
    }
}
