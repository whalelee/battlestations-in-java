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

	public void fight(int input, ArrayList<Player> targets){
		appCtrl.deductAPForPVP();
		Player me = appCtrl.getPlayerLoggedIn();
		String myName = me.getName();
		Player target = targets.get(input-1);
		String targetName = target.getName();

		System.out.print(myName + "'s HP: ");
		System.out.println(me.getCurrentHP() + " / " + me.getTotalHP());
		System.out.print(targetName + "'s HP: ");
		System.out.println(target.getCurrentHP() + " / " + target.getTotalHP());

		System.out.println(myName + " attacks with xxx at 1240m (xxx damage)");
	}

}