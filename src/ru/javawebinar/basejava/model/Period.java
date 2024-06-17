package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM/yyyy");
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String description;

    public Period() {

    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        this(startDate, endDate, title);
        this.description = description;
    }

    public Period(LocalDate startDate, LocalDate endDate, String title) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;
        return startDate.equals(period.startDate) &&
                endDate.equals(period.endDate) &&
                title.equals(period.title) &&
                Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + Objects.hashCode(description);
        return result;
    }

    @Override
    public String toString() {
        return (description == null ?
                DTF.format(startDate) + " - " + DTF.format(endDate) + "\n" + title :
                DTF.format(startDate) + " - " + DTF.format(endDate) + "\n" + title + "\n" + description);
    }
}
