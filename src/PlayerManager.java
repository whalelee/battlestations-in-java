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
    public PlayerManager() throws DataException {
        playerList = new ArrayList<Player>();
        load();
    }

    /**
     * Loads players' information from file
     *
     * @throws DataException Thrown when unable to load players' information from file.
     */
    private void load() throws DataException {
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File(FILE_NAME));
            fileIn.useDelimiter(",|\r\n");

            while (fileIn.hasNext()) {
                String username = fileIn.next();
                String password = fileIn.next();
                Player p = new Player(username, password);
                playerList.add(p);
            }
        } catch (InputMismatchException e) {
            //propagate error
            String message = "Reading error in File \"" + FILE_NAME + "\". Invalid double format.";
            throw new DataException(message);
        } catch (FileNotFoundException e) {
            //propagate error
            String message = CLASS_NAME + " class : File " + FILE_NAME + " not found";
            throw new DataException(message);
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }
    }

    /**
     * Get a player given user name.
     *
     * @param userName User name to search for.
     * @return the Player object which has the specified user name.
     *         If no such Player exists, returns null.
     */
    public Player getPlayerByCredentials(String username, String password) {
        Player playerToReturn = null;
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            if (p.getName().equals(username) && p.getPassword().equals(password)) {
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
        Player p = getPlayerByName(player.getName());
        p.setName(player.getName());
    }

    /**
     * Get a player given user name.
     *
     * @param userName User name to search for.
     * @return the Player object which has the specified user name.
     *         If no such Player exists, returns null.
     */
    public Player getPlayerByName(String username) {
        Player playerToReturn = null;
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            if (p.getName().equals(username)) {
                playerToReturn = p;
            }
        }
        return playerToReturn;
    }


}
