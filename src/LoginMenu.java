import java.util.InputMismatchException;
import java.util.Scanner;

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

    /**
     * Get user to provide username and password.
     * If the user logs in successfully, launch the MainMenu.
     * Otherwise, go back to login menu.
     */
    public void processLogin() {
        System.out.println("\n== Player Login ==\n");

        System.out.print("Enter your username > ");
        String username = sc.nextLine();

        System.out.print("Enter your password > ");
        String passwordEntered = sc.nextLine();

        Player p = appCtrl.authenticatePlayer(username, passwordEntered);

        if (p!= null){
            String playerName = p.getName();
            System.out.print("\nHi " + playerName + " !");

            MainMenu m = new MainMenu(appCtrl);
            m.readOption();

        } else{
            System.out.print("Sorry, you entered a wrong username and/or password.");

        }

       
    }

    public void processRegister() {
        System.out.println("\n== BattleStations :: Registration ==\n");

        System.out.print("Enter your username > ");
        String username = sc.nextLine();
        System.out.println();
        
        int samePassword = false;
        do{
        	System.out.print("Enter your password > ");
        	String passwordEntered = sc.nextLine();
        	System.out.println();
        	System.out.print("Confirm your password > ");
        	String passwordConfirmed = sc.nextLine();

        	if (passwordEntered.equals(passwordConfirmed)){
        		samePassword = true;
        	} else{
        		System.out.println("Password provided are not the same. Please reenter your password.");
        	}
        } while (!samePassword);

        System.out.print("Choose your player type -- (P)irate / (E)xplorer > ");
        String userSelectType = sc.nextLine();
        char playerType = userSelectType.charAt(0);

        appCtrl.processRegister(username, passwordEntered);

       
    }

    public void processExit(){
        System.out.println("== Quit Application ==");
        System.out.println("Bye bye!!!");
        System.exit(0);
    }


}
