import java.io.*;
import java.util.*;
import java.text.*;

public class BattleManager{
	
	private PlayerManager playerMgr;
	private ShipManager shipMgr;
	private Player defender;
	private Player attacker;

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
}