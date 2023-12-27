package core;

import java.util.HashMap;
import java.util.Map;

public class Receipt {
    
    private Map<String, Double> receipt;

    public Receipt(Map<String, Double> receipt) {
        this.receipt = receipt;
    }

    public Receipt() {
        receipt = new HashMap<>();
    }

    public void addItem(String name, double price) {
        receipt.put(name, price);
    }

    public void removeItem(String name) {
        receipt.remove(name);
    }

    public double getTotal() {
        return receipt.values().stream().mapToDouble(v -> (double) v).sum();
    }
}
