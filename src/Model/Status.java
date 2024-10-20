package Model;

public enum Status {
    IN_PROGRESS("En Progreso"),
    REGISTERED("Registrado"),
    CANCEL("Cancelado");

    private String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
