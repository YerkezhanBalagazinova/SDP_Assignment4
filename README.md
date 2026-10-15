# SDP_Assignment4
# Assignment 4 – Facade Pattern

**Student:** Yerkezhan Balagazinova  
**Group:** SE-2527  
**Topic:** Option B – Online Order Processing  
**Language:** Java 17  

## About

For this assignment, I chose an online order processing system.

The main idea of the project is that placing an order requires several different services to work together. The system needs to reserve the product, process the payment, create a shipment, and send a notification to the customer.

If the client had to call all of these services directly, it would also have to know the correct order of the operations and what to do when something fails.

To make this simpler, I used the Facade pattern.

The `OrderFacade` gives the client two simple methods:

```java
placeOrder(String orderId, Cart cart, Customer customer)
cancelOrder(String orderId)
```

The client only calls these methods, while the Facade handles the rest of the process internally.

## Project Structure

```text
src/
├── Main.java
├── OrderFacade.java
├── InventoryService.java
├── PaymentProcessor.java
├── ShippingService.java
├── NotificationService.java
├── Order.java
├── Cart.java
└── Customer.java
```

## How It Works

The main subsystem classes are:

- `InventoryService` – checks stock, reserves items, and releases them if needed
- `PaymentProcessor` – processes payments and refunds
- `ShippingService` – creates and cancels shipments
- `NotificationService` – sends order confirmation and cancellation messages

`OrderFacade` connects all of them together.

For example, when the client calls:

```java
facade.placeOrder("ORD-1", cart, customer);
```

the Facade performs the steps in this order:

```text
Reserve stock
↓
Process payment
↓
Create shipment
↓
Save order
↓
Send confirmation
```

The client does not need to know how these steps are implemented.

## Rollback

The Facade also handles failures.

For example, if the stock was already reserved but the payment fails, the stock is released again:

```text
Stock reserved
↓
Payment failed
↓
Stock released
```

If the payment succeeds but shipment creation fails, the Facade returns the money and also releases the stock.

This keeps the system in a correct state even if one part of the order process fails.

## Cancelling an Order

Successful orders are stored by their order ID.

For example:

```text
ORD-1
ORD-2
ORD-3
```

Because of this, a specific order can be cancelled:

```java
facade.cancelOrder("ORD-1");
```

The Facade then cancels the shipment, refunds the customer, returns the items to stock, sends a cancellation message, and removes the order.

Again, the client only makes one call instead of coordinating all services itself.

## Why I Used Facade

I used Facade because the order processing system has several classes that need to work together.

Without the Facade, the client would have to do something like this:

```java
inventoryService.reserve(cart);
paymentProcessor.processPayment(customer, amount);
shippingService.createShipment(cart);
notificationService.sendOrderConfirmation(customer, orderId);
```

With the Facade, the client only needs:

```java
facade.placeOrder(orderId, cart, customer);
```

This makes the client code shorter and easier to understand.

## Clean Code

I also tried to keep the code simple and separated by responsibility.

For example:

`InventoryService` only works with stock.

`PaymentProcessor` only works with payments.

`ShippingService` only works with delivery.

`NotificationService` only works with customer messages.

The `OrderFacade` coordinates these services but does not duplicate their internal logic.

I also used clear method names such as:

```java
reserve()
processPayment()
createShipment()
sendOrderConfirmation()
cancelOrder()
```

so it is easier to understand what each part of the program does.

## Example Output

A successful order can look like this:

```text
Shipment created for Laptop
Confirmation for ORD-1 sent to anna@mail.com
Order ORD-1 placed successfully.
```

If the payment fails:

```text
Order failed: payment declined. Stock released.
```

If an order is cancelled:

```text
Shipment cancelled for Laptop
Cancellation for ORD-1 sent to anna@mail.com
Order ORD-1 cancelled successfully.
```
## How to Run

Open the project in IntelliJ IDEA and run `Main.java`.


## UML

The UML diagram is included in the report.

The main structure is:

```text
              Main
               |
               v
          OrderFacade
       /      |      |       \
      v       v      v        v
Inventory  Payment Shipping Notification
 Service  Processor Service    Service
```

`Main` works with the Facade, and the Facade coordinates the subsystem classes.

## GitHub

Repository: **[insert your GitHub repository link here]**
