package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> contentList = new ArrayList<>();

    public List<String> getList() {
        return contentList;
    }

    public void addContent(String content) {
        contentList.add(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;
        return contentList.equals(that.contentList);
    }

    @Override
    public int hashCode() {
        return contentList.hashCode();
    }

    @Override
    public String toString() {
        return contentList.toString();
    }
}
