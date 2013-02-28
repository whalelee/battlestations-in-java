public class Player{
	
	private String username;
	private String password;
	private char playerType;
	private int level;

	private int ap;
	private int gold;
	private int ore;
	private int hp;
	private int wood;
	private int prock;

	private int craft;
	private int speed;
	private int navigation;
	private int gunnery;
	private int statsPts;
	private boolean iWin; 
	private int wins;
	private int losses;
	private int totalExp;

	private SISDate joinedDate;        //Format : dd/MM/yyyy 00:00

	//player constructor with username and password
	public Player(String username, String password){
		this.username = username;
		this.password = password;

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

	public char playerType(){
		return playerType;
	}

	public void setPlayerType(char playerType){
		this.playerType = playerType;
	}

	public int getAP(){
		return ap;
	}

	public void setAP(int ap){
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
		return hp;
	}

	public void setHP(int hp){
		this.hp = hp;
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

	public int getCraft(){
		return craft;
	}

	public void addCraft(int statsPts){
		this.craft += statsPts;
	}

	public int getSpeed(){
		return speed;
	}

	public int getGunnery(){
		return gunnery;
	}

	public void addGunnery(int statsPts){
		this.gunnery += statsPts;
	}

	public int getNavigation(){
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

	public void addLevel(){
		level++;
	}
}