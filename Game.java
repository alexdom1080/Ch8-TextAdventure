 



/**
 *  This class is the main class of the "Juul Adventure" application. 
 *  "Juul Adventure" is a very simple, text based adventure game.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Alexis Dominguez
 * @version 4/8/2018
 */

import java.util.*;

public class Game 
{
    
    private Parser parser;
    private Room currentRoom;
    private Scanner reader;
    private Player player;
    private Room prevRoom;
    
    /**
     * Create the game, initialise its internal map, the player, and the scanner.
     */
    public Game() 
    {
        
        player = new Player();
        reader = new Scanner(System.in);
        parser = new Parser();
    }
    
    /**
     * This method gets information about the player
     */
    private void createPlayer() {
        System.out.println("Enter player name: ");
        String name = reader.nextLine();
        player.setPlayerName(name);
        createRooms();
        System.out.println("Enter max player weight: ");
        int w = reader.nextInt();
        player.setMaxWeight(w);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Variables for each room
        Room outside, mainHallway, englishClass, mathClass, historyClass, gym, pool1, pool2, pool3,
             pool4, juulLounge,principalOffice, lab, secondHallway, art, scienceClass;
        
        //Item variables
        Item lab_Item, principalOffice_Item, outside_Item, mainHallway_Item, englishClass_Item,
             mathClass_Item, historyClass_Item, gym_Item, pool1_Item, pool2_Item, pool3_Item,
             pool4_Item, juulLounge_Item, secondHallway_Item, art_Item, scienceClass_Item;
             
             
             lab_Item = new Item("Computer: Used to surf the web.", 0);
             principalOffice_Item = new Item("Mango Pod: Used to gain access to the JuulLounge!", 1);
             outside_Item = new Item("Bench: Avaliable to be sat on for a bit", 0);
             mainHallway_Item = new Item("Flyer: Advertising to the join the school's Juul Club...", 0);
             secondHallway_Item = new Item("Flyer: Advertising to the join the school's Japanese "+ 
                                           "Cartoon Club...", 0);
             englishClass_Item = new Item("Book: Hamlet", 5);
             mathClass_Item = new Item("Textbook: Calculus", 100);
             historyClass_Item = new Item("Textbook: American History", 100);
             scienceClass_Item = new Item("Book: Physics", 100);
             gym_Item = new Item("Weight Rack: There is only a singular weight on it ,,,", 50);
             pool1_Item = new Item("Fitler: This looks dirty...", 1);
             pool2_Item = new Item("Fitler: This looks dirty...", 1);
             pool3_Item = new Item("Fitler: This looks dirty...", 1);
             pool4_Item = new Item("Fitler: This looks dirty...", 1);
             art_Item = new Item("Paint: The only color left here is green...", 0);
             juulLounge_Item = new Item("The only item in here is a toliet ,,,", 0);
    
  
      // create the rooms
        outside = new Room("Outside the main entrance of the high school.", outside_Item);
        mainHallway = new Room("Inside the main hallway of the school.", mainHallway_Item);
        englishClass = new Room("In the english classroom.", englishClass_Item);
        mathClass = new Room("In the math classroom", mathClass_Item);
        historyClass = new Room("In the history classroom.", historyClass_Item);
        gym = new Room("Inside the school's gym.", gym_Item);
        pool1 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!", pool1_Item);
        pool2 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!", pool2_Item);
        pool3 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!", pool3_Item);
        pool4 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!", pool4_Item);
        juulLounge = new Room("You are in the sanctuary! Congrats!", juulLounge_Item);
        principalOffice = new Room("You are in the Principal's Office!", principalOffice_Item);
        lab = new Room("You are in the computer lab of the school.", lab_Item);
        secondHallway = new Room("You have reached the second hallway.", secondHallway_Item);
        art = new Room("In the art classroom.", art_Item);
        scienceClass = new Room("You are in the science classroom.", scienceClass_Item);
        
        
        // initialise room exits
        outside.setExit("north", mainHallway);

        mainHallway.setExit("south", outside);
        mainHallway.setExit("north", historyClass);
        mainHallway.setExit("east", mathClass);
        mainHallway.setExit("west", englishClass);
        
        historyClass.setExit("south", mainHallway);
        historyClass.setExit("north", pool1);
        historyClass.setExit("west", lab);
        historyClass.setExit("east", juulLounge);
        
        mathClass.setExit("west", mainHallway);
        mathClass.setExit("south", secondHallway);
        
        englishClass.setExit("east", mainHallway);
        englishClass.setExit("south", art);
        
        lab.setExit("west", historyClass);
        
        art.setExit("north", englishClass);
        art.setExit("east", secondHallway);
        
        secondHallway.setExit("north", mathClass);
        secondHallway.setExit("west", art);
        secondHallway.setExit("east", scienceClass);
        
        scienceClass.setExit("west", secondHallway);
        scienceClass.setExit("north", gym);
        scienceClass.setExit("south", pool3);
 
        gym.setExit("north", principalOffice);
        gym.setExit("east", pool4);
        gym.setExit("south", scienceClass);
        
        principalOffice.setExit("west", pool2);
        
        prevRoom = null;
        player.setCurrentRoom(outside);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        createPlayer();
        
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    public static void main(String[] args) {
        Game juul = new Game();
        juul.play();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Juul Adventure!");
        System.out.println("Juul Adventure is a semi boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getPlayerDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case CHARGE:
                charge();
                break;
                
            case BACK:
                backRoom();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Prints out the statement of the Juul consuming a battery pack to stay charged.
     */
    private void charge() {
        System.out.println("You consume a battery pack to stay charged.");
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the high school.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getPlayerExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setPlayerNewRoom(nextRoom);
            System.out.println(player.getPlayerDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    /**
     * "look" was entered. Gets the desciption of the current room for the user.
     */
    private void look() {
        System.out.println(player.getPlayerDescription());
    }
    
    /**
     * "back" was entered. Allows the player to retrace their steps by one room.
     */
    private void backRoom(){
        player.previousRoomMove();
    }
}

