package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Class for representing a receipt
 */
public class Receipt implements Iterable<Map.Entry<String, Double>> {
    
    private List<Map.Entry<String, Double>> receipt;

    public Receipt(List<Map.Entry<String, Double>> receipt) {
        if (receipt == null) {
            throw new IllegalArgumentException("The list can not be null");
        }
        this.receipt = receipt;
    }

    public Receipt() {
        this(new ArrayList<>());
    }

    public void addItem(String name, double price) {
        if (name == null) {
            throw new IllegalArgumentException("The name can not be null");
        }
        receipt.add(Map.entry(name, price));
    }

    /**
     * Sums up all items in the receipt and returns the total
     * 
     * @return Double with the total price of the receipt
     */
    public double getTotal() {
        return receipt.stream().mapToDouble(i -> i.getValue()).sum();
    }

    @Override
    public Iterator<Map.Entry<String, Double>> iterator() {
        return receipt.iterator();
    }
}
