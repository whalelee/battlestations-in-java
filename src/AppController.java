import java.util.*;

public class AppController {
    private PlayerManager playerMgr;
    private ShipManager shipMgr;
    private WeaponPartManager weaponPartMgr;
    private BattleManager battleMgr;

    private Player playerLoggedIn;
    
    private ArrayList<Ship> shipList;
    private ArrayList<WeaponPart> cannonList;
    private ArrayList<WeaponPart> subcannonList;
    private ArrayList<WeaponPart> missileList;
    private ArrayList<WeaponPart> meleeList;

    private ArrayList<WeaponPart> engineList;
    private ArrayList<WeaponPart> figureheadList;
    private ArrayList<WeaponPart> hullList;
    private ArrayList<WeaponPart> sailList;
    private ArrayList<WeaponPart> stabilizerList;

    private final int CANNONS = 11;
    private final int MELEES = 12;
    private final int MISSILES = 13;
    private final int SUBCANNONS = 14;

    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

    public AppController() throws DataException{
        weaponPartMgr = new WeaponPartManager();
        shipMgr = new ShipManager();
        playerMgr = new PlayerManager(shipMgr, weaponPartMgr);
        battleMgr = new BattleManager(playerMgr, shipMgr);

        shipList = shipMgr.getAll();
        cannonList = weaponPartMgr.getList(CANNONS);
        subcannonList = weaponPartMgr.getList(SUBCANNONS);
        missileList = weaponPartMgr.getList(MISSILES);
        meleeList = weaponPartMgr.getList(MELEES);

        engineList = weaponPartMgr.getList(ENGINES);
        figureheadList = weaponPartMgr.getList(FIGUREHEADS);
        sailList = weaponPartMgr.getList(SAILS);
        hullList = weaponPartMgr.getList(HULLS);
        stabilizerList = weaponPartMgr.getList(STABILIZERS);
        
    }


    public Player authenticatePlayer(String username, String password) throws DataException{
         Player p = playerMgr.getPlayerByCredentials(username, password);
         String logMessage = null;
         if (p != null) {
            // authenticate success
            playerLoggedIn = p;
            logMessage = "Successful Login: Username : \"" + username + "\" Password entered \"" + password + "\"";
            playerLoggedIn.resetLoginToday();
            playerMgr.updatePlayer(playerLoggedIn);
            //appLogger.log("INFO", logMessage, playerLoggedIn.getName());
         } else{
            playerLoggedIn = null;
            logMessage = "Login Error. User name : \"" + username
                    + "\" Password entered: \"" + password + "\"";
            //appLogger.log("ERROR", logMessage);
         }

         return playerLoggedIn;    
    }

    public boolean validateUsername(String username){
        Player p = playerMgr.getPlayerByName(username);
        if (p==null){
            return true;
        }
        return false;
    }

    public void logOutPlayer(){
        playerLoggedIn = null;
    }
    /**
     * Get a player given user name.
     *
     * @param userName User name to search for.
     * @return the Player object which has the specified user name.
     *         If no such Player exists, returns null.
     * @thorws when needed
     */
    public void addPlayer(String username, String password, char playerType) throws DataException{
        //instantiate new player with no hangar & no ship
        Player p = new Player(username, password, playerType);
        //instantiate a brand new ship ("windrider") for new player 
        Ship s = shipMgr.getShipByName("Windrider");
        //instantiate new Hangar that has "Windrider" ship
        Hangar h = new Hangar (s);
        // this hangar instantiated belong to this new player.
        p.setHangar(h);

        // set default current hp to ship hp
        p.setCurrentHP(s.getHP());
        //add this player into list
        playerMgr.addPlayer(p); 
        playerLoggedIn = p;

    }

       /**
     * Get a player given user name.
     *
     * @param userName User name to search for.
     * @return the Player object which has the specified user name.
     *         If no such Player exists, returns null.
     */
    public Ship getShip(String shipName) throws DataException{
        return null;

    }

    public ArrayList<Ship> getShips(){
        return shipList;
    }

    public Player getPlayerLoggedIn(){
        return playerLoggedIn;
    }

    public ArrayList<WeaponPart> getWeaponList(int weaponClass){
        switch(weaponClass){
            case CANNONS:
                return cannonList;
            case MELEES:
                return meleeList;
            case MISSILES:
                return missileList;
            case SUBCANNONS:
                return subcannonList;
        }
        return cannonList;
    }

