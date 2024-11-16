package exercise5.observer;

public class Customer implements OrderObserver {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public void update(String orderStatus) {
        System.out.println("Notification sent to " + name + " (" + email + "): Your order status is " + orderStatus);
    }
}
