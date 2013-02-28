import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Console;

/**
 * Boundary class that allows user to login to this application.
 */
public class LoginMenu {

    private AppController appCtrl;
    private Scanner sc;

    /**
     * Constructor.
     *
     * @param appCtrl AppController class that provides data manager objects
     */
    public LoginMenu(AppController appCtrl) {
        sc = new Scanner(System.in);
        this.appCtrl = appCtrl;
    }

    /**
     * Display the login menu to console.
     */
    public void displayLoginMenu() {
        System.out.println();
        System.out.println("== BattleStations :: Welcome ==");
        System.out.println("Good morning, player!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice > ");
    }

    /**
     * Read user's selection of menu option and execute the request.
     */
    public void readOption() {
        
        int choice = 0;
        do {
            displayLoginMenu();
            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        processLogin();
                        break;
                    case 2:
                    	processRegister();
                    	break;
                    case 3:
                        processExit();
                        break;
                    default:
                        System.out.println("Invalid Input! Please try again.");
                }
            } catch (InputMismatchException e) {
                // display error message
                System.out.println("Please enter a valid option!");
                sc.nextLine();
            }
        } while (choice != 3);
    }

    public String runConsoleToMaskPassword(String message) {
        Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }

        
        char passwordArray[] = console.readPassword(message);
        return new String(passwordArray);
    }

    /**
     * Get user to provide username and password.
     * If the user logs in successfully, launch the MainMenu.
     * Otherwise, go back to login menu.
     */
    public void processLogin() {
        System.out.println("\n== Player Login ==\n");

        System.out.print("Enter your username > ");
        String username = sc.nextLine();

        //runCon
        String passwordEntered = runConsoleToMaskPassword("Enter your password > ");

        Player p = appCtrl.authenticatePlayer(username, passwordEntered);

        if (p!= null){
            String playerName = p.getName();
            System.out.print("\nHi " + playerName + " !");

            //MainMenu m = new MainMenu(appCtrl);
            //m.readOption();

        } else{
            System.out.print("Sorry, you entered a wrong username and/or password.");

        }

       
    }

    public void processRegister() {
        String passwordEntered = null;
        String username = null;
        char playerType = 'a';

        System.out.println("\n== BattleStations :: Registration ==\n");

        
        boolean userDoesNotExist = false;
        do{
            System.out.print("Enter your username > ");
            username = sc.nextLine();
            System.out.println();

            if (appCtrl.validateUsername(username)){
                userDoesNotExist = true;
            } else{
                System.out.println("Username has been taken. Choose a new username.");
            }
        } while (!userDoesNotExist);

        boolean samePassword = false;
        do{
            System.out.print("Enter your password > ");
            passwordEntered = sc.nextLine();
            System.out.println();
            System.out.print("Confirm your password > ");
            String passwordConfirmed = sc.nextLine();

            if (passwordEntered.equals(passwordConfirmed)){
                samePassword = true;
            } else{
                System.out.println("Password provided are not the same. Please reenter your password.");
            }
        } while (!samePassword);

        
        boolean correctPlayerType = false;
        do{
            System.out.print("Choose your player type -- (P)irate / (E)xplorer > ");
            String userSelectType = sc.nextLine();
            userSelectType.toLowerCase();
            playerType = userSelectType.charAt(0);

            if (playerType=='p' || playerType == 'e'){
                correctPlayerType = true;
            } else{
                System.out.println("Please enter  (P)irate / (E)xplorer");
            }
        } while (!correctPlayerType);
        
        try{
            appCtrl.addPlayer(username, passwordEntered, playerType);
        } catch (DataException e){
            
        }
    }

    public void processExit(){
        System.out.println("== Quit Application ==");
        System.out.println("Bye bye!!!");
        System.exit(0);
    }


}
