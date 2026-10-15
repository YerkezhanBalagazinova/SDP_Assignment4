import java.util.HashMap;
import java.util.Map;

public class OrderFacade {
    private final InventoryService inventoryService;
    private final PaymentProcessor paymentProcessor;
    private final ShippingService shippingService;
    private final NotificationService notificationService;
    private final Map<String, Order> orders;

    public OrderFacade(int initialStock) {
        inventoryService = new InventoryService(initialStock);
        paymentProcessor = new PaymentProcessor();
        shippingService = new ShippingService();
        notificationService = new NotificationService();
        orders = new HashMap<>();
    }

    public String placeOrder(String orderId, Cart cart, Customer customer) {
        if (orders.containsKey(orderId)) {
            return "Order failed: order ID already exists.";
        }

        if (!inventoryService.reserve(cart)) {
            return "Order failed: not enough stock.";
        }

        boolean paid = paymentProcessor.processPayment(customer, cart.getTotalAmount()
        );

        if (!paid) {
            inventoryService.release(cart);
            return "Order failed: payment declined. Stock released.";
        }

        boolean shipped = shippingService.createShipment(cart);

        if (!shipped) {
            paymentProcessor.refund(
                    customer,
                    cart.getTotalAmount()
            );
            inventoryService.release(cart);
            return "Order failed: shipment could not be created.";
        }

        Order order = new Order(orderId, cart, customer);
        orders.put(orderId, order);

        boolean notificationSent =
                notificationService.sendOrderConfirmation(
                        customer,
                        orderId
                );

        if (!notificationSent) {
            return "Order placed, but confirmation could not be sent.";
        }

        return "Order " + orderId + " placed successfully.";
    }

    public String cancelOrder(String orderId) {
        Order order = orders.get(orderId);

        if (order == null) {
            return "Order not found.";
        }

        shippingService.cancelShipment(order.getCart());

        paymentProcessor.refund(
                order.getCustomer(),
                order.getCart().getTotalAmount()
        );

        inventoryService.release(order.getCart());

        notificationService.sendCancellation(order.getCustomer(),
                orderId
        );

        orders.remove(orderId);

        return "Order " + orderId + " cancelled successfully.";
    }

    public int getAvailableStock() {
        return inventoryService.getAvailableStock();
    }

    public int getOrderCount() {
        return orders.size();
    }

    public boolean hasOrder(String orderId) {
        return orders.containsKey(orderId);
    }
}
