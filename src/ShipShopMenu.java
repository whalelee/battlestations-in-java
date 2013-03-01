import java.io.*;
import java.util.*;
import java.text.*;

public class ShipShopMenu{
	
	private AppController appCtrl;
	private Scanner sc;
	private ArrayList<Ship> shipList;

	public ShipShopMenu(AppController appCtrl){
		this.appCtrl = appCtrl;
		sc = new Scanner(System.in);
		shipList = appCtrl.getShips();
	}

	public void displayShipyardMenu(){
        
        System.out.println();
        System.out.println("== BattleStations :: Le Shipyard ==");
        for(int i = 1 ; i <= shipList.size() ; i++){
        	Ship s = shipList.get(i-1);
        	System.out.print(i + ". ");
        	System.out.print(s.getName());
        	System.out.print(" (min: L");
        	System.out.print(s.getLevelReq() );
        	System.out.println(")");
        }
        System.out.println();
        System.out.print("Return to [M]ain | Enter number > ");
            
    }

    public void displayShipDetail(int index){
		String choice = null;
		boolean validChoice = false;
		do{
			System.out.println();
			System.out.println("== BattleStations :: Le Shipyard :: Details ==");
	        System.out.println();
	        Ship s = shipList.get(index-1);
	        System.out.println("Ship: " + s.getName());
	        System.out.println("HP: " + s.getHP());
	        System.out.println("Slots: " + s.getSlots());
	        System.out.println("Capacity: " + s.getCapacity());
	        System.out.println("Level Required: " + s.getLevelReq());

	        System.out.println();
	        System.out.print("Gold: " + s.getGold());
	        System.out.print(" | Wood: " + s.getWood());
	        System.out.print(" | Ore: " + s.getOre());
	        System.out.println(" | Prock: " + s.getProck());

	        System.out.println();
	        System.out.print("Return to [M]ain | [B]uy it > ");
	        choice = sc.nextLine().trim().toUpperCase();
	        if(choice.equals("M") || choice.equals("B")){
	        	validChoice = true;
	        	if(choice.equals("M")){
	        		processBackToMainMenu();
	        	} else{
	        		processBuyShip();
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
            displayShipyardMenu();
            try {
                choice = sc.nextLine();
                System.out.println();
                if(choice.equals("M")){
                	validChoice = true;
                	processBackToMainMenu();
                } else {     	
                	int input = Integer.parseInt(choice);
                	if (input >= 1 || input <= shipList.size()){
                		validChoice = true;
                		displayShipDetail(input);
                	}
                }
            System.out.println("Invalid Input!");
            } catch (InputMismatchException e) {
                // display error message
                System.out.println("Please enter a valid option!");
                sc.nextLine();
            }
        } while (!validChoice);

    }

    public void processBuyShip(){

    }

    public void processBackToMainMenu(){
    	MainMenu m = new MainMenu(appCtrl);
    	m.readOption();
    }
}