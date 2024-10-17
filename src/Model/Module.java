package Model;

import java.time.LocalDateTime;

public class Module {

    Integer idModule;
    Integer idContainer;
    String description;
    String status;
    String code;
    Float weight;

    public Module() {
    }

    public Module(Integer idModule, Integer idContainer, String description, String status, String code, Float weight) {
        this.idModule = idModule;
        this.idContainer = idContainer;
        this.description = description;
        this.status = status;
        this.code = code;
        this.weight = weight;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public Integer getIdContainer() {
        return idContainer;
    }

    public void setIdContainer(Integer idContainer) {
        this.idContainer = idContainer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
