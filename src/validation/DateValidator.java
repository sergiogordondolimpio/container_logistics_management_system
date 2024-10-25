package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    // Method to validate the date format
    public String validateDateFormat(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return "Si se agrega una fecha deben estar ambos.";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);  // Ensure strict parsing

        try {
            sdf.parse(dateStr);  // Try parsing the date
            return null;
        } catch (ParseException e) {
            return "Formato inválido (dd/MM/aaaa).";
        }
    }

    // Method to check if from date is before to date
    public String validateDateRange(String fromDateStr, String toDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        try {
            Date fromDate = sdf.parse(fromDateStr);
            Date toDate = sdf.parse(toDateStr);

            // Check if fromDate is before or equal to toDate
            if (fromDate.after(toDate)) {
                return "'Desde' tiene que ser menor o igual a 'Hasta'.";
            }
            return null;
        } catch (ParseException e) {
            return "Formato inválido (dd/mm/aaaa).";
        }
    }

}
