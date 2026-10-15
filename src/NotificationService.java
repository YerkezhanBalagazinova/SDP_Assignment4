public class NotificationService {
    private String lastNotification;

    public boolean sendOrderConfirmation(Customer customer, String orderId) {
        if (!isEmailValid(customer.getEmail())) {
            return false;
        }

        lastNotification = "Confirmation for " + orderId + " sent to " + customer.getEmail();

        System.out.println(lastNotification);
        return true;
    }

    public boolean sendCancellation(Customer customer, String orderId) {
        if (!isEmailValid(customer.getEmail())) {
            return false;
        }

        lastNotification ="Cancellation for " + orderId + " sent to " + customer.getEmail();

        System.out.println(lastNotification);
        return true;
    }

    private boolean isEmailValid(String email) {
        return email != null && email.contains("@");
    }

    public String getLastNotification() {
        return lastNotification;
    }
}
