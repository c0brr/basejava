package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> descriptions = new ArrayList<>();

    public List<String> getDescriptions() {
        return descriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;
        return descriptions.equals(that.descriptions);
    }

    @Override
    public int hashCode() {
        return descriptions.hashCode();
    }

    @Override
    public String toString() {
        return descriptions.toString();
    }
}
