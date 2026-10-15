public class InventoryService {
    private int availableStock;

    public InventoryService(int availableStock) {
        this.availableStock = availableStock;
    }

    public boolean isAvailable(Cart cart) {
        return cart.getQuantity() > 0 && availableStock >= cart.getQuantity();
    }

    public boolean reserve(Cart cart) {
        if (!isAvailable(cart)) {
            return false;
        }

        availableStock -= cart.getQuantity();
        return true;
    }

    public void release(Cart cart) {
        availableStock += cart.getQuantity();
    }

    public int getAvailableStock() {
        return availableStock;
    }
}
