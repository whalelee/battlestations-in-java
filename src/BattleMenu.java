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
		Player me = appCtrl.getPlayerLoggedIn();
		Player target = targets.get(input-1);

		System.out.print(me.getName() + "'s HP: ");
		System.out.println(me.getCurrentHP() + " / " + me.getTotalHP());
		System.out.print(target.getName() + "'s HP: ");
		System.out.println(target.getCurrentHP() + " / " + target.getTotalHP());
	}

}