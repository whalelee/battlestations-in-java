import java.util.*;

public class AppController {
    private PlayerManager playerMgr;
    private ShipManager shipMgr;

    private Player playerLoggedIn;
    private ArrayList<Ship> shipList;

    public AppController() throws DataException{
        shipMgr = new ShipManager();
        shipList = shipMgr.getAll();
        playerMgr = new PlayerManager(shipMgr);
        

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

    public Player getPlayerLoggedIn(){
        return playerLoggedIn;
    }

} // AppController
