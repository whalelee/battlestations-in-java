import java.io.*;
import java.util.*;
import java.text.*;

public class WeaponPart{

	private String name =" ";
	private int weight;
	private int levelReq;
	private int gold;
	private int wood;
	private int ore;
	private int prock;
	private String port;

	public WeaponPart(){
		this.name = " ";
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}

	public int getLevelReq(){
		return levelReq;
	}
	public void setLevelReq(int levelReq){
		this.levelReq = levelReq;
	}

	public int getGold(){
		return gold;
	}
	public void setGold(int gold){
		this.gold = gold;
	}

	public int getWood(){
		return wood;
	}
	public void setWood(int wood){
		this.wood = wood;
	}

	public int getOre(){
		return ore;
	}
	public void setOre(int ore){
		this.ore = ore;
	}

	public int getProck(){
		return prock;
	}
	public void setProck(int prock){
		this.prock = prock;
	}

	public String getPort(){
		return port;
	}
	public void setPort(String port){
		this.port = port;
	}


	public int getHP(){
		return 0;
	}
	public void setHP(int hp){

	}

	public int getCapacity(){
		return 0;
	}
	public void setCapacity(int capacity){

	}

	public int getSpeed(){
		return 0;
	}
	public void setSpeed(int speed){

	}

	public int getRange(){
		return 0;
	}
	public void setRange(int range){
	
	}

	public int getMinDamage(){
		return 0;
	}
	public void setMinDamage(int minDamage){
	}

	public int getMaxDamage(){
		return 0;
	}
	public void setMaxDamage(int maxDamage){                                                      
	}

	public String toString(){
		if (this.name.equals(" ")){
			return "Nil";
		}
		return "L" + this.levelReq + " - " + this.name;
	}
}