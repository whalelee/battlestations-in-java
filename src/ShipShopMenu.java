import java.util.*;

public class ShipShopMenu{
	
	private AppController appCtrl;
	private Scanner sc;

	public ShipShopMenu(AppController appCtrl){
		this.appCtrl = appCtrl;
		sc = new Scanner(System.in);
	}

	public void displayShipyardMenu(){
        System.out.println("== BattleStations :: Le Shipyard ==");
        System.out.println();
        ArrayList<Ship> shipList = appCtrl.getShips();
        for(int i = 1 ; i <= shipList.size() ; i++){
        	Ship s = shipList.get(i-1);
        	System.out.print(i + ". ");
        	System.out.print(s.getName());
        	System.out.print(" (min: L");
        	System.out.print(s.getLevelReq() );
        	System.out.println(")");
        }
        System.out.println();
        System.out.println("Return to [M]ain | Enter number > ");

    }

    public void readOption(){
    	
    	String choice = null;
        do {
            displayShipyardMenu();
            try {
                choice = sc.nextLine();
                System.out.println();
                switch (choice) {
                    /*case "1":
                        processViewStatistics();
                        break;
                    case "2":
                        processMyHangar();
                        break;
                    case "3":
                        processShopping();
                        break;*/
                    default:
                        System.out.println("Invalid Input!");
                }
            } catch (InputMismatchException e) {
                // display error message
                System.out.println("Please enter a valid option!");
                sc.nextLine();
            }
        } while (!choice.equals("5"));

    }
}