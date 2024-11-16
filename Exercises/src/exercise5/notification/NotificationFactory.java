package exercise5.notification;

public class NotificationFactory {
    public static NotificationService createNotificationService(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EmailNotificationService();
        } else if (type.equalsIgnoreCase("sms")) {
            return new SMSNotificationService();
        }
        throw new IllegalArgumentException("Unknown notification type");
    }
}
