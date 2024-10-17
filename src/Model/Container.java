package Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Container {

    Integer idContainer;
    String location;
    String status;
    String code;
    LocalDateTime arriveDate;

    public Integer getIdContainer() {
        return idContainer;
    }

    public void setIdContainer(Integer idContainer) {
        this.idContainer = idContainer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
