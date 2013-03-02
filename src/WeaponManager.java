import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */
public class WeaponManager{
	private ArrayList<Weapon> cannonList;
	private ArrayList<Weapon> subcannonList;
	private ArrayList<Weapon> missileList;
	private ArrayList<Weapon> meleeList;

	private ArrayList< ArrayList<Weapon>> weaponList;
    
    private final String MISSILE_FILE_NAME = "data/missiles.csv";
    private final String CANNON_FILE_NAME = "data/cannons.csv";
    private final String MELEE_FILE_NAME = "data/melee.csv";
    private final String SUBCANNON_FILE_NAME = "data/subcannons.csv";

    private final String CLASS_NAME = "WeaponManager";


    private final int CANNONS = 1;
    private final int MELEES = 2;
    private final int MISSILES = 3;
    private final int SUBCANNONS = 4;

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load ship' information from file.
     */
	public WeaponManager() throws DataException{
		cannonList = new ArrayList<Weapon>();
		subcannonList = new ArrayList<Weapon>();
		missileList = new ArrayList<Weapon>();
		meleeList = new ArrayList<Weapon>();
		weaponList = new ArrayList< ArrayList<Weapon>>();
        load();
	}

	public ArrayList<Weapon> getWeaponList(int weaponClass){
		switch(weaponClass){
			case CANNONS:
				return cannonList;
			case MELEES:
				return meleeList;
			case MISSILES:
				return missileList;
			case SUBCANNONS:
				return subcannonList;
		}
		return cannonList;
	}

	public void load() throws DataException{
		loadMissile();
		loadCannon();
		loadSubcannon();
		loadMelee();
		weaponList.add(missileList);
		weaponList.add(cannonList);
		weaponList.add(meleeList);
		weaponList.add(subcannonList);
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
						cannonList.add(p);
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

	public Weapon getWeaponByName(String name){
		for(ArrayList<Weapon> list: weaponList){
			for(Weapon w: list){
				if(w.getName().equals(name)){
					return w;
				}
			}
		}
		return null;
	}
}