     public ArrayList<WeaponPart> getPartList(int partClass){
        switch(partClass){
            case ENGINES :
                return engineList;
            case SAILS :
                return sailList;
            case FIGUREHEADS :
                return figureheadList;
            case HULLS :
                return hullList;
            case STABILIZERS :
                return stabilizerList;
        }
        return engineList;
    }

    public ArrayList<String> buy(WeaponPart w) throws DataException{
        try {
            ArrayList<String> errors = validateCanBuy(w);
            if (errors.size() == 0){
                //add weapon into storage
                playerLoggedIn.addToStorage(w);
                playerMgr.updatePlayer(playerLoggedIn);
            } // end if canBuy??
            return errors;
        } catch(DataException e) {
            throw e;
        }
    }

    public ArrayList<String> buy(int partType, WeaponPart w) throws DataException{
        try{
            ArrayList<String> errors = validateCanBuy(w);

            if(errors.size() == 0){
                //add part into storage
                playerLoggedIn.addToStorage(partType, w);
                playerMgr.updatePlayer(playerLoggedIn);
            }//end if
            return errors;
        } catch (DataException e){
            throw e;
        }
    }

    public ArrayList<String> validateCanBuy(WeaponPart w) {
        //compare Level Required,Gold,Wood,Ore,Plasma Rock to buy weapon
        
        ArrayList<String> errors = new ArrayList<String>();
        String s = "";

        boolean levelEnough = playerLoggedIn.getLevel()>=w.getLevelReq();

        if (!levelEnough) {
            s = "Your Level is " + playerLoggedIn.getLevel() + "." + w.getName() + " requires a minimum Level of " + w.getLevelReq();
            errors.add(s); 
        } 

        boolean goldEnough = playerLoggedIn.getGold()>=w.getGold();
        if (!goldEnough) {
            s = "Your Gold is " + playerLoggedIn.getGold() + "." + w.getName() + " requires a minimum Gold of " + w.getGold();
            errors.add(s);  
        } 

        boolean woodEnough = playerLoggedIn.getWood()>=w.getWood();
        if (!woodEnough) {
            s = "Your Wood is " + playerLoggedIn.getWood() + "." + w.getName() + " requires a minimum Wood of " + w.getWood();
            errors.add(s);  
        } 
        boolean oreEnough = playerLoggedIn.getOre()>=w.getOre();
        if (!oreEnough) {
            s = "Your Ore is " + playerLoggedIn.getOre() + "." + w.getName() + " requires a minimum Ore of " + w.getOre();
            errors.add(s);  
        }

        boolean prockEnough = playerLoggedIn.getProck()>=w.getProck();
        if (!prockEnough) {
            s = "Your Prock is " + playerLoggedIn.getProck() + "." + w.getName() + " requires a minimum Prock of " + w.getProck();
            errors.add(s);  
        }

        return errors;

    }

    public ArrayList<String> validateCanBuy(Part w) {
        //compare Level Required,Gold,Wood,Ore,Plasma Rock to buy weapon
        
        ArrayList<String> errors = new ArrayList<String>();
        String s = "";

        boolean levelEnough = playerLoggedIn.getLevel()>=w.getLevelReq();

        if (!levelEnough) {
            s = "Your Level is " + playerLoggedIn.getLevel() + "." + w.getName() + " requires a minimum Level of " + w.getLevelReq();
            errors.add(s); 
        } 

        boolean goldEnough = playerLoggedIn.getGold()>=w.getGold();
        if (!goldEnough) {
            s = "Your Gold is " + playerLoggedIn.getGold() + "." + w.getName() + " requires a minimum Gold of " + w.getGold();
            errors.add(s);  
        } 

        boolean woodEnough = playerLoggedIn.getWood()>=w.getWood();
        if (!woodEnough) {
            s = "Your Wood is " + playerLoggedIn.getWood() + "." + w.getName() + " requires a minimum Wood of " + w.getWood();
            errors.add(s);  
        } 
        boolean oreEnough = playerLoggedIn.getOre()>=w.getOre();
        if (!oreEnough) {
            s = "Your Ore is " + playerLoggedIn.getOre() + "." + w.getName() + " requires a minimum Ore of " + w.getOre();
            errors.add(s);  
        }

        boolean prockEnough = playerLoggedIn.getProck()>=w.getProck();
        if (!prockEnough) {
            s = "Your Prock is " + playerLoggedIn.getProck() + "." + w.getName() + " requires a minimum Prock of " + w.getProck();
            errors.add(s);  
        }

        return errors;

    }

