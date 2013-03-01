import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */
public class PartManager{
	private ArrayList<Part> engineList;
	private ArrayList<Part> figureheadList;
	private ArrayList<Part> hullList;
	private ArrayList<Part> sailList;
	private ArrayList<Part> stabilizerList;
    
    private final String ENGINE_FILE_NAME = "data/engines.csv";
    private final String FIGUREHEAD_FILE_NAME = "data/figurehead.csv";
    private final String SAIL_FILE_NAME = "data/sails.csv";
    private final String HULL_FILE_NAME = "data/hulls.csv";
    private final String STABILIZER_FILE_NAME = "data/stabilizers.csv";

    private final String CLASS_NAME = "PartManager";


    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load ship' information from file.
     */
	public PartManager() throws DataException{
		engineList = new ArrayList<Weapon>();
		subcannonList = new ArrayList<Weapon>();
		missileList = new ArrayList<Weapon>();
		meleeList = new ArrayList<Weapon>();
		
        load();
	}

	public ArrayList<Weapon> getWeaponList(int weaponClass){
		switch(weaponClass){
			case CANNONS:
				return engineList;
			case MELEES:
				return meleeList;
			case MISSILES:
				return missileList;
			case SUBCANNONS:
				return subcannonList;
		}
		return engineList;
	}

	public void load() throws DataException{
		loadMissile();
		loadCannon();
		loadSubcannon();
		loadMelee();
	}

	public void loadThisWeaponClass(String weaponClass) throws DataException{
		String filename = "";
		ArrayList<Weapon> weaponList = new ArrayList<Weapon>();

		switch(weaponClass) {
			case "missile" :
				filename = MISSILE_FILE_NAME;
				break;
			case "cannon" :
				filename = CANNON_FILE_NAME;
				break;
			case "subcannon" :
				filename = SUBCANNON_FILE_NAME;
				break;
			case "melee" :
				filename = MELEE_FILE_NAME;
				break;
		}

		Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File(filename));
            fileIn.useDelimiter("\r\n");
            fileIn.next(); //skip the first line
            //#Ship,Speed,HP,Slots,Capacity,Level Required,Gold,Wood,Ore,Plasma Rock,Port
            while (fileIn.hasNext()) {
                String[] data   = fileIn.next().split(",");
               	String name = data[0];
				int range = Integer.parseInt(data[1]);  
				int minDamage = Integer.parseInt(data[2]);  
				int maxDamage = Integer.parseInt(data[3]);  
				int weight = Integer.parseInt(data[4]);  
				int levelReq = Integer.parseInt(data[5]);  
				int gold = Integer.parseInt(data[6]);  
				int wood = Integer.parseInt(data[7]);  
				int ore = Integer.parseInt(data[8]);  
				int prock = Integer.parseInt(data[9]);  
				String port = data[10];  

                //set data to the Ship object
                Weapon p = new Weapon();
                p.setName(name);
                p.setRange(range);
                p.setMinDamage(minDamage);
                p.setMaxDamage(maxDamage);
                p.setWeight(weight);
                p.setLevelReq(levelReq);
                p.setGold(gold);
                p.setWood(wood);
                p.setOre(ore);
                p.setProck(prock);
                p.setPort(port);          

                switch(weaponClass) {
					case "missile" :
						missileList.add(p);
						break;
					case "cannon" :
						engineList.add(p);
						break;
					case "subcannon" :
						subcannonList.add(p);
						break;
					case "melee" :
						meleeList.add(p);
						break;
				}
            }
        } catch (InputMismatchException e) {
            //propagate error
            String message = "Reading error in File \"" + filename + "\". Invalid double format.";
            throw new DataException(message);
        } catch (FileNotFoundException e) {
            //propagate error
            String message = CLASS_NAME + " class : File " + filename + " not found";
            throw new DataException(message);
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }
	}

	public void loadMissile() throws DataException{
        loadThisWeaponClass("missile");
	}

	public void loadCannon() throws DataException{
		loadThisWeaponClass("cannon");
	}

	public void loadSubcannon() throws DataException{
		loadThisWeaponClass("subcannon");
	}

	public void loadMelee() throws DataException{
		loadThisWeaponClass("melee");
	}
}