public class Order {
    private String id;
    private Cart cart;
    private Customer customer;

    public Order(String id, Cart cart, Customer customer) {
        this.id = id;
        this.cart = cart;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public Customer getCustomer() {
        return customer;
    }
}