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
	private int attackerTotalDamage;
	private int defenderTotalDamage;
	private int timeForBattle1;
	private int attackerPositionForBattle1;
	private int defenderPositionForBattle1;
	private int rangeForBattle1;

	private Battle b;

	public BattleManager(PlayerManager playerMgr, ShipManager shipMgr){
		this.playerMgr = playerMgr;
		this.shipMgr = shipMgr;
		
	}

	public void assignPlayer(Player attacker, Player defender){
		this.defender = defender;
		this.attacker = attacker;
	}

	public Battle startBattle(){
		b = new Battle(this.attacker, this.defender);
		return b;
		// b.getAttackerTotalDamage();
		// b.getDefenderTotalDamage();
		// b.getAttackerCombatXP();
		// b.getDefenderCombatXP();
		// b.getAttackerGold();
		// b.getDefenderGold();
	}


}