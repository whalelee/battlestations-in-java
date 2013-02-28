import java.io.*;
import java.text.*;
import java.util.*;

/**
 * This class provides functions to log events to file.
 */
public class AppLogger {

    // attribute
    private final String FILE_NAME = "data/app_logs.csv";
    private final String CLASS_NAME = "AppLogger";

    /**
     * No-argument constructor
     */
    public AppLogger() {
        //Creation and initialisation of the file
        PrintStream fileOut = null;
        try {
            File f = new File(FILE_NAME);
            if (!f.exists()) {
                //creating file Header
                fileOut = new PrintStream(new FileOutputStream(FILE_NAME, false));
                fileOut.println("Time Stamp,Log Type,Log Info,Cashier Logged in");
            }

        } catch (FileNotFoundException e) {
            //display error
            System.out.println(CLASS_NAME + " class : " + FILE_NAME + " not found");
        } finally {
            if (fileOut != null) {
                fileOut.close();
            }
        }
    }

    /**
     * Append a new log in the file
     *
     * @param messageType Either "INFO" or "ERROR".
     * @param message     The message that contains details of the event to be logged.
     */
    public void log(String messageType, String message) {
        log(messageType, message, null);
    }

    /**
     * Append a new log in the file
     *
     * @param messageType Either "INFO" or "ERROR".
     * @param message     The message that contains details of the event to be logged.
     * @param userName    User name of the user who triggered this event.
     */
    public void log(String messageType, String message, String userName) {
        PrintStream fileOut = null;
        try {
            //Logging info and messages in the file
            fileOut = new PrintStream(new FileOutputStream(FILE_NAME, true));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fileOut.print(sdf.format(new Date()));
            fileOut.print(",");
            fileOut.print(messageType);
            fileOut.print(",");
            fileOut.print(message);

            // check if the cashier has logged in successfully or not
            if (userName != null) {
                fileOut.print(",");
                fileOut.println(userName);
            } else {
                fileOut.println(",No cashier logged in yet");
            }
        } catch (FileNotFoundException e) {
            //display error
            System.out.println(CLASS_NAME + " class : " + FILE_NAME + " not found");
        } finally {
            if (fileOut != null) {
                fileOut.close();
            }
        }
    }
}
