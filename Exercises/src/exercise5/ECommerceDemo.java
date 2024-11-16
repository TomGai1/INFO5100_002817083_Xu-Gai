package exercise5;

import exercise5.config.ConfigurationManager;
import exercise5.observer.Customer;
import exercise5.observer.Order;
import exercise5.notification.NotificationService;
import exercise5.notification.NotificationFactory;

public class ECommerceDemo {
    public static void main(String[] args) {
        // Demonstrate Singleton Pattern
        ConfigurationManager config = ConfigurationManager.getInstance();
        config.updateConfig("smtp.newserver.com", "new-api-key-123");

        // Demonstrate Observer Pattern
        Order order = new Order("ORD-001");
        Customer customer1 = new Customer("John Doe", "john@example.com");
        Customer customer2 = new Customer("Jane Smith", "jane@example.com");

        order.attach(customer1);
        order.attach(customer2);

        // Demonstrate Factory Method Pattern
        NotificationService emailService = NotificationFactory.createNotificationService("email");
        NotificationService smsService = NotificationFactory.createNotificationService("sms");

        // Simulate order process
        System.out.println("\n=== Processing Order ORD-001 ===");
        order.updateStatus("CONFIRMED");
        emailService.sendNotification("john@example.com", "Order confirmed!");

        System.out.println("\n=== Updating Order Status ===");
        order.updateStatus("SHIPPED");
        smsService.sendNotification("+1234567890", "Your order has been shipped!");

        // Demonstrate detaching observer
        System.out.println("\n=== Customer 2 unsubscribed from notifications ===");
        order.detach(customer2);
        order.updateStatus("DELIVERED");
    }
}