    public ArrayList<Player> getPlayerList(){
        return playerMgr.getAll();

    }

    public ArrayList<Player> getTargets(){
        ArrayList<Player> pList = this.getPlayerList();
        ArrayList<Player> targetList = new ArrayList<Player>();

        int loggedInPlayerLevel = this.playerLoggedIn.getLevel();
        String loggedInPlayerName = this.playerLoggedIn.getName();

        for(int i = 0; i < pList.size(); i++){
            Player p = pList.get(i);
            boolean notLoggedInPlayer = !loggedInPlayerName.equals(p.getName());
            int difference = Math.abs(loggedInPlayerLevel - p.getLevel());
            boolean within5Levels = (difference >= 0 && difference <= 5);
            
            if(notLoggedInPlayer && within5Levels){
                targetList.add(p); 
            }
            
        }
        return targetList;
    }

    public void deductAPForPVP() throws DataException{
        playerLoggedIn.increaseAP(-8);
        playerMgr.updatePlayer(playerLoggedIn);

    }

    public ArrayList<ArrayList<Attack>> startBattle(Player target){
        battleMgr.assignPlayer(target,playerLoggedIn);
        battleMgr.getMultiplier(); // this calculates multiplier already;


        int timeTaken = battleMgr.getTimeTakenForBattle1();
        int defenderPosition = battleMgr.getPositionForBattle1("defender", timeTaken);
        int attackerPosition = battleMgr.getPositionForBattle1("attacker", timeTaken);
        int rangeForBattle1 = defenderPosition-attackerPosition; 
        ArrayList<WeaponPart> attackerWeapons = getWeaponInRange(playerLoggedIn, rangeForBattle1); //attacker is playerLoggedIn
        ArrayList<WeaponPart> defenderWeapons = getWeaponInRange(target, rangeForBattle1); //defender is target
        Random r = new Random();
        ArrayList<Attack> attackerList = new ArrayList<Attack>();
        // arraylist of attack called attackerList
        // for loop attackerweapons
        // each attackerweapon.getMaxDamage
        // each attackerweapon.getMinDamage
        int attackerTotalDamage = 0;
        for (WeaponPart weapon: attackerWeapons){
            int maxDamage = weapon.getMaxDamage();
            int minDamage = weapon.getMinDamage();
            int damageRange = maxDamage - minDamage;
            int randomDamage = r.nextInt(damageRange+1);
            int actualDamage = minDamage+randomDamage;
            Attack a = new Attack(playerLoggedIn.getName(), weapon.getName(), rangeForBattle1, actualDamage);
            attackerList.add(a);
            attackerTotalDamage += actualDamage;

        }
        int attackerCombatXP = battleMgr.getCombatXP(attackerTotalDamage, "attacker");
        // do the same for defender
        ArrayList<Attack> defenderList = new ArrayList<Attack>();
        int defenderTotalDamage = 0;
        for (WeaponPart weapon: defenderWeapons){
            int maxDamage = weapon.getMaxDamage();
            int minDamage = weapon.getMinDamage();
            int damageRange = maxDamage - minDamage;
            int randomDamage = r.nextInt(damageRange+1);
            int actualDamage = minDamage+randomDamage;
            Attack d = new Attack(target.getName(), weapon.getName(), rangeForBattle1, actualDamage);
            defenderList.add(d);
            defenderTotalDamage += actualDamage;

        }
        int defenderCombatXP = battleMgr.getCombatXP(defenderTotalDamage, "defender");
        ArrayList<ArrayList<Attack>> result = new ArrayList<ArrayList<Attack>>();
        result.add(attackerList);
        result.add(defenderList);
        
        return result;

    }

    public ArrayList<WeaponPart> getWeaponInRange(Player target, int range){
        ArrayList<WeaponPart> myList = target.getHangar().getWeaponList(); 
        // need a weaponpart resultList
        ArrayList<WeaponPart> results = new  ArrayList<WeaponPart>();
        System.out.println("opponent:*** " +target.getName());
        for(WeaponPart wp: myList){
            WeaponPart w = weaponPartMgr.getWeaponByName(wp.getName());
            // check if w.getRange is within the range
            if (w.getRange() <= range){
                results.add(w);
                System.out.println("weapon:*** " +w.getName());
            }
            // if it is , add w to result list
        }

        return results;// return resultList
    }
} // AppController
