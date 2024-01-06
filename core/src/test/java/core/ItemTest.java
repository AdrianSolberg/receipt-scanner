package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for the class Item
 */
public class ItemTest {
    
    @Test
    public void testItem() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Item(null, 10.0), "Should not be able to have name null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Item("", 10.0), "Should not be able to have an empty name");
        Assertions.assertDoesNotThrow(() -> new Item("Discount", -10.0), "Should be able to have a negative price (discounts)");

        Item item = new Item("Item", 100.0);
        Assertions.assertEquals("Item", item.getName(), "The name should be 'item'");
        Assertions.assertEquals(100.0, item.getPrice(), "The price should be 100.0");
    }
}
