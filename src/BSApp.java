/**
 * The main class of this BSApp.
 * It sets up objects that are required throughout the application and
 * instantiate the starting boundary class LoginMenu to interact with user.
 */
public class BSApp{

    /**
     * Instantiate the starting boundary class LoginMenu to interact with user.
     */
    public static void main(String[] args) {

        AppController appCtrl = new AppController();
        LoginMenu loginMenu = new LoginMenu(appCtrl);
        loginMenu.readOption();

        //System.out.println("We apologize, the Battle Station Application must terminate due to a system error.");
        //System.exit(1);
        

    }
}
