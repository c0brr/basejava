package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> contentList;

    public ListSection() {

    }

    public ListSection(List<String> content) {
        Objects.requireNonNull(content, "content must not be null");
        this.contentList = content;
    }

    public List<String> getContentList() {
        return new ArrayList<>(contentList);
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
