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
    private WeaponPartManager weaponPartMgr;
    private final String FILE_NAME = "data/players.csv";
    private final String CLASS_NAME = "PlayerManager";

    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;
    private final int WEAPONS = 6;

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load player' information from file.
     */
    public PlayerManager(ShipManager shipMgr, WeaponPartManager weaponPartMgr) throws DataException {
        this.shipMgr = shipMgr;
        this.weaponPartMgr = weaponPartMgr;
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
                //split string data using ","
                //username,password,playerType,gold,wood,ore,prock,joinedDate,ap,loggedInDate,playerLevel,shipName,craft,navigation,gunnery,currentHP
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
                double craft = Double.parseDouble(data[12]);
                double navigation = Double.parseDouble(data[13]);
                double gunnery = Double.parseDouble(data[14]);
                int currentHP = Integer.parseInt(data[15]);
                int exp = Integer.parseInt(data[16]);

                Storage storage = new Storage();

                String storageFigureheads = data[17];
                String[] figureheads = storageFigureheads.split(":");
                for(String fig : figureheads) {
                    
                    WeaponPart part = this.weaponPartMgr.getPartByTypeAndName(FIGUREHEADS,fig);
                    if (part!=null)
                        storage.addToFigureheadList(part);
                }

                String storageSails = data[18];
                String[] sails = storageSails.split(":");
                for(String sai : sails) {
                    WeaponPart sail = this.weaponPartMgr.getPartByTypeAndName(SAILS,sai);
                    if (sail!=null)
                        storage.addToSailList(sail);
                }

                String storageStabilizers = data[19];
                String[] stabilizers = storageStabilizers.split(":");
                for(String sta : stabilizers) {
                    WeaponPart stabilizer = this.weaponPartMgr.getPartByTypeAndName(STABILIZERS,sta);
                    if (stabilizer!=null)
                        storage.addToStabilizerList(stabilizer);
                }

                String storageHull = data[20];
                String[] hulls = storageHull.split(":");
                for(String hul : hulls) {
                    WeaponPart hull = this.weaponPartMgr.getPartByTypeAndName(HULLS,hul);
                    if (hull!=null)
                        storage.addToHullList(hull);
                }

                String storageEngine = data[21];
                String[] engines = storageEngine.split(":");
                for(String eng : engines) {
                    WeaponPart engine = this.weaponPartMgr.getPartByTypeAndName(ENGINES,eng);
                    if (engine!=null)
                        storage.addToEngineList(engine);
                }

                String storageWeapon = data[22];
                String[] weapons = storageWeapon.split(":");
                for(String wea : weapons) {
                    WeaponPart weapon = this.weaponPartMgr.getWeaponByName(wea);
                    if (weapon!=null)
                        storage.addToWeaponList(weapon);
                }

                Ship s = this.shipMgr.getShipByName(shipName);
                
                //create a hangar with ship gotten
                Hangar h = new Hangar(s);

                String hangarFigurehead = data[23];
                WeaponPart figurehead = this.weaponPartMgr.getPartByTypeAndName(FIGUREHEADS, hangarFigurehead);
                    if (figurehead!=null)
                        h.setFigurehead(figurehead);
                
                hangarFigurehead = data[24];
                figurehead = this.weaponPartMgr.getPartByTypeAndName(SAILS, hangarFigurehead);
                    if (figurehead!=null)
                        h.setSail(figurehead);

                hangarFigurehead = data[25];
                figurehead = this.weaponPartMgr.getPartByTypeAndName(STABILIZERS, hangarFigurehead);
                    if (figurehead!=null)
                        h.setStabilizer(figurehead);

                hangarFigurehead = data[26];
                figurehead = this.weaponPartMgr.getPartByTypeAndName(HULLS, hangarFigurehead);
                    if (figurehead!=null)
                        h.setHull(figurehead);

                hangarFigurehead = data[27];
                figurehead = this.weaponPartMgr.getPartByTypeAndName(HULLS, hangarFigurehead);
                    if (figurehead!=null)
                        h.setEngine(figurehead);

                storageWeapon = data[28];
                weapons = storageWeapon.split(":");
                for(String wea : weapons) {
                    WeaponPart weapon = this.weaponPartMgr.getWeaponByName(wea);

                    if (weapon!=null)
                        h.addWeapon(weapon);
                }
                
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
                p.setCraft(craft);
                p.setNavigation(navigation);
                p.setGunnery(gunnery);
                p.setCurrentHP(currentHP);
                p.setExp(exp);
                p.setStorage(storage);

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
            int totalPlayer = playerList.size();
            for (int i = 0; i < totalPlayer; i++) {
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
                fileOut.print(",");
                fileOut.print(c.getCraft());
                fileOut.print(",");
                fileOut.print(c.getNavigation());
                fileOut.print(",");
                fileOut.print(c.getGunnery());
                fileOut.print(",");
                fileOut.print(c.getCurrentHP());
                fileOut.print(",");
                fileOut.print(c.getExp());
                fileOut.print(",");
                fileOut.print(c.getStorage().getListToString(FIGUREHEADS));
                fileOut.print(",");
                fileOut.print(c.getStorage().getListToString(SAILS));
                fileOut.print(",");
                fileOut.print(c.getStorage().getListToString(STABILIZERS));
                fileOut.print(",");
                fileOut.print(c.getStorage().getListToString(HULLS));
                fileOut.print(",");
                fileOut.print(c.getStorage().getListToString(ENGINES));
                fileOut.print(",");
                fileOut.print(c.getStorage().getWeaponListToString());
                fileOut.print(",");
                fileOut.print(c.getHangar().getFigurehead().getName());
                fileOut.print(",");
                fileOut.print(c.getHangar().getSail().getName());
                fileOut.print(",");
                fileOut.print(c.getHangar().getStabilizer().getName());
                fileOut.print(",");
                fileOut.print(c.getHangar().getHull().getName());
                fileOut.print(",");
                fileOut.print(c.getHangar().getEngine().getName());
                fileOut.print(",");
                fileOut.print(c.getHangar().getWeaponListToString());
                fileOut.print(",");


                if (i<totalPlayer - 1){
                    fileOut.println(); 
                }   
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
