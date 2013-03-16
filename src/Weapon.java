public class Weapon extends WeaponPart{
	
	private int range;
	private int minDamage;
	private int maxDamage;
	


	public int getRange(){
		return range;
	}
	public void setRange(int range){
		this.range = range;
	}

	public int getMinDamage(){
		return minDamage;
	}
	public void setMinDamage(int minDamage){
		this.minDamage = minDamage;
	}

	public int getMaxDamage(){
		return maxDamage;
	}
	public void setMaxDamage(int maxDamage){
		this.maxDamage = maxDamage;
	}

}