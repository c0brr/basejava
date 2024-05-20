package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Period {
    private static final DateTimeFormatter DTF =  DateTimeFormatter.ofPattern("MM/yyyy");
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private String description;

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
