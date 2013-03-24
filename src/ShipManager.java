import java.io.*;
import java.util.*;
import java.text.*;

public class ShipManager{
	private ArrayList<Ship> shipList;
    private final String FILE_NAME = "data/ships.csv";
    private final String CLASS_NAME = "ShipManager";

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load ship' information from file.
     */
    public ShipManager() throws DataException {
        shipList = new ArrayList<Ship>();
        load();
    }

    /**
     * Loads ships' information from file
     *
     * @throws DataException Thrown when unable to load ships' information from file.
     */
    private void load() throws DataException {
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File(FILE_NAME));
            fileIn.useDelimiter("\r\n");
            fileIn.next(); //skip the first line
            //#Ship,Speed,HP,Slots,Capacity,Level Required,Gold,Wood,Ore,Plasma Rock,Port
            while (fileIn.hasNext()) {
                String[] data   = fileIn.next().split(",");
                String shipName = data[0];
                int speed       = Integer.parseInt(data[1]);
                int hp          = Integer.parseInt(data[2]);
                int slots       = Integer.parseInt(data[3]);
                int capacity    = Integer.parseInt(data[4]);
                int levelReq    = Integer.parseInt(data[5]); 
                int gold        = Integer.parseInt(data[6]);
                int wood        = Integer.parseInt(data[7]);
                int ore         = Integer.parseInt(data[8]);
                int prock       = Integer.parseInt(data[9]);
                String port     = data[10];

                //set data to the Ship object
                Ship p = new Ship();
                p.setName(shipName);
                p.setSpeed(speed);
                p.setHP(hp);
                p.setSlots(slots);
                p.setCapacity(capacity);
                p.setLevelReq(levelReq);
                p.setGold(gold);
                p.setOre(ore);
                p.setWood(wood);
                p.setProck(prock);
                p.setPort(port);

                shipList.add(p);
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

    public ArrayList<Ship> getAll(){
    	return shipList;
    }

    public Ship getShipByName(String shipName){
    	Ship ship = null;
    	for(Ship s : shipList ){
    		if(s.getName().equals(shipName)){
    			ship = s;
    		}
    	}

    	return ship;

    }





}