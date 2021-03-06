import java.io.*;
import java.util.*;
import java.text.*;

public class PartMenu{
	
	private AppController appCtrl;
	private Scanner sc;
	
    private ArrayList<WeaponPart> engineList;
    private ArrayList<WeaponPart> figureheadList;
    private ArrayList<WeaponPart> hullList;
    private ArrayList<WeaponPart> sailList;
    private ArrayList<WeaponPart> stabilizerList;

    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

	public PartMenu(AppController appCtrl){
		this.appCtrl = appCtrl;
		sc = new Scanner(System.in);

        engineList = appCtrl.getPartList(ENGINES);
        figureheadList = appCtrl.getPartList(FIGUREHEADS);
        sailList = appCtrl.getPartList(SAILS);
        hullList = appCtrl.getPartList(HULLS);
        stabilizerList = appCtrl.getPartList(STABILIZERS);
        
	}

	public void displayPartMenu(){
        

        System.out.println();
        System.out.println("== BattleStations :: Le Part ==");
        System.out.println("1. Engines");
        System.out.println("2. Figureheads");
        System.out.println("3. Sails");
        System.out.println("4. Hulls");
        System.out.println("5. Stabilizers");
        System.out.println();
        System.out.print("Return to [M]ain | Enter part class > ");
       

    }


    public void displayPartDetail(int partClass, int index){
		String wClassName = "";
        ArrayList<WeaponPart> partList = new ArrayList<WeaponPart>();

        switch(partClass){
            case ENGINES :
                wClassName = "Engines";
                partList = engineList;
                break;
            case SAILS :
                wClassName = "Sails";
                partList = sailList;
                break;
            case FIGUREHEADS :
                wClassName = "Figureheads";
                partList = figureheadList;
                break;
            case HULLS :
                wClassName = "Hulls";
                partList = hullList;
                break;
            case STABILIZERS :
                wClassName = "Stabilizers";
                partList = stabilizerList;
                break;
        }
	    String choice = null;
        boolean validChoice = false;
        do{
            System.out.println();
            System.out.println("== BattleStations :: Le Part :: " + wClassName + " :: Details ==");
            System.out.println();
            WeaponPart s = partList.get(index-1);
            System.out.println("Part: " + s.getName());
            System.out.println("Speed: " + s.getSpeed());
            System.out.println("HP: " + s.getHP());
            System.out.println("Capacity: " + s.getCapacity());
            System.out.println("Weight: " + s.getWeight());
            System.out.println("Level Required: " + s.getLevelReq());

            System.out.println();
            System.out.print("Gold: " + s.getGold());
            System.out.print(" | Wood: " + s.getWood());
            System.out.print(" | Ore: " + s.getOre());
            System.out.println(" | Prock: " + s.getProck());

            System.out.println();
            System.out.print("Return to [M]ain | [C]hoose Other Part | [B]uy it > ");
            choice = sc.nextLine().trim().toUpperCase();
            if(choice.equals("M") || choice.equals("B") || choice.equals("C")){
                validChoice = true;
                if(choice.equals("M")){
                    processBackToMainMenu();
                } else if(choice.equals("B")){
                    processBuyPart(partClass, s);
                    validChoice = false;
                } else if(choice.equals("C")){
                    displayPartClassMenu(partClass);
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
            displayPartMenu();
            try {
                choice = sc.nextLine().trim().toUpperCase();
                System.out.println();
                
                if(choice.equals("M")){
                	validChoice = true;
                	processBackToMainMenu();
                } else { 
                    try{
                        int input = Integer.parseInt(choice);
                        if (input >= 1 && input <= 5){
                            validChoice = true;
                            displayPartClassMenu(input);
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

    public void displayPartClassMenu(int partClass){
        String wClassName = "";
        ArrayList<WeaponPart> partList = new ArrayList<WeaponPart>();

        switch(partClass){
            case ENGINES :
                wClassName = "Engines";
                partList = engineList;
                break;
            case SAILS :
                wClassName = "Sails";
                partList = sailList;
                break;
            case FIGUREHEADS :
                wClassName = "Figureheads";
                partList = figureheadList;
                break;
            case HULLS :
                wClassName = "Hulls";
                partList = hullList;
                break;
            case STABILIZERS :
                wClassName = "Stabilizers";
                partList = stabilizerList;
                break;
        }
        
        String choice = "";
        boolean validChoice = false;
        do{
            System.out.println();
            System.out.println("== BattleStations :: Le Part :: " + wClassName + "==");
            for(int i = 1 ; i <= partList.size() ; i++){
                WeaponPart c = partList.get(i-1);
                System.out.print(i + ". ");
                System.out.print(c.getName());
                System.out.print(" (min: L");
                System.out.print(c.getLevelReq() );
                System.out.println(")");
            }
            System.out.println();
            System.out.print("Return to [M]ain | [B]ack to Part Menu| Enter part > ");
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
                    if (input >= 1 && input <= partList.size()){
                        validChoice = true;
                        displayPartDetail(partClass,input);
                    }// end if
                    System.out.println("Invalid Input!!!");
                }catch (NumberFormatException e){
                    System.out.println("Invalid Input!!!");
                }

            }


        } while(!validChoice);
    }

    public void processBuyPart(int partType, WeaponPart p){
        try{
            ArrayList<String> errors = appCtrl.buy(partType, p);
            if (errors.size() == 0){
                System.out.println("You have successfully bought the item " + p.getName());
            } else{
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