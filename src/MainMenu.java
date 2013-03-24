import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */

public class MainMenu{

    private AppController appCtrl;
    private Scanner sc; 
    private Player p; //loggedInPlayer
    private Ship s;

    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;
    private final int WEAPONS = 6;

    private ArrayList<String> validIndices;

    public MainMenu(AppController appCtrl){
        this.appCtrl = appCtrl;
        sc = new Scanner(System.in);
        p = appCtrl.getPlayerLoggedIn();
        validIndices = new ArrayList<String>();
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
        System.out.println("2. My hangar (Storage)");
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
                        //processMyHangar();
                        processStorage();
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
                    readOption();
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
       /* 
        String choice = "";
        boolean validChoice = false;
        do{
            System.out.println();
            System.out.println("== BattleStations :: My Hangar ==");
            System.out.println("");
            System.out.println();
            System.out.println("Parts");
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
            System.out.print("Return to [M]ain | [E]quip | [U]nequip | [R]epair");
            choice = sc.nextLine().trim().toUpperCase();
            
            switch (choice){
                case "M":
                    validChoice = true;
                    readOption();
                    break;
                case "E":
                    validChoice = true;
                    processStorage();
                    break;
                case "U":
                    validChoice = true;
                    processUnequip();
                    break;
                case "R":
                    validChoice = true;
                    processRepair();
                    break;
            }

        }while(!validChoice);
        */

    
    }

    public void processStorage(){
        String choice = "";
        boolean validChoice = false;

        validIndices = new ArrayList<String>();

        do{
            System.out.println();
            System.out.println("== BattleStations :: Storage ==");
            System.out.println("");
            showListInStorage(FIGUREHEADS);
            showListInStorage(SAILS);
            showListInStorage(STABILIZERS);
            showListInStorage(HULLS);
            showListInStorage(ENGINES);
            showListInStorage(WEAPONS);

            System.out.print("Return to [M]ain | [B]ack to Hangar | Enter weapon/ part > ");
            choice = sc.nextLine().trim().toUpperCase();

            if (choice.equals("M")){
                validChoice = true;
                readOption();
            } else if (choice.equals("B")){
                validChoice = true;
                processMyHangar();
            } else if (validIndices.contains(choice)){
                validChoice = true;
                processEquip(choice);
            }else{
                System.out.println("Invalid Input!");
            }

        }while(!validChoice);

            
    }

    public void showListInStorage(int list){
        String partType = "Figureheads: ";
        ArrayList<WeaponPart> partList = p.getStorage().getFigureheadList();
        ArrayList<WeaponPart> weaponList = null;
        String prefix = "F";
        WeaponPart pa = null;
        WeaponPart w = null;
        switch(list){
            case FIGUREHEADS:
                partType = "Figureheads: ";
                partList = p.getStorage().getFigureheadList();
                prefix = "F";
                pa = null;
                break;
            case ENGINES:
                partType = "Engines";
                partList = p.getStorage().getEngineList();
                prefix = "E";
                pa = null;
                break;
            case HULLS:
                partType = "Hulls";
                partList = p.getStorage().getHullList();
                prefix = "H";
                pa = null;
                break;
            case SAILS:
                partType = "Sails";
                partList = p.getStorage().getSailList();
                prefix = "S";
                pa = null;
                break;
            case STABILIZERS:
                partType = "Stabilizers";
                partList = p.getStorage().getStabilizerList();
                prefix = "T";
                pa = null;
                break;
            case WEAPONS:
                partType = "Weapons";
                weaponList = p.getStorage().getWeaponList();
                prefix = "W";
                w = null;
                break;

        }

        System.out.println(partType + ": ");
        if (weaponList!=null){
            if (weaponList.size() == 0){
                System.out.println("NIL");
            }
            for (int i = 1; i <=weaponList.size(); i++){
                w = weaponList.get(i -1);
                int levelReq = w.getLevelReq();
                String partName = w.getName();
                String index = prefix + i;
                validIndices.add(index);

                System.out.println(String.format("[%1s] L%2d - %3s", index, levelReq, partName));
                
            }
        } else {

           if (partList.size() == 0){
                System.out.println("NIL");
            }
            for (int i = 1; i <=partList.size(); i++){
                pa = partList.get(i -1);
                int levelReq = pa.getLevelReq();
                String partName = pa.getName();
                String index = prefix + i;
                validIndices.add(index);

                System.out.println(String.format("[%1s] L%2d - %3s", index, levelReq, partName));
                
            } 
        }
        
        System.out.println("");

    }

    public void processEquip(String choice){
        
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
                    ShipShopMenu a = new ShipShopMenu(appCtrl);
                    a.readOption();
                    break;
                case "2":
                    validChoice = true;
                    ArmoryMenu m = new ArmoryMenu(appCtrl);
                    m.readOption();
                    break;
                case "3":
                    validChoice = true;
                    PartMenu p = new PartMenu(appCtrl);
                    p.readOption();
                    break;
                default:
                    System.out.println("Invalid Input!");

            }//close switch
        } while(!validChoice); // close do
    }

    public void processAllocateStatsPts(){

    }
    
    public void processPVP(){
        String choice = null;
        boolean validChoice = false;

        do{
            System.out.println();
            System.out.println("== BattleStations :: PVP ==");
            showPlayerList();
            
            choice = sc.nextLine().toUpperCase().trim();
            
        } while (!validChoice);
    }

    public void showPlayerList(){
        ArrayList<Player> pList = appCtrl.getPlayerList();
        int counter = 1;
        for(int i = 0; i < pList.size(); i++){
            Player p = pList.get(i);
            if(!this.p.getName().equals(p.getName())){
                System.out.print(counter + ". ");
                System.out.print(p.getName());
                System.out.print(" [" + p.getLevel() + "] - ");
                System.out.print(p.getStatus());
                System.out.println();
                counter++;

            }
            
        }

        System.out.print("Return to [M]ain | Attack (1 - " +(counter-1)+") > ");



    }
    public void processLogout(){
        appCtrl.logOutPlayer();
        p = null;
        LoginMenu lm = new LoginMenu(appCtrl);
        lm.readOption();
    }
}