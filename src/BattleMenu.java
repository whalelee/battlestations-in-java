import java.io.*;
import java.util.*;
import java.text.*;

public class BattleMenu{
	private AppController appCtrl;
	private Scanner sc;

	private ArrayList<Player> targets;

	public BattleMenu(AppController appCtrl){
		this.appCtrl = appCtrl;
		sc = new Scanner(System.in);
	}

	public void fight(int input, ArrayList<Player> targets) throws DataException{
		appCtrl.deductAPForPVP();
		Player me = appCtrl.getPlayerLoggedIn();
		String myName = me.getName();
		Player target = targets.get(input-1);
		String targetName = target.getName();

		// print before FF1
		System.out.print(myName + "'s HP: ");
		System.out.println(me.getCurrentHP() + " / " + me.getTotalHP());
		System.out.print(targetName + "'s HP: ");
		System.out.println(target.getCurrentHP() + " / " + target.getTotalHP());

		// this starts the battle and assigns the person you are attacking
		Battle battle = appCtrl.startBattle(target);

		// this starts firefight 1 and returns the attacks associated with firefight 1
		battle = battle.fireFight1();
		// print the aftermath of FF1 attacks
		for (Attack a: battle.getAttackList1()) {
			System.out.println(a.toString());
		}
		System.out.println();

		// let's check if can go on to FF2
		boolean carryOnForFF2 = battle.carryOn();

		if (carryOnForFF2) {
			int attackerDamage = battle.getAttackerTotalDamage();
			int defenderDamage = battle.getDefenderTotalDamage();
			System.out.print(myName + "'s HP: ");
			System.out.println((me.getCurrentHP() - defenderDamage) + " / " + me.getTotalHP());
			System.out.print(targetName + "'s HP: ");
			System.out.println((target.getCurrentHP() - attackerDamage) + " / " + target.getTotalHP());

			// this starts firefight 1 and returns the attacks associated with firefight 1
			battle = battle.fireFight2();
			// print the aftermath of FF1 attacks
			for (Attack a: battle.getAttackList2()) {
				System.out.println(a.toString());
			}
			System.out.println();
		}
		
		// let's check if can go on to FF3
		boolean carryOnForFF3 = battle.carryOn();

		if (carryOnForFF3) {
			int attackerDamage = battle.getAttackerTotalDamage();
			int defenderDamage = battle.getDefenderTotalDamage();
			System.out.print(myName + "'s HP: ");
			System.out.println((me.getCurrentHP() - defenderDamage) + " / " + me.getTotalHP());
			System.out.print(targetName + "'s HP: ");
			System.out.println((target.getCurrentHP() - attackerDamage) + " / " + target.getTotalHP());

			// this starts firefight 1 and returns the attacks associated with firefight 1
			battle = battle.fireFight3();
			// print the aftermath of FF1 attacks
			for (Attack a: battle.getAttackList3()) {
				System.out.println(a.toString());
			}
			System.out.println();
		}

		//user what weapon to fight at what distance & cost how much damage
		//System.out.println(myName + " attacks with xxx at " + m (xxx damage)");
		System.out.println();
		System.out.println();
		System.out.println(battle.getOutcome());
		int attackerDamage = battle.getAttackerTotalDamage();
		int defenderDamage = battle.getDefenderTotalDamage();
		System.out.print(myName + "'s HP: ");
		System.out.println((me.getCurrentHP() - defenderDamage) + " / " + me.getTotalHP());
		System.out.print(targetName + "'s HP: ");
		System.out.println((target.getCurrentHP() - attackerDamage) + " / " + target.getTotalHP());
		System.out.println(myName + " gained " + battle.getAttackerGold() + " gold.");
		System.out.println(targetName + " gained " + battle.getDefenderGold()  + " gold.");
		System.out.println(myName + " gained " + battle.getAttackerCombatXP()  + " exp.");
		System.out.println(targetName + " gained " + battle.getDefenderCombatXP()  + " exp.");
	}

}