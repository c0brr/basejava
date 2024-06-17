package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Period> periods;
    private String name;
    private String website;

    public Company() {

    }

    public Company(List<Period> periods, String name, String website) {
        this(periods, name);
        this.website = website;
    }

    public Company(List<Period> periods, String name) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(periods, "periods must not be null");
        this.periods = periods;
        this.name = name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;
        return periods.equals(company.periods)
                && name.equals(company.name)
                && Objects.equals(website, company.website);
    }

    @Override
    public int hashCode() {
        int result = periods.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + Objects.hashCode(website);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(website == null ?
                name + "\n" :
                name + "\n" + website + "\n");
        for (Period period : periods) {
            stringBuilder.append(period.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}