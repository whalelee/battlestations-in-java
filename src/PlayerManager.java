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
                String name = fileIn.next();
                String userName = fileIn.next();
                Player p = new Player(name, userName, balance);
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
     * Saves players' information to  file
     *
     * @throws DataException Thrown when unable to save players' information to file.
     */
    private void save() throws DataException {
        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream(new FileOutputStream(FILE_NAME, false));
            for (int i = 0; i < playerList.size(); i++) {
                Player p = playerList.get(i);
                fileOut.print(p.getName());
                fileOut.print(",");
                fileOut.print(p.getUserName());
                fileOut.print(",");
                fileOut.println(p.getBalance());
            }
        } catch (FileNotFoundException e) {
            //propagate error
            String message = CLASS_NAME + " class : File " + FILE_NAME + " not found";
            throw new DataException(message);
        } finally {
            if (fileOut != null) {
                fileOut.close();
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
    public Player retrievePlayer(String userName, String password) {
        Player playerReturned = null;
        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            if (p.getUserName().equals(userName)) {
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
    public ArrayList<Player> retrieveAll() {
        return playerList;
    }

    /**
     * Update a player
     *
     * @param player Player to be updated.
     * @throws DataException Thrown when unable to save player's information to file.
     */
    public void update(Player player) throws DataException {
        Player p = retrievePlayer(player.getUserName());
        p.setName(player.getName());
        save();
    }

}
