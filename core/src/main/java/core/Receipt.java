package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Class for representing a receipt
 */
public class Receipt implements Iterable<Item> {
    
    private List<Item> receipt;
    private Date date;

    public Receipt(List<Item> receipt) {
        if (receipt == null) {
            throw new IllegalArgumentException("The list can not be null");
        }
        this.receipt = receipt;
        this.date = new Date();
    }

    public Receipt() {
        this(new ArrayList<>());
    }

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The name can not be null");
        }
        receipt.add(item);
    }
    
    public Date getDate() {
        return date;
    }

    /**
     * Sums up all items in the receipt and returns the total
     * 
     * @return Double with the total price of the receipt
     */
    public double getTotal() {
        return receipt.stream().mapToDouble(i -> i.getPrice()).sum();
    }


    @Override
    public Iterator<Item> iterator() {
        return receipt.iterator();
    }
}
