package Controller;


import DAO.MovementHistoryDAO;

public class MovementHistoryController {

    private MovementHistoryDAO movementHistoryDAO;

    public MovementHistoryController() {
        this.movementHistoryDAO = new MovementHistoryDAO();
    }

    public Integer save(String type) {
        return movementHistoryDAO.save(type);
    }
}

