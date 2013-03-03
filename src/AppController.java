import java.util.*;

public class AppController {
    private PlayerManager playerMgr;
    private ShipManager shipMgr;
    private WeaponManager weaponMgr;
    private PartManager partMgr;

    private Player playerLoggedIn;
    
    private ArrayList<Ship> shipList;
    private ArrayList<Weapon> cannonList;
    private ArrayList<Weapon> subcannonList;
    private ArrayList<Weapon> missileList;
    private ArrayList<Weapon> meleeList;

    private ArrayList<Part> engineList;
    private ArrayList<Part> figureheadList;
    private ArrayList<Part> hullList;
    private ArrayList<Part> sailList;
    private ArrayList<Part> stabilizerList;

    private final int CANNONS = 1;
    private final int MELEES = 2;
    private final int MISSILES = 3;
    private final int SUBCANNONS = 4;

    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

    public AppController() throws DataException{
        weaponMgr = new WeaponManager();
        partMgr = new PartManager();
        shipMgr = new ShipManager();
        playerMgr = new PlayerManager(shipMgr, partMgr, weaponMgr);
        shipList = shipMgr.getAll();
        cannonList = weaponMgr.getWeaponList(CANNONS);
        subcannonList = weaponMgr.getWeaponList(SUBCANNONS);
        missileList = weaponMgr.getWeaponList(MISSILES);
        meleeList = weaponMgr.getWeaponList(MELEES);

        engineList = partMgr.getPartList(ENGINES);
        figureheadList = partMgr.getPartList(FIGUREHEADS);
        sailList = partMgr.getPartList(SAILS);
        hullList = partMgr.getPartList(HULLS);
        stabilizerList = partMgr.getPartList(STABILIZERS);
        
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

    public ArrayList<Weapon> getWeaponList(int weaponClass){
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

     public ArrayList<Part> getPartList(int partClass){
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

    public ArrayList<String> buy(Weapon w) throws DataException{
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

    public void buy(int partType, Part w) throws DataException{
        try{
            boolean canBuy = validateCanBuy(w);

            if(canBuy){
                //add part into storage
                playerLoggedIn.addToStorage(partType, w);
                playerMgr.updatePlayer(playerLoggedIn);
            }//end if
        } catch (DataException e){
            throw e;
        }
    }

    public ArrayList<String> validateCanBuy(Weapon w) {
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

    public boolean validateCanBuy(Part w) throws DataException{
        //compare Level Required,Gold,Wood,Ore,Plasma Rock to buy weapon
        boolean ableToBuy = true;

        ableToBuy = ableToBuy && (playerLoggedIn.getLevel()>=w.getLevelReq());
        if (!ableToBuy) {
            throw new DataException("Your Level is " + playerLoggedIn.getLevel() + "." + w.getName() + " requires a minimum Level of " + w.getLevelReq());  
        } 
        ableToBuy = ableToBuy && (playerLoggedIn.getGold()>=w.getGold());
        if (!ableToBuy) {
            throw new DataException("Your Gold is " + playerLoggedIn.getGold() + "." + w.getName() + " requires a minimum Gold of " + w.getGold());  
        } 

        ableToBuy = ableToBuy && (playerLoggedIn.getWood()>=w.getWood());
        if (!ableToBuy) {
            throw new DataException("Your Wood is " + playerLoggedIn.getWood() + "." + w.getName() + " requires a minimum Wood of " + w.getWood());  
        } 
        ableToBuy = ableToBuy && (playerLoggedIn.getOre()>=w.getOre());
        if (!ableToBuy) {
            throw new DataException("Your Ore is " + playerLoggedIn.getOre() + "." + w.getName() + " requires a minimum Ore of " + w.getOre());  
        }

        ableToBuy = ableToBuy && (playerLoggedIn.getProck()>=w.getProck());
        if (!ableToBuy) {
            throw new DataException("Your Prock is " + playerLoggedIn.getProck() + "." + w.getName() + " requires a minimum Prock of " + w.getProck());  
        }

        return ableToBuy;

    }

} // AppController
