package Controller;

import Model.Container;
import DAO.ContainerDAO;
import View.Contents.RegisterContainerView;

public class ContainerController {

    private static ContainerController instance;
    private ContainerDAO containerDAO;
    private RegisterContainerView registerContainerView;

    public ContainerController() {
        this.containerDAO = new ContainerDAO();
    }


    // Save container to the database
    public Container saveContainer(Container container) {
        return containerDAO.save(container);
    }

    public boolean isCodeInDatabase(String code) {
        return containerDAO.siCodeInDatabase(code);
    }
}

