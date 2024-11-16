package exercise5.config;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private String smtpServer;
    private String apiKey;

    private ConfigurationManager() {
        // Default values
        this.smtpServer = "smtp.company.com";
        this.apiKey = "default-api-key";
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getSmtpServer() { return smtpServer; }
    public String getApiKey() { return apiKey; }
    public void updateConfig(String smtpServer, String apiKey) {
        this.smtpServer = smtpServer;
        this.apiKey = apiKey;
    }
}
