package dmitriev.pass.domain;

import com.fasterxml.jackson.annotation.JsonView;
import dmitriev.pass.View;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
@ToString(of = {"guid", "personName", "personSurname", "personPatronymic", "passportNumber", "dateFrom", "dateTo"})
public class Pass {
    @Id
    @JsonView(View.Public.class)
    private String guid = String.valueOf(UUID.randomUUID());

    @JsonView(View.Internal.class)
    private String personName;

    @JsonView(View.Internal.class)
    private String personSurname;

    @JsonView(View.Internal.class)
    private String personPatronymic;

    @JsonView(View.Internal.class)
    private String passportNumber;

    @JsonView(View.Internal.class)
    private String dateFrom;

    @JsonView(View.Internal.class)
    private String dateTo;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonPatronymic() {
        return personPatronymic;
    }

    public void setPersonPatronymic(String personPatronymic) {
        this.personPatronymic = personPatronymic;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}