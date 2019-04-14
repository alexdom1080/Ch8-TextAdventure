// Item.java
/**
 * Item class holds the description of the item as well as the weight.
 * This class also allows for the information about the item to be obtained and input
 *
 * @author Alexis Dominguez
 * @version 4/8/2019
 */
public class Item
{
    // Variables that hold description and weight.
    private String itemDesc;
    private int itemWeight;
    private String name;

    /**
     * Constructor for objects of class Item
     */
    public Item()
    {
        // initialise instance variables
        itemDesc = "";
        itemWeight = 0;
        name = "";
    }
    
    /**
     * Constructor for objects of the class that takes in parameters.
     */
    public Item(String desc, int weight, String name) {
        itemDesc = desc;
        itemWeight = weight;
        this.name = name;
    }

    /**
     * Returns a desription of the item to the user
     * @return itemString - string description of the item
     */
    public String getItemDesc()
    {
        String itemString = "\tName: " + this.name;
        itemString += "\n\tDescription: ";
        itemString += this.itemDesc + "\n";
        itemString += "\tWeight: " + this.itemWeight + "\n";
        return itemString;
    }
    
    /**
     * Returns item name
     * @return name - the item's name
     */
    public String getItemName() {
        return name;
    }
    
    /**
     * Returns the description of the item
     * @return itemDesc - returns desc of item
     */
    public String getDescOfItem() {
        return itemDesc;
    }
    
    /**
     * Returns the item weight
     * @return itemWeight - returns item weight
     */
    public int getItemWeight() {
        return itemWeight;
    }
    
}
