public class PaymentProcessor {

    public boolean processPayment(Customer customer, double amount) {
        if (amount <= 0 || customer.getBalance() < amount) {
            return false;
        }

        customer.deductBalance(amount);
        return true;
    }

    public void refund(Customer customer, double amount) {
        if (amount > 0) {
            customer.addBalance(amount);
        }
    }
}