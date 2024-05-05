package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final List<Period> periods = new ArrayList<>();
    private String name;
    private String website;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;
        return periods.equals(company.periods) && name.equals(company.name) && website.equals(company.website);
    }

    @Override
    public int hashCode() {
        int result = periods.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + website.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return (website != null ? name + "\n" + website + "\n" + periods :
                name + "\n" + periods);
    }
}
