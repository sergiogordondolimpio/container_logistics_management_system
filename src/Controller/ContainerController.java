package Controller;

import Model.Container;
import DAO.ContainerDAO;
import View.Contents.RegisterContainerView;

import java.util.List;

public class ContainerController {

    private ContainerDAO containerDAO;

    public ContainerController() {
        this.containerDAO = new ContainerDAO();
    }

    public Container saveContainer(Container container) {
        return containerDAO.save(container);
    }

    public boolean isCodeInDatabase(String code) {
        return containerDAO.siCodeInDatabase(code);
    }

    public List<Container> getAllContainers() {
        return containerDAO.getAllContainers();
    }

    public Integer getIdContainerByCode(String code) {
        return containerDAO.getIdContainerByCode(code);
    }

    public Container getContainerByCode(String code) {
        return containerDAO.getContainerByCode(code);
    }
}

