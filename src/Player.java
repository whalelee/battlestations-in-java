public class Player{
	
	private String username;
	private String password;
	private char playerType;
	private int level;

	private int gold;
	private int ore;
	private int currentHP;
	private int wood;
	private int prock;
	private int ap;

	private Hangar hangar;
	private Storage storage;

	private double craft;
	private double navigation;
	private double gunnery;
	private int exp;

	private int statsPts;
	private boolean iWin; 
	private int wins;
	private int losses;
	private int totalExp;

	private SISDate joinedDate;        //Format : dd MMM yyyy
	private SISDate lastLoggedInDate;

	private final int DEFAULT_GOLD = 5000;
	private final int DEFAULT_WOOD = 500;
	private final int DEFAULT_ORE = 50;
	private final int DEFAULT_PROCK = 5;
	private final int DEFAULT_LEVEL = 1;
	private final int DEFAULT_AP = 120;
	private final double DEFAULT_NAVIGATION = 0;
	private final double DEFAULT_GUNNERY = 0;
	private final double DEFAULT_CRAFT = 0;
	private final int DEFAULT_EXP = 0;

	private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

	//player constructor with username and password
	public Player(String username, String password, char playerType){
		this.username 	= username;
		this.password 	= password;
		this.playerType = playerType;
		
		this.gold		= DEFAULT_GOLD;
		this.wood		= DEFAULT_WOOD;
		this.ore		= DEFAULT_ORE;
		this.prock		= DEFAULT_PROCK;
		this.level		= DEFAULT_LEVEL;
		this.ap			= DEFAULT_AP;
		this.navigation	= DEFAULT_NAVIGATION;
		this.gunnery	= DEFAULT_GUNNERY;
		this.craft		= DEFAULT_CRAFT;
		this.exp		= DEFAULT_EXP;

		this.hangar 	= new Hangar();
		this.storage 	= new Storage();


		this.joinedDate = new SISDate();
		this.lastLoggedInDate = new SISDate();
	}

	public SISDate getLastLoggedInDate(){
		return lastLoggedInDate;
	}

	public void setLastLoggedInDate(SISDate d){
		this.lastLoggedInDate = d;
	}

	public SISDate getJoinedDate(){
		return joinedDate;
	}

	public void setJoinedDate(SISDate d){
		this.joinedDate = d;
	}

	public String getName(){
		return username;
	}

	public void setName(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public char getPlayerType(){
		return playerType;
	}

	public void setPlayerType(char playerType){
		this.playerType = playerType;
	}

	public int getAP(){
		return ap;
	}

	public void setAP(int ap){
		if (ap > 500){
			ap = 500;
		}
		this.ap = ap;
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

	public int getCurrentHP(){
		return currentHP;
	}

	public void setCurrentHP(int currentHP){
		this.currentHP = currentHP;
	}

	public void increaseCurrentHP(int increment) {
		this.currentHP += increment;
	}

	public int getTotalHP(){
		Ship s = this.hangar.getShip();
		int shipHP = s.getHP();
		double hpInDouble = ((100 + this.craft)/100) * shipHP;
		return (int)hpInDouble;

	}

	public String getStatus(){
		int totalHP = this.getTotalHP();
		int currentHP = this.getCurrentHP();
		if (totalHP == currentHP){
			return "Perfect";
		}
		return "Damaged";

	}

	public int getSpeed(){
		Ship s = this.hangar.getShip();
		int shipSpeed = s.getSpeed();

		double speedInDouble =  shipSpeed + navigation;

		return (int)speedInDouble;

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

	public double getCraft(){
		return craft;
	}

	public void addCraft(int statsPts){
		this.craft += statsPts;
	}


	public double getGunnery(){
		return gunnery;
	}

	public void setCraft(double craft){
		this.craft = craft;
	}

	public void setGunnery(double gunnery){
		this.gunnery = gunnery;
	}

	public void addGunnery(int statsPts){
		this.gunnery += statsPts;
	}

	public double getNavigation(){
		return navigation;
	}

	public void setNavigation(double navigation){
		this.navigation = navigation;
	}

	public void addNavigation(int statsPts){
		this.navigation += statsPts;
	}

	public int getStatsPts(){
		return statsPts;
	}

	public void setStatsPts(int statsPts){
		this.statsPts = statsPts;
	}

	public int getWins(){
		return wins;
	}

	public int getLosses(){
		return losses;
	}

	public void won(boolean iWin){
		
		this.iWin = iWin;
		
		if (iWin){
			wins++;
		}else{
			losses++;
		}

	}

	public int getExp(){
		return exp;
	}

	public void setExp(int exp){
		this.exp = exp;
	}

	public void increaseExp(int exp){
		totalExp += exp;
	}

	public int getLevel(){
		return level;
	}

	public int getExpectedLevel(){
		
		return this.convertExpToLevel(1);
 	}

 	//recursive function
 	public int convertLevelToExp(int level){
 		//base case, cannot to down anymore
 		if (level <= 1){
 			return 0;
 		} 
 		//cap, cannot go up anymore
 		if (level > 100){
 			return this.convertLevelToExp(100);
 		}
 		//recursive steps
 		if (level > 1){
 			return this.convertLevelToExp(level -1 ) + ((level - 1)*100);
 		}
 		return 0;
 	}

 	public int convertExpToLevel(int expectedLevel){
 		
 		if (expectedLevel > 100){
 			return 100;
 		}
 		
 		int expAtExpectedLevel = this.convertLevelToExp(expectedLevel);
 		
 		if(this.exp == expAtExpectedLevel){
 			return expectedLevel;
 		}

 		if(this.exp < expAtExpectedLevel){
 			return expectedLevel -1;
 		}

 		if(this.exp > expAtExpectedLevel){
 			return this.convertExpToLevel(expectedLevel+1);
 		}
 		return 1;

 	}

	public void setLevel(int level){
		this.level = level;
	}

	public void addLevel(){
		level++;
	}

	public Hangar getHangar(){
		return this.hangar;
	}

	public void setHangar(Hangar h){
		this.hangar = h;
	}

	public Storage getStorage(){
		return this.storage;
	}

	public void setStorage(Storage s){
		this.storage = s;
	}


	public void resetLoginToday(){
		SISDate lastLoggedIn = this.lastLoggedInDate;
		SISDate today = new SISDate();
		if (!today.equals(lastLoggedIn)){
			increaseAP(120);
		}
		this.lastLoggedInDate = today;

	}

	public void increaseAP(int increment){
		int newAP = this.ap + increment;
		if (newAP > 500){
			newAP = 500;
		}
		this.setAP(newAP);
	}

	private final int GOLD = 1;
	private final int WOOD = 2;
	private final int ORE = 3;
	private final int PROCK = 4;

	public void increaseGold(int increment){
		int newGold = this.gold + increment;
		this.setGold(newGold);
	}

	public void increaseWood(int increment){
		int newWood = this.wood + increment;
		this.setWood(newWood);
	}

	public void increaseOre(int increment){
		int newOre = this.ore + increment;
		this.setOre(newOre);
	}

	public void increaseProck(int increment){
		int newProck = this.prock + increment;
		this.setProck(newProck);
	}

	public void deductResources(WeaponPart w){
		//deduct gold by ...
		increaseGold(w.getGold()*(-1));
		increaseWood(w.getWood()*(-1));
		increaseOre(w.getOre()*(-1));
		increaseProck(w.getProck()*(-1));
	}


	public void addToStorage(WeaponPart w){
		//deduct resource
		deductResources(w);
		//add weapon to storage
		this.storage.addToWeaponList(w);
	}


	public void addToStorage(int partType, WeaponPart w){
		//deduct resource
		deductResources(w);
		//add part to storage
		switch (partType){
			case FIGUREHEADS:
				this.storage.addToFigureheadList(w);
				break;
			case ENGINES:
				break;
		}
		

	}
}