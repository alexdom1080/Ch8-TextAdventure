//Player.java
import java.util.*;
/**
 * Player class holds the player's current room, name, max weight, and previous room info.
 *
 * @author Alexis Dominguez
 * @version 4/13/2019
 */
public class Player
{
    // Variables
    private String playerName;
    private Room currentRoom;
    private int maxWeight;
    private Stack<Room> roomStack;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        playerName = "";
        currentRoom = null;
        maxWeight = 1000;
        roomStack = new Stack<Room>();
    }
    
    /**
     * Constructor that contians parameters
     */
    public Player(String name, Room cRoom, int mWeight) {
        this.playerName = name;
        this.currentRoom = cRoom;
        this.maxWeight = mWeight;
        roomStack = new Stack<Room>();
    }
    
    /**
     * Sets player name
     * @param pName - takes in the player name
     */
    public void setPlayerName(String pName) {
        this.playerName = pName;
    }
    
    /**
     * Returns player name
     * @return playerName - returns the player name
     */
    public String playerName() {
        return this.playerName;
    }
    
    /**
     * Sets current room
     * @param currRoom - takes in current room
     */
    public void setCurrentRoom(Room currRoom) {
        this.currentRoom = currRoom;
    }
    
    /**
     * Returns player current room
     * @return currentRoom - returns current room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    
    /**
     * Sets max weight
     * @param maxiumWeight - takes in max weight
     */
    public void setMaxWeight(int maxiumWeight) {
        this.maxWeight = maxiumWeight;
    }
    
    /**
     * Returns the max weight
     * @return maxWeight - returns max weight
     */
    public int getMaxWeight() {
        return this.maxWeight;
    }
    
    /**
     * Gets player description
     * @return result - returns player current location and descprition of room
     */
    public String getPlayerDescription() {
        String result = "Player " + playerName + ": " + "\n";
        result += currentRoom.getLongDescription();
        return result;
    }
    
    /**
     * Gets the player's exit
     * @return currentRoom exit - returns the direction the player would like to go
     */
    public Room getPlayerExit(String direction) {
        return currentRoom.getExit(direction);
    }
    
    /**
     * Sets the direction the player would like to go
     * @param nextRoom
     */
    public void setPlayerNewRoom(Room nextRoom){
        roomStack.push(currentRoom);
        currentRoom = nextRoom;
    }
    
    /**
     * Moves player back to previous room
     */
    public void previousRoomMove() {
        if(roomStack.empty()) {
            System.out.println("Sorry, there are no previous rooms to go to.");
        }else {
            currentRoom = roomStack.pop();
            System.out.println("Player " + playerName + ": ");
            System.out.println(currentRoom.getLongDescription());
        }
    }
}
