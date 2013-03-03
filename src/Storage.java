import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */
public class Storage{
	private ArrayList<Part> figureheadList;
	private ArrayList<Part> sailList;
	private ArrayList<Part> stabilizerList;
	private ArrayList<Part> engineList;
	private ArrayList<Part> hullList;
	
	private ArrayList<Weapon> weaponList;

	private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

	public Storage(){
		figureheadList = new ArrayList<Part>();
		sailList = new ArrayList<Part>();
		stabilizerList = new ArrayList<Part>();
		engineList = new ArrayList<Part>();
		hullList = new ArrayList<Part>();
		weaponList = new ArrayList<Weapon>();
	}

	public ArrayList<Part> getFigureheadList(){
		return figureheadList;
	}

	public void setFigureheadList(ArrayList<Part> f){
		this.figureheadList = f;;
	}

	public void addToFigureheadList(Part p){
		this.figureheadList.add(p);
	}

	public String getListToString(int partType){
		ArrayList<Part> list = new ArrayList<Part>();

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
		for(Part p : list){
			result += p.getName(); 
			result +=":";
		}

		return result.substring(0, result.length()-1); 
		// return the whole string except for the last character
	}

	public ArrayList<Part> getSailList(){
		return sailList;
	}

	public void setSailList(ArrayList<Part> s){
		this.sailList = s;
	}

	public void addToSailList(Part p){
		this.sailList.add(p);
	}

	public ArrayList<Part> getStabilizerList(){
		return stabilizerList;
	}

	public void setStabilizerList(ArrayList<Part> s){
		this.stabilizerList = s;
	}
	
	public void addToStabilizerList(Part p){
		this.stabilizerList.add(p);
	}

	public ArrayList<Part> getEngineList(){
		return engineList;
	}

	public void setEngineList(ArrayList<Part> e){
		this.engineList = e;;
	}

	public void addToEngineList(Part p){
		this.engineList.add(p);
	}

	public ArrayList<Part> getHullList(){
		return hullList;
	}
	
	public void setHullList(ArrayList<Part> h){
		this.hullList = h;
	}

	public void addToHullList(Part p){
		this.hullList.add(p);
	}

	public ArrayList<Weapon> getWeaponList(){
		return weaponList;
	}

	public void setWeaponList(ArrayList<Weapon> w){
		this.weaponList = w;
	}
	
	public void addToWeaponList(Weapon w){
		this.weaponList.add(w);
	}		

	public String getWeaponListToString(){
		
		if (weaponList.size() ==0){
			return " ";
		}
		String result ="";
		for(Weapon p : weaponList){
			result += p.getName(); 
			result +=":";
		}
		return result.substring(0, result.length()-1); 
		// return the whole string except for the last character
	}

}