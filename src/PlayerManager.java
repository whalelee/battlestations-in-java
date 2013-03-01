import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */
public class PlayerManager {

    // attribute
    private ArrayList<Player> playerList;
    private ShipManager shipMgr;
    private final String FILE_NAME = "data/players.csv";
    private final String CLASS_NAME = "PlayerManager";

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load player' information from file.
     */
    public PlayerManager(ShipManager shipMgr) throws DataException {
        this.shipMgr = shipMgr;
        playerList = new ArrayList<Player>();
        load();
    }

    /**
     * Loads players' information from file
     *
     * @throws DataException Thrown when unable to load players' information from file.
     */
    public void load() throws DataException {
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File(FILE_NAME));
            fileIn.useDelimiter("\r\n");
            fileIn.next(); //skip the first line
            while (fileIn.hasNext()) {
                String[] data = fileIn.next().split(",");
                String username   = data[0];
                String password   = data[1];
                String playerType = data[2];
                int gold          = Integer.parseInt(data[3]);
                int ore           = Integer.parseInt(data[4]);
                int wood          = Integer.parseInt(data[5]);
                int prock         = Integer.parseInt(data[6]);
                String dateInString = data[7];
                SISDate joinedDate = new SISDate(dateInString);
                int ap = Integer.parseInt(data[8]);
                String lastLoggedInDateInString = data[9];
                SISDate lastLoggedInDate = new SISDate(lastLoggedInDateInString);
                int level = Integer.parseInt(data[10]);
                String shipName = data[11];

                Ship s = this.shipMgr.getShipByName(shipName);
                //create a hangar with ship gotten
                Hangar h = new Hangar(s);
                Player p = new Player(username, password, playerType.charAt(0));
                p.setGold(gold);
                p.setOre(ore);
                p.setWood(wood);
                p.setProck(prock);
                p.setJoinedDate(joinedDate);
                p.setAP(ap);
                p.setLastLoggedInDate(lastLoggedInDate);
                p.setLevel(level);
                p.setHangar(h);

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

    public void updatePlayer(Player player) throws DataException{
        //update the player list
        for (int i=0; i<playerList.size(); i++){
            Player p = playerList.get(i);
            if (player.getName().equals(p.getName())){
                playerList.remove(i);
                playerList.add(i, player);
            }
        }
        save(); //update csv file

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

    private String readFirstLine() throws DataException{
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File(FILE_NAME));
            fileIn.useDelimiter("\r\n");
            return fileIn.next(); //skip the first line
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
     * Save players' information to the file.
     *
     * @throws DataException Thrown when unable to save players' information to file.
     */
    private void save() throws DataException {
        PrintStream fileOut = null;
        try {
            String firstLine = readFirstLine();
            fileOut = new PrintStream(new FileOutputStream(FILE_NAME, false));
            
            fileOut.println(firstLine);
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
                String joinedDate = c.getJoinedDate().toString();
                fileOut.print(joinedDate);
                fileOut.print(",");
                fileOut.print(c.getAP());
                fileOut.print(",");
                String lastLoggedInDate = c.getLastLoggedInDate().toString();
                fileOut.print(lastLoggedInDate);
                fileOut.print(",");
                fileOut.print(c.getLevel());
                fileOut.print(",");
                fileOut.print(c.getHangar().getShip().getName());

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
