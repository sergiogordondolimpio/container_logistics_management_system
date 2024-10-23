package Controller;

import DAO.ModuleDAO;
import Model.Module;

import java.util.List;

public class ModuleController {

    private ModuleDAO moduleDAO;

    public ModuleController() {
        this.moduleDAO = new ModuleDAO();
    }

    public Module save(Module module) {
        return moduleDAO.save(module);
    }


    public boolean isCodeInDatabase(String code) {
       return moduleDAO.isCodeInDatabase(code);
    }

    public List<Module> getModulesByContainer(Integer idContainer) {
        return moduleDAO.getModulesByContainer(idContainer);
    }

    public boolean deleteByCode(String code) {
        return moduleDAO.deleteByCode(code);
    }

}
