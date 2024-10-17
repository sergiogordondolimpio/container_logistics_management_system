package Validation;

import Controller.ModuleController;

public class ModuleValidator {

    private ModuleController moduleController;

    public ModuleValidator(ModuleController moduleController) {
        this.moduleController = moduleController;
    }

    public String validateCode(String code) {
        String codePattern = "^M[A-Z]{3}\\d{4}$";
        if (!code.matches(codePattern)) {
            return "Inválido formato de código de módulo, ingresar: MAAA0001.";
        }
        return null;
    }

    // Validate if a field contains a number with or without decimals
    public String validateNumberWithDecimal(String numberField) {
        String decimalPattern = "^\\d+(\\.\\d+)?$";
        if (!numberField.matches(decimalPattern)) {
            return "El campo debe contener solo números o números con decimales.";
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
        if (moduleController.isCodeInDatabase(code)) {
            return "Código ya existe.";
        }
        return null;
    }
}
