package Validation;

import Controller.ContainerController;

public class ContainerValidator {

    private final ContainerController containerController;

    public ContainerValidator(ContainerController containerController) {
        this.containerController = containerController;
    }

    public String validateDate(String date) {
        String datePattern = "^([0-2][0-9]|(3)[0-1])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
        if (!date.matches(datePattern)) {
            return "Formato inválido de fecha, ingresar: dd/mm/yyyy.";
        }
        return null;
    }

    public String validateHour(String hour) {
        String timePattern = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
        if (!hour.matches(timePattern)) {
            return "Formato inválido de hora, ingresar: HH:mm.";
        }
        return null;
    }

    public String validateCode(String code) {
        String codePattern = "^C[A-Z]{3}\\d{4}$";
        if (!code.matches(codePattern)) {
            return "Inválido formato de código de contenedor, ingresar: CAAA0001.";
        }
        return null;
    }

    public String validateRequiredFields(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return "Todos los campos son obligatorios.";
            }
        }
        return null;
    }

    public String isCodeUnique(String code) {
        if (containerController.isCodeInDatabase(code)) {
            return "Código ya existe.";
        }
        return null;
    }
}

