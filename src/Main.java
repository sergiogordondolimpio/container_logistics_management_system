import Controller.LoginController;
import Model.UserDAO;
import View.Login;
import View.Menu;


public class Main {
    public static void main(String[] args) {
//        UserDAO userDAO = UserDAO.getInstance(); // Singleton pattern
//        Login view = new Login();
//        LoginController controller = new LoginController(userDAO, view);
//        view.setController(controller);

        new Menu();

    }
}