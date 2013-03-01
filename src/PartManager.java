import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Data manager class to persist player' information to file.
 */
public class PartManager{
	private ArrayList<Part> engineList;
	private ArrayList<Part> figureheadList;
	private ArrayList<Part> hullList;
	private ArrayList<Part> sailList;
	private ArrayList<Part> stabilizerList;
    
    private final String ENGINE_FILE_NAME = "data/engines.csv";
    private final String FIGUREHEAD_FILE_NAME = "data/figureheads.csv";
    private final String SAIL_FILE_NAME = "data/sails.csv";
    private final String HULL_FILE_NAME = "data/hulls.csv";
    private final String STABILIZER_FILE_NAME = "data/stabilizers.csv";

    private final String CLASS_NAME = "PartManager";


    private final int ENGINES = 1;
    private final int FIGUREHEADS = 2;
    private final int SAILS = 3;
    private final int HULLS = 4;
    private final int STABILIZERS = 5;

    /**
     * no argument constructor
     *
     * @throws DataException Thrown when unable to load ship' information from file.
     */
	public PartManager() throws DataException{
		engineList = new ArrayList<Part>();
		figureheadList = new ArrayList<Part>();
		sailList = new ArrayList<Part>();
		hullList = new ArrayList<Part>();
		stabilizerList = new ArrayList<Part>();
		
        load();
	}

	public ArrayList<Part> getPartList(int partClass){
		switch(partClass){
			case ENGINES:
				return engineList;
			case HULLS:
				return hullList;
			case SAILS:
				return sailList;
			case FIGUREHEADS:
				return figureheadList;
			case STABILIZERS:
				return stabilizerList;
		}
		return engineList;
	}

	public void load() throws DataException{
		loadThisPartClass(ENGINES);
		loadThisPartClass(HULLS);
		loadThisPartClass(SAILS);
		loadThisPartClass(FIGUREHEADS);
		loadThisPartClass(STABILIZERS);
	}

	public void loadThisPartClass(int partClass) throws DataException{
		String filename = "";
		ArrayList<Part> weaponList = new ArrayList<Part>();

		switch(partClass) {
			case SAILS :
				filename = SAIL_FILE_NAME;
				break;
			case ENGINES :
				filename = ENGINE_FILE_NAME;
				break;
			case FIGUREHEADS :
				filename = FIGUREHEAD_FILE_NAME;
				break;
			case HULLS :
				filename = HULL_FILE_NAME;
				break;
			case STABILIZERS :
				filename = STABILIZER_FILE_NAME;
				break;
		}

		Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File(filename));
            fileIn.useDelimiter("\r\n");
            fileIn.next(); //skip the first line
            //#Name,Speed,HP,Capacity,Weight,Level Required,Gold,Wood,Ore,Plasma Rock,Port
            while (fileIn.hasNext()) {
                String[] data   = fileIn.next().split(",");
               	String name = data[0];
				int speed = Integer.parseInt(data[1]);  
				int hp = Integer.parseInt(data[2]);  
				int capacity = Integer.parseInt(data[3]);  
				int weight = Integer.parseInt(data[4]);  
				int levelReq = Integer.parseInt(data[5]);  
				int gold = Integer.parseInt(data[6]);  
				int wood = Integer.parseInt(data[7]);  
				int ore = Integer.parseInt(data[8]);  
				int prock = Integer.parseInt(data[9]);  
				String port = data[10];  

                //set data to the Ship object
                Part p = new Part();
                p.setName(name);
                p.setSpeed(speed);
                p.setHP(hp);
                p.setCapacity(capacity);
                p.setWeight(weight);
                p.setLevelReq(levelReq);
                p.setGold(gold);
                p.setWood(wood);
                p.setOre(ore);
                p.setProck(prock);
                p.setPort(port);          

                switch(partClass) {
					case SAILS :
						sailList.add(p);
						break;
					case ENGINES :
						engineList.add(p);
						break;
					case FIGUREHEADS :
						figureheadList.add(p);
						break;
					case HULLS :
						hullList.add(p);
						break;
					case STABILIZERS :
						stabilizerList.add(p);
						break;
				}
            }
        } catch (InputMismatchException e) {
            //propagate error
            String message = "Reading error in File \"" + filename + "\". Invalid double format.";
            throw new DataException(message);
        } catch (FileNotFoundException e) {
            //propagate error
            String message = CLASS_NAME + " class : File " + filename + " not found";
            throw new DataException(message);
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }
	}
}