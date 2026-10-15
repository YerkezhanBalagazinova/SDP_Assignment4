public class Cart {
    private String itemName;
    private int quantity;
    private double totalAmount;

    public Cart(String itemName, int quantity, double totalAmount) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}