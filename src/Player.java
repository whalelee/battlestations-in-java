public class Player{
	
	private String username;
	private String password;
	private char playerType;
	private int level;

	private int gold;
	private int ore;
	private int hp;
	private int wood;
	private int prock;
	private int ap;

	private Hangar hangar;
	private double craft;
	private double navigation;
	private double gunnery;


	private int speed;
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

	//player constructor with username and password
	public Player(String username, String password, char playerType){
		this.username = username;
		this.password = password;
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
		this.hangar = new Hangar();



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

	public int getHP(){
		Ship s = this.hangar.getShip();
		int shipHP = s.getHP();
		return int(((100 + this.craft)/100) * shipHP);

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

	public int getSpeed(){
		return speed;
	}

	public double getGunnery(){
		return gunnery;
	}

	public void addGunnery(int statsPts){
		this.gunnery += statsPts;
	}

	public double getNavigation(){
		return navigation;
	}

	public void addNavigation(int statsPts){
		this.navigation += statsPts;
		speed += navigation;
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

	public int totalExp(){
		return totalExp;
	}

	public void increaseExp(int exp){
		totalExp += exp;
	}

	public int getLevel(){
		return level;
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
}