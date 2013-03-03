import java.io.*;
import java.util.*;
import java.text.*;

public class ArmoryMenu{
	
	private AppController appCtrl;
	private Scanner sc;
	private ArrayList<Weapon> cannonList;
    private ArrayList<Weapon> subcannonList;
    private ArrayList<Weapon> missileList;
    private ArrayList<Weapon> meleeList;

    private final int CANNONS = 1;
    private final int MELEES = 2;
    private final int MISSILES = 3;
    private final int SUBCANNONS = 4;


	public ArmoryMenu(AppController appCtrl){
		this.appCtrl = appCtrl;
		sc = new Scanner(System.in);
		cannonList = appCtrl.getWeaponList(CANNONS);
        subcannonList = appCtrl.getWeaponList(SUBCANNONS);
        missileList = appCtrl.getWeaponList(MISSILES);
        meleeList = appCtrl.getWeaponList(MELEES);
	}

	public void displayArmoryMenu(){
        

        System.out.println();
        System.out.println("== BattleStations :: Le Armory ==");
        System.out.println("1. Cannons");
        System.out.println("2. Melee Weapons");
        System.out.println("3. Missiles");
        System.out.println("4. Subcannons");
        System.out.println();
        System.out.print("Return to [M]ain | Enter weapon class > ");
       

    }


    public void displayWeaponDetail(int weaponClass, int index){
		String wClassName = "";
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();

        switch(weaponClass){
            case CANNONS :
                wClassName = "Cannons";
                weaponList = cannonList;
                break;
            case MISSILES :
                wClassName = "Missiles";
                weaponList = missileList;
                break;
            case MELEES :
                wClassName = "Melee";
                weaponList = meleeList;
                break;
            case SUBCANNONS :
                wClassName = "Subcannons";
                weaponList = subcannonList;
                break;
        }
	    String choice = null;
        boolean validChoice = false;
        do{
            System.out.println();
            System.out.println("== BattleStations :: Le Armory :: " + wClassName + " :: Details ==");
            System.out.println();
            Weapon s = weaponList.get(index-1);
            System.out.println("Weapon: " + s.getName());
            System.out.println("Range: " + s.getRange());
            System.out.println("Min Damage: " + s.getMinDamage());
            System.out.println("Max Damage: " + s.getMaxDamage());
            System.out.println("Weight: " + s.getWeight());
            System.out.println("Level Required: " + s.getLevelReq());

            System.out.println();
            System.out.print("Gold: " + s.getGold());
            System.out.print(" | Wood: " + s.getWood());
            System.out.print(" | Ore: " + s.getOre());
            System.out.println(" | Prock: " + s.getProck());

            System.out.println();
            System.out.print("Return to [M]ain | [C]hoose Other Weapon | [B]uy it > ");
            choice = sc.nextLine().trim().toUpperCase();
            if(choice.equals("M") || choice.equals("B") || choice.equals("C")){
                validChoice = true;
                if(choice.equals("M")){
                    processBackToMainMenu();
                } else if(choice.equals("B")){
                    processBuyWeapon(s);
                    validChoice = false;
                } else if(choice.equals("C")){
                    displayWeaponClassMenu(weaponClass);
                }
            }else{
                System.out.println("Invalid Input!");
                validChoice =false;
            }
        } while(!validChoice);
    }

    public void readOption(){
    	
    	String choice = null;
    	boolean validChoice = false;

        do {
            displayArmoryMenu();
            try {
                choice = sc.nextLine().trim().toUpperCase();
                System.out.println();
                if(choice.equals("M")){
                	validChoice = true;
                	processBackToMainMenu();
                } else {
                    try{
                        int input = Integer.parseInt(choice);
                        if (input >= 1 && input <= 4){
                            validChoice = true;
                            displayWeaponClassMenu(input);
                        }// end if
                        System.out.println("Invalid Input!!!");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid Input!!!");
                    } 
                    
                }
            } catch (InputMismatchException e) {
                // display error message
                System.out.println("Please enter a valid option!");
                sc.nextLine();
            }
        } while (!validChoice);

    }

    public void displayWeaponClassMenu(int weaponClass){
        String wClassName = "";
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();

        switch(weaponClass){
            case CANNONS :
                wClassName = "Cannons";
                weaponList = cannonList;
                break;
            case MISSILES :
                wClassName = "Missiles";
                weaponList = missileList;
                break;
            case MELEES :
                wClassName = "Melee";
                weaponList = meleeList;
                break;
            case SUBCANNONS :
                wClassName = "Subcannons";
                weaponList = subcannonList;
                break;
        }
        String choice = "";
        boolean validChoice = false;
        do{
            System.out.println();
            System.out.println("== BattleStations :: Le Armory :: " + wClassName + "==");
            for(int i = 1 ; i <= weaponList.size() ; i++){
                Weapon c = weaponList.get(i-1);
                System.out.print(i + ". ");
                System.out.print(c.getName());
                System.out.print(" (min: L");
                System.out.print(c.getLevelReq() );
                System.out.println(")");
            }
            System.out.println();
            System.out.print("Return to [M]ain | [B]ack to Armory Menu| Enter weapon > ");
            choice = sc.nextLine().trim().toUpperCase();
            if(choice.equals("M")){
                validChoice = true;
                processBackToMainMenu();
            } else if(choice.equals("B")){
                validChoice = true;
                readOption();
            } else {        
                try{
                    int input = Integer.parseInt(choice);
                    int size = weaponList.size();
                    if (input > 1 && input <= size){
                        validChoice = true;
                        displayWeaponDetail(weaponClass,input);
                    }  // end if
                    System.out.println("Invalid Input!!!");
                }catch (NumberFormatException e){
                    System.out.println("Invalid Input!!!");
                } 

                 
            }


        } while(!validChoice);
    }

    public void processBuyWeapon(Weapon w){
        try{
            ArrayList<String> errors = appCtrl.buy(w);
            if (errors.size() == 0){
                System.out.println("You have successfully bought the item " + w.getName());
            }else{
                System.out.println();
                System.out.println("UNABLE TO BUY!!!");
                for (String error: errors){
                    System.out.println(error);
                }
            }
        } catch (DataException e){
            System.out.println();
            System.out.println("UNABLE TO BUY!!!");
            System.out.println(e.getMessage());
        }
    }

    public void processBackToMainMenu(){
    	MainMenu m = new MainMenu(appCtrl);
    	m.readOption();
    }
}