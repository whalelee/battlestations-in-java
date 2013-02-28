import java.util.*;

public class AppController {
    private PlayerManager playerMgr;

    private Player playerLoggedIn;

    public AppController() throws DataException{
        playerMgr = new PlayerManager();
    }


    public Player authenticatePlayer(String username, String password){
         Player p = playerMgr.getPlayerByCredentials(username, password);
         String logMessage = null;
         if (p != null) {
            // authenticate success
            playerLoggedIn = p;
            logMessage = "Successful Login: Username : \"" + username + "\" Password entered \"" + password + "\"";
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

    public void addPlayer(String username, String password, char playerType) throws DataException{
        Player p = new Player(username, password, playerType);
        playerMgr.addPlayer(p);

    }

} // AppController
