import java.io.*;
import java.util.*;
import java.text.*;

public class Hangar {
	private int totalSpeed;
	private Ship ship;
	private WeaponPart figurehead;
	private WeaponPart sail;
	private WeaponPart stabilizer;
	private WeaponPart hull;
	private WeaponPart engine;
	private ArrayList<WeaponPart> weaponList;


	public Hangar() {
		this.ship = new Ship();
		this.figurehead = new WeaponPart();
		this.sail = new WeaponPart();
		this.stabilizer = new WeaponPart();
		this.hull = new WeaponPart();
		this.engine = new WeaponPart(); 
		weaponList = new ArrayList<WeaponPart>();
	}

	public Hangar(Ship ship) {
		this.ship = ship;
		this.figurehead = new WeaponPart();
		this.sail = new WeaponPart();
		this.stabilizer = new WeaponPart();
		this.hull = new WeaponPart();
		this.engine = new WeaponPart(); 
		weaponList = new ArrayList<WeaponPart>();
	}

	public Ship getShip(){
		return ship;
	}

	public void setShip(Ship s){
		ship = s;
	}
	

	public WeaponPart getFigurehead(){
		return figurehead;
	}

	public void setFigurehead(WeaponPart figurehead){
		this.figurehead = figurehead;
	}


	public WeaponPart getSail(){
		return sail;
	}

	
	public void setSail(WeaponPart sail){
		this.sail = sail;
	}

	public WeaponPart getStabilizer(){
		return stabilizer;
	}

	public void setStabilizer(WeaponPart stabilizer){
		this.stabilizer = stabilizer;
	}


	public WeaponPart getHull(){
		return hull;
	}

	public void setHull(WeaponPart hull){
		this.hull = hull;
	}



	public WeaponPart getEngine(){
		return engine;
	}

	public void setEngine(WeaponPart engine){
		this.engine = engine;
	}


	public ArrayList<WeaponPart> getWeaponList(){
		return weaponList;
	}

	public void addWeapon(WeaponPart w){
		weaponList.add(w);
	}

	public void removeWeapon(int index){
		weaponList.remove(index);
	}

	public String getWeaponListToString(){
		
		if (weaponList.size() ==0){
			return " ";
		}
		String result ="";
		for(WeaponPart p : weaponList){
			result += p.getName(); 
			result +=":";
		}
		return result.substring(0, result.length()-1); 
		// return the whole string except for the last character
	}
}