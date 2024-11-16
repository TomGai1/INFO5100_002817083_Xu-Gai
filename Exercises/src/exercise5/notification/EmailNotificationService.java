package exercise5.notification;

import exercise5.config.ConfigurationManager;

public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String recipient, String message) {
        ConfigurationManager config = ConfigurationManager.getInstance();
        System.out.println("Sending email via " + config.getSmtpServer() + " to " + recipient + ": " + message);
    }
}
