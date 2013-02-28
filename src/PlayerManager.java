import java.io.*;
import java.util.*;

/**
 * Data manager class to persist player' information to file.
 */
public class PlayerManager {

    // attribute
    private ArrayList<Player> playerList;
    private final String FILE_NAME = "data/player.csv";
    private final String CLASS_NAME = "PlayerManager";

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load player' information from file.
     */
    public PlayerManager(){
        playerList = new ArrayList<Player>();
    }

    /**
     * Get a player given user name.
     *
     * @param userName User name to search for.
     * @return the Player object which has the specified user name.
     *         If no such Player exists, returns null.
     */
    public Player getPlayer(String username) {
        Player playerToReturn = null;
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            if (p.getName().equals(username)) {
                playerToReturn = p;
            }
        }
        return playerToReturn;
    }

    /**
     * Get all players.
     *
     * @return all the Player objects
     */
    public ArrayList<Player> getAll() {
        return playerList;
    }

    /**
     * Update a player
     *
     * @param player Player to be updated.
     * @throws DataException Thrown when unable to save player's information to file.
     */
    public void update(Player player){
        Player p = getPlayer(player.getName());
        p.setName(player.getName());
    }

}
