import java.io.*;
import java.util.*;
import java.text.*;

public class BattleManager{
	
	private PlayerManager playerMgr;
	private ShipManager shipMgr;
	private Player defender;
	private Player attacker;
	private int levelDifference;
	private double attackerMultiplier;
	private double defenderMultiplier;

	public BattleManager(PlayerManager playerMgr, ShipManager shipMgr){
		this.playerMgr = playerMgr;
		this.shipMgr = shipMgr;
		
	}

	public void assignPlayer(Player defender, Player attacker){
		this.defender = defender;
		this.attacker = attacker;
	}


	public int getTimeTakenForBattle1(){
		int distance1 = 2500;
		int targetPosition = 1000;
		int targetSpeed = defender.getSpeed();
		System.out.println("***" + defender.getName());
		System.out.println("***" + defender.getSpeed());
		double time = (distance1 - targetPosition) / targetSpeed;
		return (int)time;
	}
	

	public int getPositionForBattle1(String type, int timeTaken){
		switch(type){
			case "attacker":
				return (timeTaken * attacker.getSpeed()) + 0; //0 is the attacker's starting position
				
			case "defender":
				return (timeTaken * defender.getSpeed()) + 1000; //1000 is defender's starting position
				
		}
		return 0;
	}

	public int getCombatXP(int totalDamage, String type) {
		int level =0;
		double multiplier=0;
		switch(type){
			case "attacker":
				multiplier = attackerMultiplier;
				level = attacker.getLevel(); 
				break;
			case "defender":
				multiplier = defenderMultiplier;
				level = defender.getLevel(); 
				break;
		}

		double combatXP = (totalDamage / 300) * level * 5 * multiplier;
		return (int)combatXP;
    }

    public void getMultiplier(){
    	int attackerLevel = attacker.getLevel();
    	int defenderLevel = defender.getLevel();
    	this.levelDifference = Math.abs( attackerLevel - defenderLevel);
    	boolean attackerIsGreater = (attackerLevel > defenderLevel);

		switch(this.levelDifference) {
			case 5:
				if (attackerIsGreater) {
					attackerMultiplier = 1.3;
				} else {
					attackerMultiplier = 0.7;
				}
				break;
			case 4:
				if (attackerIsGreater) {
					attackerMultiplier = 1.2;
				} else {
					attackerMultiplier = 0.8;
				}
				break;
			case 3:
				if (attackerIsGreater) {
					attackerMultiplier = 1.1;
				} else {
					attackerMultiplier = 0.9;
				}
				break;
			default:
				attackerMultiplier = 1.0;
		}

    	defenderMultiplier = 2.0 - attackerMultiplier;
    }

}