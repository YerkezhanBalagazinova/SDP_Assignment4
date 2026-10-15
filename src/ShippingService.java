public class ShippingService {
    public boolean createShipment(Cart cart) {
        if (cart.getQuantity() <= 0) {
            return false;
        }

        System.out.println("Shipment created for " + cart.getItemName());
        return true;
    }

    public boolean cancelShipment(Cart cart) {
        if (cart.getQuantity() <= 0) {
            return false;
        }

        System.out.println("Shipment cancelled for " + cart.getItemName());
        return true;
    }
}
