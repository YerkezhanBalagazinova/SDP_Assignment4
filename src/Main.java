public class Main {
    public static void main(String[] args) {
        OrderFacade facade = new OrderFacade(10);

        Customer customer1 = new Customer("Anna", "anna@mail.com", 1000000);
        Cart cart1 = new Cart("Laptop", 2, 900000);

        System.out.println(facade.placeOrder("ORD-1", cart1, customer1));
        System.out.println("Stock: " + facade.getAvailableStock());
        System.out.println("Orders: " + facade.getOrderCount());
        System.out.println("Anna balance: " + customer1.getBalance());

        Customer customer2 = new Customer("Alex", "alex@mail.com", 100000);
        Cart cart2 = new Cart("Phone", 1, 300000);

        System.out.println(facade.placeOrder("ORD-2", cart2, customer2));
        System.out.println("Stock after failed payment: " + facade.getAvailableStock());

        Customer customer3 = new Customer("Kate", "kate@mail.com", 500000);
        Cart cart3 = new Cart("Tablet", 1, 200000);

        System.out.println(facade.placeOrder("ORD-3", cart3, customer3));
        System.out.println("Orders: " + facade.getOrderCount());

        System.out.println(facade.cancelOrder("ORD-1"));
        System.out.println("ORD-1 exists: " + facade.hasOrder("ORD-1"));
        System.out.println("Stock after cancellation: " + facade.getAvailableStock());
        System.out.println("Anna balance after refund: " + customer1.getBalance());
        System.out.println("Orders left: " + facade.getOrderCount());
    }
}