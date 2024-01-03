package core;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for representing a receipt
 */
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

    /**
     * Sums up all items in the receipt and returns the total
     * 
     * @return Double with the total price of the receipt
     */
    public double getTotal() {
        return receipt.values().stream().mapToDouble(v -> (double) v).sum();
    }
}
