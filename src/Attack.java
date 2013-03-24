public class Attack{
	private String name;
	private String weaponName;
	private int distance;
	private int damage;

	public Attack(String name, String weaponName, int distance, int damage){
		this.name = name;
		this.weaponName = weaponName;
		this.distance = distance;
		this.damage = damage;

	}

	public String getName(){
		return name;
	}

	public String getWeaponName(){
		return weaponName;
	}

	public int getDistance(){
		return distance;
	}

	public int getDamage(){
		return damage;
	}

	public String toString(){
		return name + " attacks with " + weaponName +" at " + distance + " m (" + damage +
		" damage)";
	}

}