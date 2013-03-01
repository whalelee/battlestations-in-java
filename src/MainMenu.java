import java.util.*;

public class MainMenu{

    private AppController appCtrl;
    private Scanner sc; 
    private Player p;
    private Ship s;

    public MainMenu(AppController appCtrl){
        this.appCtrl = appCtrl;
        sc = new Scanner(System.in);
        p = appCtrl.getPlayerLoggedIn();
    }
    public void displayMainMenu() {
        System.out.println();
        System.out.println("== BattleStations :: Main Menu ==");
        System.out.println("Captain: " + p.getName());
        System.out.println("AP: " + p.getAP() + "\t\t HP: " + p.getCurrentHP() + " / " + p.getTotalHP());
        System.out.println("Gold: " + p.getGold() + "\t Wood: " + p.getWood());
        System.out.println("Ore: " + p.getOre() + "\t\t Prock: " + p.getProck());
        System.out.println();
        System.out.println("1. View my vital statistics");
        System.out.println("2. My hangar");
        System.out.println("3. Le Shippe Shoppe");
        System.out.println("4. PVP");
        System.out.println("5. Logout");
        System.out.println();
        System.out.print("Enter your choice > ");
    }

    public void readOption() {

        int choice = 0;
        do {
            displayMainMenu();
            try {
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                switch (choice) {
                    case 1:
                        processViewStatistics();
                        break;
                    case 2:
                        processMyHangar();
                        break;
                    case 3:
                        processShopping();
                        break;
                    case 4:
                        processPVP();
                        break;
                    case 5:
                        processLogout();
                        break;
                    default:
                        System.out.println("Invalid Input!");
                }
            } catch (InputMismatchException e) {
                // display error message
                System.out.println("Please enter a valid option!");
                sc.nextLine();
            }
        } while (choice != 5);
    }

    public void processViewStatistics(){
        String choice = null;
        boolean validChoice = false;
        do{
            System.out.println();
            System.out.println("== BattleStations :: captain ==");
            System.out.println("Craft: " + p.getCraft() +"\tSpeed: " + p.getSpeed() + "(+" + p.getNavigation()+ ")");
            System.out.println("Gunnery: " + p.getGunnery() + "\tStats Pts: " + p.getStatsPts());
            System.out.println("Navigation: " + p.getNavigation());
            System.out.println("Wins: " + p.getWins() + "\tLosses: " + p.getLosses());
            System.out.println("Total Exp: " + p.getCraft() );
            System.out.println("Joined on: " + p.getJoinedDate());
            System.out.println();
            System.out.println("Return to [M]ain | [A]llocate Stats Pts > ");
            choice = sc.nextLine().trim().toUpperCase();
            if(choice.equals("M") || choice.equals("A")){
                validChoice = true;
                if(choice.equals("M")){
                    processBackToMainMenu();
                } else{
                    processAllocateStatsPts();
                }

            }else{
                System.out.println("Invalid Input!");
                validChoice =false;
            }
        } while(!validChoice);
    }

    public void processMyHangar(){
       /* System.out.println();
        System.out.println("== BattleStations :: My Hangar ==");
        System.out.println("");
        System.out.println();
        System.out.println("Parts");
        System.out.println("[F]igurehead: \t L45 - Black Skull crest");
        System.out.println("[S]ail: \t L45 - Solar Sail");
        System.out.println("S[t]abilizer: \t L46 - Outrigger");
        System.out.println("[H]hull: \t L46 - Alloy Plating");
        System.out.println("[E]ngine: \t L45 - Fuel Injector");
        System.out.println();
        System.out.println("Weapons:");
        System.out.println();
        System.out.println("[W1] L40 - Impact Cannon");
        System.out.println("[W2] L40 - Impact Cannon");
        System.out.println("[W3] L35 - Aerial Nines");
        System.out.println("[W4] L45 - Hand of Justice");
        System.out.println();
        System.out.println("Capacity:" + "");
        System.out.println("Speed:" + "");
        System.out.println("Capacity:" + "");
        System.out.println("Return to [M]ain | [E]quip | [U]nequip | [R]eport");
        */

    
    }

    public void processShopping(){
        String choice = null;
        boolean validChoice = false;

        do{
            System.out.println();
            System.out.println("== BattleStations :: Le Shippe Shoppe ==");
            System.out.println("1. Le Shipyard");
            System.out.println("2. Le Armory");
            System.out.println("3. Le Part");
            System.out.println();
            System.out.print("Return to [M]ain | Enter weapon/part > ");
            choice = sc.nextLine().toUpperCase().trim();
            switch(choice){
                case "M":
                    validChoice = true;
                    this.readOption();
                    break;
                case "1":
                    validChoice = true; 
                    ShipShopMenu m = new ShipShopMenu(appCtrl);
                    m.readOption();
                    break;
               /* case "2":
                    validChoice = true;
                    ArmoryMenu m = new ArmoryMenu(appCtrl);
                    m.readOption();
                    break;
                case "3":
                    validChoice = true;
                    PartMenu m = new PartMenu(appCtrl);
                    m.readOption();
                    break;*/
                default:
                    System.out.println("Invalid Input!");

            }//close switch
        } while(!validChoice); // close do
    }

    public void processAllocateStatsPts(){

    }
    
    public void processPVP(){

    }

    public void processLogout(){
        appCtrl.logOutPlayer();
        p = null;
        LoginMenu lm = new LoginMenu(appCtrl);
        lm.readOption();
    }
}