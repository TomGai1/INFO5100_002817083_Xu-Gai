package exercise5.observer;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;
    private String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void attach(OrderObserver observer) {
        observers.add(observer);
    }

    public void detach(OrderObserver observer) {
        observers.remove(observer);
    }

    public void updateStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(status);
        }
    }
}
