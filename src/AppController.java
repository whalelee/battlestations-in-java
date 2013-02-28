import java.util.*;

public class AppController {
    private PlayerManager playerMgr;

    private Player playerLoggedIn;


    public Player authenticatePlayer(String username, String password){
         Player p = playerMgr.getPlayer(username);
         String logMessage = null;
         if (p != null && p.getPassword().equals(password) {
            // authenticate success
            playerLoggedIn = p;
            logMessage = "Successful Login: Username : \"" + username + "\" Password entered \"" + password + "\"";
            appLogger.log("INFO", logMessage, playerLoggedIn.getName());
         } else{
            playerLoggedIn = null;
            logMessage = "Login Error. User name : \"" + userName
                    + "\" Password entered: \"" + password + "\"";
            appLogger.log("ERROR", logMessage);
         }

         return playerLoggedIn;    
    }

    public void processRegister(String username, String password){

        Player p = new Player()
    }

} // AppController
