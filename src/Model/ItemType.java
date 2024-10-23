package Model;

public enum ItemType {
    CONTAINER("Contenedor"),
    MODULE("Modulo"),
    BOX("Caja");

    private String label;

    ItemType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
