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

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, mainHallway, englishClass, mathClass, historyClass, gym, pool1, pool2, pool3,
             pool4, juulLounge,principalOffice, lab, secondHallway, frenchClass, spanishClass, art,
             scienceClass;
      
        // create the rooms
        outside = new Room("Outside the main entrance of the high school.");
        mainHallway = new Room("Inside the main hallway of the school.");
        englishClass = new Room("In the english classroom.");
        mathClass = new Room("In the math classroom");
        historyClass = new Room("In the history classroom.");
        gym = new Room("Inside the school's gym.");
        pool1 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!");
        pool2 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!");
        pool3 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!");
        pool4 = new Room("Uh-Oh! This area is wet, and it looks like you have died! Good-bye!");
        juulLounge = new Room("You are in the sanctuary! Congrats!");
        principalOffice = new Room("You are in the Principal's Office!");
        lab = new Room("You are in the computer lab of the school.");
        secondHallway = new Room("You have reached the second hallway.");
        frenchClass = new Room("Inside the french classroom.");
        spanishClass = new Room("In the spanish classroom.");
        art = new Room("In the art classroom.");
        scienceClass = new Room("You are in the science classroom.");
        
        
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
        
        
        

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
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

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
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
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
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
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
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
        System.out.println(currentRoom.getLongDescription());
    }
}
