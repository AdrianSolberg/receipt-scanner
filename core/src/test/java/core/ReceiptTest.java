package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for the class Receipt
 */
public class ReceiptTest {

    @Test
    public void testEmptyContructor() {
        Receipt receipt = new Receipt();
        Assertions.assertDoesNotThrow(() -> receipt.getTotal(), "The receipt should not be null");
        Assertions.assertEquals(0.0, receipt.getTotal(), "The total should be 0.0");
    }

    @Test
    public void testNonEmptyConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Receipt(null), "Should not be able to send in null");
        List<Item> receiptList = new ArrayList<>();
        Item item1 = new Item("Item1", 10.0);
        Item item2 = new Item("Item2", 20.5);
        receiptList.add(item1);
        receiptList.add(item2);
        Receipt receipt = new Receipt(receiptList);
        Assertions.assertEquals(30.5, receipt.getTotal(), "The total should be 30.5");
    }

    @Test
    public void testAddItemAndGetTotal() {
        Receipt receipt = new Receipt();
        receipt.addItem(new Item("Item1", 100.0));
        Assertions.assertEquals(100.0, receipt.getTotal(), "The total should be 100.0");
        receipt.addItem(new Item("Item2", 50.0));
        Assertions.assertEquals(150.0, receipt.getTotal(), "The total should now be 150.0");
        receipt.addItem(new Item("Discount", -10.0));
        Assertions.assertEquals(140.0, receipt.getTotal(), "Should be possible to have negative price");
        Assertions.assertThrows(IllegalArgumentException.class, () -> receipt.addItem(null), "Should not be able to add item null");
    }
    
    @Test
    public void testIterator() {
        Receipt receipt = new Receipt();
        receipt.addItem(new Item("Item1", 100.0));
        receipt.addItem(new Item("Item2", 50.0));
        Iterator<Item> iterator = receipt.iterator();
        Assertions.assertTrue(iterator.hasNext(), "Should have a next item");
        Item item1 = iterator.next();
        Assertions.assertEquals("Item1", item1.getName());
        Assertions.assertTrue(iterator.hasNext(), "Should have a next item");
        Item item2 = iterator.next();
        Assertions.assertEquals("Item2", item2.getName());
    }

}
