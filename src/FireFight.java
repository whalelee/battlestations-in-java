import java.io.*;
import java.util.*;
import java.text.*;

public class FireFight{
	private int time;
	private Player attacker;
	private Player defender;
	private int attackerPosition;
	private int defenderPosition;
	private int distanceApart;
	private final int DEFENDER_START = 1000;
	private ArrayList<Attack> attackList;
	private int attackerTotalDamage;
	private int defenderTotalDamage;
	private ArrayList<WeaponPart> defenderWeapons;
	private ArrayList<WeaponPart> attackerWeapons;
	private int attackerCombatXP;
	private int defenderCombatXP;
	private int attackerGold;
	private int defenderGold;
	private double attackerMultiplier;
	private double defenderMultiplier;


	public FireFight(Player attacker, Player defender, int time){
		// assign
		this.defender = defender;
		this.attacker = attacker;
		this.time = time;

		// calculate positions based on assignment of players and time of firefight
		this.defenderPosition = this.time * defender.getSpeed() + DEFENDER_START;
		this.attackerPosition = this.time * attacker.getSpeed();

		this.distanceApart = this.defenderPosition - this.attackerPosition;

		// calculate multipler based on assignment of players
		this.setMultiplier();

		// calculate weapons used based on the positions
		this.defenderWeapons = this.getWeaponInRange(this.defender);
		this.attackerWeapons = this.getWeaponInRange(this.attacker);

		// calculate attacks based on weapons
		this.setAttacks();

		// calculate combatXP and combatGold based on attacks AND multipliers
		this.setCombatXPAndGold();

		// now you are free to get damage dealt out, XP gained, and gold gained
	}

	public ArrayList<WeaponPart> getWeaponInRange(Player target) {
		System.out.println(target.getName());
        ArrayList<WeaponPart> myList = target.getHangar().getWeaponList(); 
        // need a weaponpart resultList
        ArrayList<WeaponPart> results = new  ArrayList<WeaponPart>();

        for(WeaponPart wp: myList){
            // check if w.getRange is within the range
            if (wp.getRange() >= this.distanceApart){
                results.add(wp);
            }
            // if it is , add w to result list
        }

        return results;// return resultList
	}

	public void setAttacks() {
		Random r = new Random();
        this.attackList = new ArrayList<Attack>();
		attackerTotalDamage = 0;

        for (WeaponPart weapon: this.attackerWeapons){
            int maxDamage = weapon.getMaxDamage();
            int minDamage = weapon.getMinDamage();
            int damageRange = maxDamage - minDamage;
            int randomDamage = r.nextInt(damageRange+1);
            int actualDamage = minDamage+randomDamage;
            Attack a = new Attack(attacker.getName(), weapon.getName(), this.distanceApart, actualDamage);
            this.attackList.add(a);
            attackerTotalDamage += actualDamage;
        }

        defenderTotalDamage = 0;
        for (WeaponPart weapon: this.defenderWeapons){
            int maxDamage = weapon.getMaxDamage();
            int minDamage = weapon.getMinDamage();
            int damageRange = maxDamage - minDamage;
            int randomDamage = r.nextInt(damageRange+1);
            int actualDamage = minDamage+randomDamage;
            Attack a = new Attack(defender.getName(), weapon.getName(), this.distanceApart, actualDamage);
            this.attackList.add(a);
            defenderTotalDamage += actualDamage;
        }

	}

	public void setCombatXPAndGold() {
		int level			= 0;
		double multiplier	= 0;
		
		double combatXP		= (attackerTotalDamage / 300) * attacker.getLevel() * 5 * attackerMultiplier;
		attackerCombatXP	= (int) combatXP;
		
		combatXP			= (defenderTotalDamage / 300) * defender.getLevel() * 5 * defenderMultiplier;
		defenderCombatXP	= (int) combatXP;
		
		double gold			= (attackerTotalDamage / 300) * attacker.getLevel() * attackerMultiplier;
		attackerGold		= (int) gold;
		
		gold				= (defenderTotalDamage / 300) * defender.getLevel() * defenderMultiplier;
		defenderGold		= (int) gold;
    }

    public void setMultiplier(){
    	int attackerLevel = attacker.getLevel();
    	int defenderLevel = defender.getLevel();
    	int levelDifference = Math.abs( attackerLevel - defenderLevel);
    	boolean attackerIsGreater = (attackerLevel > defenderLevel);

		switch(levelDifference) {
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

    public int getAttackerTotalDamage() {
    	return this.attackerTotalDamage;
    }

    public int getDefenderTotalDamage() {
    	return this.defenderTotalDamage;
    }

    public int getAttackerCombatXP() {
    	return this.attackerCombatXP;
    }

    public int getDefenderCombatXP() {
    	return this.defenderCombatXP;
    }

    public int getAttackerGold() {
    	return this.attackerGold;
    }

    public int getDefenderGold() {
    	return this.defenderGold;
    }

    public ArrayList<Attack> getAttackList(){
    	return this.attackList;
    }
}