package Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Container extends StorageUnit {

    String location;
    LocalDateTime arriveDate;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDateTime arriveDate) {
        this.arriveDate = arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        if (arriveDate != null) {
            LocalDate localDate = arriveDate.toLocalDate();
            this.arriveDate = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        } else {
            this.arriveDate = null;
        }
    }
}
