import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */
public class Storage{
	private ArrayList<WeaponPart> figureheadList;
	private ArrayList<WeaponPart> sailList;
	private ArrayList<WeaponPart> stabilizerList;
	private ArrayList<WeaponPart> engineList;
	private ArrayList<WeaponPart> hullList;
	
	private ArrayList<WeaponPart> weaponList;

	private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

	public Storage(){
		figureheadList = new ArrayList<WeaponPart>();
		sailList = new ArrayList<WeaponPart>();
		stabilizerList = new ArrayList<WeaponPart>();
		engineList = new ArrayList<WeaponPart>();
		hullList = new ArrayList<WeaponPart>();
		weaponList = new ArrayList<WeaponPart>();
	}

	public ArrayList<WeaponPart> getFigureheadList(){
		return figureheadList;
	}

	public void setFigureheadList(ArrayList<WeaponPart> f){
		this.figureheadList = f;;
	}

	public void addToFigureheadList(WeaponPart p){
		this.figureheadList.add(p);
	}

	public String getListToString(int partType){
		ArrayList<WeaponPart> list = new ArrayList<WeaponPart>();

		switch(partType){
			case FIGUREHEADS:
				list = figureheadList;
				break;
			case HULLS:
				list = hullList;
				break;
			case STABILIZERS:
				list = stabilizerList;
				break;
			case SAILS:
				list = sailList;
				break;
			case ENGINES:
				list = engineList;
				break;

		}
		
		if (list.size() ==0){
			return " ";
		}
		String result ="";
		for(WeaponPart p : list){
			result += p.getName(); 
			result +=":";
		}

		return result.substring(0, result.length()-1); 
		// return the whole string except for the last character
	}

	public ArrayList<WeaponPart> getSailList(){
		return sailList;
	}

	public void setSailList(ArrayList<WeaponPart> s){
		this.sailList = s;
	}

	public void addToSailList(WeaponPart p){
		this.sailList.add(p);
	}

	public ArrayList<WeaponPart> getStabilizerList(){
		return stabilizerList;
	}

	public void setStabilizerList(ArrayList<WeaponPart> s){
		this.stabilizerList = s;
	}
	
	public void addToStabilizerList(WeaponPart p){
		this.stabilizerList.add(p);
	}

	public ArrayList<WeaponPart> getEngineList(){
		return engineList;
	}

	public void setEngineList(ArrayList<WeaponPart> e){
		this.engineList = e;;
	}

	public void addToEngineList(WeaponPart p){
		this.engineList.add(p);
	}

	public ArrayList<WeaponPart> getHullList(){
		return hullList;
	}
	
	public void setHullList(ArrayList<WeaponPart> h){
		this.hullList = h;
	}

	public void addToHullList(WeaponPart p){
		this.hullList.add(p);
	}

	public ArrayList<WeaponPart> getWeaponList(){
		return weaponList;
	}

	public void setWeaponList(ArrayList<WeaponPart> w){
		this.weaponList = w;
	}
	
	public void addToWeaponList(WeaponPart w){
		this.weaponList.add(w);
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