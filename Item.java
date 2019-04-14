// Item.java
/**
 * Item class holds the description of the item as well as the weight.
 *
 * @author Alexis Dominguez
 * @version 4/8/2019
 */
public class Item
{
    // Variables that hold description and weight.
    private String itemDesc;
    private int itemWeight;

    /**
     * Constructor for objects of class Item
     */
    public Item()
    {
        // initialise instance variables
        itemDesc = "";
        itemWeight = 0;
    }
    
    /**
     * Constructor for objects of the class that takes in parameters.
     */
    public Item(String desc, int weight) {
        itemDesc = desc;
        itemWeight = weight;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getItemDesc()
    {
        String itemString = "Item Description: ";
        itemString += this.itemDesc + "\n";
        itemString += "Item Weight: " + this.itemWeight;
        return itemString;
    }
}
