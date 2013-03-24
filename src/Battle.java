import java.io.*;
import java.util.*;
import java.text.*;
import java.math.RoundingMode;

public class Battle{
	private Player attacker;
	private Player defender;

	private int attackerTotalDamage;
	private int defenderTotalDamage;

	private int attackerCombatXP;
	private int defenderCombatXP;

	private int attackerGold;
	private int defenderGold;

	private ArrayList<Attack> attackList1 = new ArrayList<Attack>();

	private ArrayList<Attack> attackList2 = new ArrayList<Attack>();

	private ArrayList<Attack> attackList3 = new ArrayList<Attack>();

	public Battle(Player attacker, Player defender){
		this.defender = defender;
		this.attacker = attacker;
		
		// // now start first firefight
		// int timeForFireFight1 = (int) ((2500 - 1000) / defender.getSpeed());
		// FireFight ff1 = new FireFight(attacker, defender, timeForFireFight1);
		
		// // update total damage, combat xp and gold so far
		// this.updateStats(ff1); 

		// // check if still carry on with FireFight2
		// if (this.carryOn()){
		// 	int timeForFireFight2 = (int) ((5000 - 1000) / defender.getSpeed());
		// 	FireFight ff2 = new FireFight(attacker, defender, timeForFireFight2);
		// 	this.updateStats(ff2);

		// 	if (this.carryOn()){
		// 		int timeForFireFight3 = (int) ((7500 - 1000) / defender.getSpeed());
		// 		FireFight ff3 = new FireFight(attacker, defender, timeForFireFight3);
		// 		this.updateStats(ff3);
		// 	}
		// }


	}

	public Battle fireFight1(){
		// now start first firefight1
		double time = (2500 - 1000) / defender.getSpeed() + 1; // add 1 to round up
		
		
		int timeForFireFight1 = (int) time;
		FireFight ff1 = new FireFight(attacker, defender, timeForFireFight1);
		
		// update total damage, combat xp and gold so far
		this.attackList1 = this.updateStats(ff1); 

		return this;
	}

	public Battle  fireFight2(){
		// now start first firefight1
		double time = (5000 - 1000) / defender.getSpeed() + 1; // add 1 to round up
		
		
		int timeForFireFight1 = (int) time;
		FireFight ff1 = new FireFight(attacker, defender, timeForFireFight1);
		
		// update total damage, combat xp and gold so far
		this.attackList2 = this.updateStats(ff1); 

		return this;
	}

	public Battle fireFight3(){
		// now start first firefight1
		double time = (7500 - 1000) / defender.getSpeed() + 1; // add 1 to round up
		
		
		int timeForFireFight1 = (int) time;
		FireFight ff1 = new FireFight(attacker, defender, timeForFireFight1);
		
		// update total damage, combat xp and gold so far
		this.attackList3 = this.updateStats(ff1); 

		return this;
	}

	public boolean carryOn(){
		int attackerHP = this.attacker.getCurrentHP();
		int defenderHP = this.defender.getCurrentHP();

		if(attackerTotalDamage >= defenderHP || 
			defenderTotalDamage >= attackerHP){
			return false;
		}
		return true;
	}

	public ArrayList<Attack> updateStats(FireFight ff) {
		attackerTotalDamage += ff.getAttackerTotalDamage();
		defenderTotalDamage += ff.getDefenderTotalDamage();

		attackerCombatXP += ff.getAttackerCombatXP();
		defenderCombatXP += ff.getDefenderCombatXP();

		attackerGold += ff.getAttackerGold();
		defenderGold += ff.getDefenderGold();

		return ff.getAttackList();

	}

	public int getAttackerTotalDamage() {
    	return attackerTotalDamage;
    }

    public int getDefenderTotalDamage() {
    	return defenderTotalDamage;
    }

    public int getAttackerCombatXP() {
    	return attackerCombatXP;
    }

    public int getDefenderCombatXP() {
    	return defenderCombatXP;
    }

    public int getAttackerGold() {
    	return attackerGold;
    }

    public int getDefenderGold() {
    	return defenderGold;
    }

    public ArrayList<Attack> getAttackList1(){
    	return attackList1;
    }

    public ArrayList<Attack> getAttackList2(){
    	return attackList2;
    }

    public ArrayList<Attack> getAttackList3(){
    	return attackList3;
    }

    public String getOutcome() {
    	if (defenderTotalDamage - attacker.getCurrentHP() <= 0) {
    		return "You lose!";
    	} else if (attackerTotalDamage - defender.getCurrentHP() <= 0) {
    		return "You win!";
    	} else {
    		return "It is a draw!";
    	}
    } 

}