package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    // Convert date and time strings to LocalDateTime
    public static LocalDateTime createDateTime(String date, String time) {
        String dateTimeString = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

}
