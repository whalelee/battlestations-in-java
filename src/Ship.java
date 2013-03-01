public class Ship {
	private int speed;
	private String name;
	private int hp;
	private int gold;
	private int ore;
	private int wood;
	private int prock;
	private int levelReq;
	private String port;
	private int slots;
	private int capacity;

	public Ship() {
		this.name = "Windrider";

	}

	public int getSpeed(){
		return speed;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public String getPort(){
		return port;
	}

	public void setPort(String port){
		this.port = port;
	}

	public int getSlots(){
		return slots;
	}

	public void setSlots(int slots){
		this.slots = slots;
	}

	public int getCapacity(){
		return capacity;
	}

	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getHP(){
		return hp;
	}

	public void setHP(int hp){
		this.hp = hp;
	}

	public int getGold(){
		return gold;
	}

	public void setGold(int gold){
		this.gold = gold;
	}

	public int getOre(){
		return ore;
	}

	public void setOre(int ore){
		this.ore = ore;
	}

	public int getWood(){
		return wood;
	}

	public void setWood(int wood){
		this.wood = wood;
	}

	public int getProck(){
		return prock;
	}

	public void setProck(int prock){
		this.prock = prock;
	}

	public int getLevelReq(){
		return levelReq;
	}

	public void setLevelReq(int levelReq){
		this.levelReq = levelReq;
	}
	
}