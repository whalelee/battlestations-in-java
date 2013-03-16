import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist WeaponPart & Parts' information to file.
 */
public class WeaponPartManager{
	private ArrayList<WeaponPart> engineList;
	private ArrayList<WeaponPart> figureheadList;
	private ArrayList<WeaponPart> hullList;
	private ArrayList<WeaponPart> sailList;
	private ArrayList<WeaponPart> stabilizerList;

	private ArrayList<WeaponPart> cannonList;
	private ArrayList<WeaponPart> subcannonList;
	private ArrayList<WeaponPart> missileList;
	private ArrayList<WeaponPart> meleeList;

	private ArrayList< ArrayList<WeaponPart>> weaponList;
    //parts csv
    private final String ENGINE_FILE_NAME = "data/engines.csv";
    private final String FIGUREHEAD_FILE_NAME = "data/figureheads.csv";
    private final String SAIL_FILE_NAME = "data/sails.csv";
    private final String HULL_FILE_NAME = "data/hulls.csv";
    private final String STABILIZER_FILE_NAME = "data/stabilizers.csv";
    //weapon csv
    private final String MISSILE_FILE_NAME = "data/missiles.csv";
    private final String CANNON_FILE_NAME = "data/cannons.csv";
    private final String MELEE_FILE_NAME = "data/melee.csv";
    private final String SUBCANNON_FILE_NAME = "data/subcannons.csv";

    private final String CLASS_NAME = "WeaponPartManager";
    //parts
    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;
    //weapon
    private final int CANNONS = 11;
    private final int MELEES = 12;
    private final int MISSILES = 13;
    private final int SUBCANNONS = 14;

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load ship' information from file.
     */
	public WeaponPartManager() throws DataException{
		engineList = new ArrayList<WeaponPart>();
		figureheadList = new ArrayList<WeaponPart>();
		sailList = new ArrayList<WeaponPart>();
		hullList = new ArrayList<WeaponPart>();
		stabilizerList = new ArrayList<WeaponPart>();

		cannonList = new ArrayList<WeaponPart>();
		subcannonList = new ArrayList<WeaponPart>();
		missileList = new ArrayList<WeaponPart>();
		meleeList = new ArrayList<WeaponPart>();
		weaponList = new ArrayList< ArrayList<WeaponPart>>();
		
        load();
	}

	public ArrayList<WeaponPart> getList(int weaponPartClass){
		switch(weaponPartClass){
			case CANNONS:
				return cannonList;
			case MELEES:
				return meleeList;
			case MISSILES:
				return missileList;
			case SUBCANNONS:
				return subcannonList;
			case ENGINES:
				return engineList;
			case HULLS:
				return hullList;
			case SAILS:
				return sailList;
			case FIGUREHEADS:
				return figureheadList;
			case STABILIZERS:
				return stabilizerList;
		}
		return cannonList;
	}

	public void load() throws DataException{
		loadClass(ENGINES);
		loadClass(HULLS);
		loadClass(SAILS);
		loadClass(FIGUREHEADS);
		loadClass(STABILIZERS);

		loadClass(SUBCANNONS);
		loadClass(CANNONS);
		loadClass(MISSILES);
		loadClass(MELEES);
		weaponList.add(missileList);
		weaponList.add(cannonList);
		weaponList.add(meleeList);
		weaponList.add(subcannonList);
	}

	public void loadClass(int weaponPartClass) throws DataException{
		String filename = "";
		boolean isPart = true;
		
		switch(weaponPartClass) {
			case SAILS :
				filename = SAIL_FILE_NAME;
				break;
			case ENGINES :
				filename = ENGINE_FILE_NAME;
				break;
			case FIGUREHEADS :
				filename = FIGUREHEAD_FILE_NAME;
				break;
			case HULLS :
				filename = HULL_FILE_NAME;
				break;
			case STABILIZERS :
				filename = STABILIZER_FILE_NAME;
				break;
			case MISSILES:
				filename = MISSILE_FILE_NAME;
				isPart=false;
				break;
			case CANNONS:
				filename = CANNON_FILE_NAME;
				isPart=false;
				break;
			case SUBCANNONS:
				filename = SUBCANNON_FILE_NAME;
				isPart=false;
				break;
			case MELEES:
				filename = MELEE_FILE_NAME;
				isPart=false;
				break;
		}

		if(isPart){
			_loadPart(filename, weaponPartClass);
		} else{
			_loadWeapon(filename, weaponPartClass);
		}
	}

	protected void _loadPart(String filename, int partClass) throws DataException{
		Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File(filename));
            fileIn.useDelimiter("\r\n");
            fileIn.next(); //skip the first line
            //#Name,Speed,HP,Capacity,Weight,Level Required,Gold,Wood,Ore,Plasma Rock,Port
            while (fileIn.hasNext()) {
                String[] data   = fileIn.next().split(",");
               	String name = data[0];
				int speed = Integer.parseInt(data[1]);  
				int hp = Integer.parseInt(data[2]);  
				int capacity = Integer.parseInt(data[3]);  
				int weight = Integer.parseInt(data[4]);  
				int levelReq = Integer.parseInt(data[5]);  
				int gold = Integer.parseInt(data[6]);  
				int wood = Integer.parseInt(data[7]);  
				int ore = Integer.parseInt(data[8]);  
				int prock = Integer.parseInt(data[9]);  
				String port = data[10];  

                //set data to the Ship object
                Part p = new Part();
                p.setName(name);
                p.setSpeed(speed);
                p.setHP(hp);
                p.setCapacity(capacity);
                p.setWeight(weight);
                p.setLevelReq(levelReq);
                p.setGold(gold);
                p.setWood(wood);
                p.setOre(ore);
                p.setProck(prock);
                p.setPort(port);          

                switch(partClass) {
					case SAILS :
						sailList.add(p);
						break;
					case ENGINES :
						engineList.add(p);
						break;
					case FIGUREHEADS :
						figureheadList.add(p);
						break;
					case HULLS :
						hullList.add(p);
						break;
					case STABILIZERS :
						stabilizerList.add(p);
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

	protected void _loadWeapon(String filename, int weaponClass) throws DataException{
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
					case MISSILES:
						missileList.add(p);
						break;
					case CANNONS:
						cannonList.add(p);
						break;
					case SUBCANNONS:
						subcannonList.add(p);
						break;
					case MELEES:
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

	public WeaponPart getPartByTypeAndName(int partType, String name){
		
		ArrayList<WeaponPart> partList = new ArrayList<WeaponPart>();

		switch(partType){
			case FIGUREHEADS:
				partList = figureheadList;
				break;
			case ENGINES:
				partList = engineList;
				break;
			case HULLS:
				partList = hullList;
				break;
			case SAILS:
				partList = sailList;
				break;
			case STABILIZERS:
				partList = stabilizerList;
				break;
		}
		
		for(WeaponPart p: partList){
			if(p.getName().equals(name)){
				return p;
			}
		}
		return null;
	}

	public WeaponPart getWeaponByName(String name){
		for(ArrayList<WeaponPart> list: weaponList){
			for(WeaponPart w: list){
				if(w.getName().equals(name)){
					return w;
				}
			}
		}
		return null;
	}




}