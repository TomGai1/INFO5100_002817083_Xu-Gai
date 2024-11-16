package exercise5.notification;

import exercise5.config.ConfigurationManager;

public class SMSNotificationService implements NotificationService {
    @Override
    public void sendNotification(String recipient, String message) {
        ConfigurationManager config = ConfigurationManager.getInstance();
        System.out.println("Sending SMS using API key " + config.getApiKey() + " to " + recipient + ": " + message);
    }
}
