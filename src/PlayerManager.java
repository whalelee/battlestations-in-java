import java.io.*;
import java.util.*;
import java.text.*;

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
            fileIn.next(); //skip the first line
            while (fileIn.hasNext()) {
                
                String username   = fileIn.next();
                String password   = fileIn.next();
                String playerType = fileIn.next();
                int gold          = Integer.parseInt(fileIn.next());
                int ore           = fileIn.nextInt();
                int wood          = fileIn.nextInt();
                int prock         = fileIn.nextInt();
                String dateInString = fileIn.next();
                System.out.println(dateInString);
                SISDate joinedDate = new SISDate(dateInString);
                Player p = new Player(username, password, playerType.charAt(0));
                p.setGold(gold);
                p.setOre(ore);
                p.setWood(wood);
                p.setProck(prock);
                p.setJoinedDate(joinedDate);
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
        } catch (ParseException e) {
            throw new DataException(e.getMessage());
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

    public void addPlayer(Player p) throws DataException{
        playerList.add(p);
        save();
    }

    /**
     * Save players' information to the file.
     *
     * @throws DataException Thrown when unable to save players' information to file.
     */
    private void save() throws DataException {
        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream(new FileOutputStream(FILE_NAME, false));
            for (int i = 0; i < playerList.size(); i++) {
                Player c = playerList.get(i);
                fileOut.print(c.getName());
                fileOut.print(",");
                fileOut.print(c.getPassword());
                fileOut.print(",");
                fileOut.print(c.getPlayerType());
                fileOut.print(",");
                fileOut.print(c.getGold());
                fileOut.print(",");
                fileOut.print(c.getWood());
                fileOut.print(",");
                fileOut.print(c.getOre());
                fileOut.print(",");
                fileOut.print(c.getProck());
                fileOut.print(",");
                String date = c.getJoinedDate().toString();
                fileOut.print(date);
                fileOut.println();
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

}
