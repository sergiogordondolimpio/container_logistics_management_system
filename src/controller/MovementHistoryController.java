package controller;


import dao.MovementHistoryDAO;
import model.MovementHistory;

import java.util.List;

public class MovementHistoryController {

    private MovementHistoryDAO movementHistoryDAO;

    public MovementHistoryController() {
        this.movementHistoryDAO = new MovementHistoryDAO();
    }

    public Integer save(String type, Integer idItem, String typeItem) {
        return movementHistoryDAO.save(type, idItem, typeItem);
    }

    public List<MovementHistory> getHistoryByIdItem(Integer id, String itemType) {
        return movementHistoryDAO.getHistoryByIdItem(id, itemType);
    }
}

