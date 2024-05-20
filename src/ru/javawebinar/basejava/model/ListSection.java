package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> contentList;

    public ListSection(List<String> content) {
        Objects.requireNonNull(content, "content must not be null");
        this.contentList = content;
    }

    public List<String> getContentList() {
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
