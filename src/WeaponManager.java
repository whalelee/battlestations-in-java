import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */
public class WeaponManager{
	private ArrayList<Cannon> cannonList;
	private ArrayList<Subcannon> subcannonList;
	private ArrayList<Missile> missileList;
	private ArrayList<Melee> meleeList;
    private final String MISSILE_FILE_NAME = "data/missiles.csv";
    private final String CANNON_FILE_NAME = "data/cannons.csv";
    private final String MELEE_FILE_NAME = "data/melee.csv";
    private final String SUBCANNON_FILE_NAME = "data/subcannons.csv";

    private final String CLASS_NAME = "WeaponManager";

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load ship' information from file.
     */
	public WeaponManager(){
		cannonList = new ArrayList<Cannon>();
		subcannonList = new ArrayList<Subcannon>();
		missileList = new ArrayList<Missile>();
		meleeList = new ArrayList<Melee>();
        load();
	}

	public void load(){
		loadMissile();
		loadCannon();
		loadSubcannon();
		loadMelee();
	}

	public void loadMissile(){

	}

	public void loadCannon(){

	}

	public void loadSubcannon(){

	}

	public void loadMelee(){

	}
}