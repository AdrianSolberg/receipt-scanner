package core;

/**
 * Class for representing an item
 */
public class Item {
    
    private String name;
    private double price;

    public Item() {
        name = "Default item";
        price = 100.0;
    }

    public Item(String name, double price) {
        if (name == "" || name == null) {
            throw new IllegalArgumentException("The item must have a name");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
