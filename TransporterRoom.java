import java.util.Random;
/**
 * Creates the transporter for the player to use.
 *
 * @author Alexis Dominguez
 * @version 4/21/2019
 */
public class TransporterRoom extends Room
{

    /**
     * Constructor for objects of class TransporterRoom
     * @param description, item - takes in the description and item for the room from the Room class
     */
    public TransporterRoom(String description, Item item)
    {
        super(description, item);
    }

    /**
     * Overrides the getExit method from Room
     *
     * @param  direction - takes in direction to see where to go
     * @return findRandomRoom - returns a random direction
     */
    public Room getExit(String direction)
    {
        return findRandomRoom();
    }
    
    /**
     * Finds a random direction to take the player in
     * @return r - returns a random direction after calculating a random number to find in the array
     */
    private Room findRandomRoom() {
        Random rand = new Random();
        Room r;
        
        Room[] rArray = new Room[4];
        rArray[0] = super.getExit("north");
        rArray[1] = super.getExit("east");
        rArray[2] = super.getExit("south");
        rArray[3] = super.getExit("west");
        
        r = rArray[rand.nextInt(rArray.length)];
        return r;
    }
}
