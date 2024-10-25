package manager;

import model.User;

public class SessionManager {
    private static SessionManager instance;
    private User loggedInUser;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {

        //return loggedInUser;
        User user = new User();
        user.setId(1);
        user.setUsername("su");
        return user;
    }

    public void clearSession() {
        this.loggedInUser = null;
    }
}

