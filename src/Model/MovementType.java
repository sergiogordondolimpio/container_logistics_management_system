package Model;

public enum MovementType {
    CONTAINER_RETISTER("Registro de container"),
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
