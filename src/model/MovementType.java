package model;

public enum MovementType {
    CONTAINER_REGISTER("Registro de container"),
    DEVANNING("Devanning"),
    UNPACKING("Unpacking");

    private String label;

    MovementType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
