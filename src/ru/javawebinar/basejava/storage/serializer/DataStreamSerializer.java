package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements SerializationStrategy {

    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                AbstractSection abstractSection = entry.getValue();
                switch (entry.getKey()) {
                    case SectionType.OBJECTIVE, SectionType.PERSONAL -> dos.writeUTF(((TextSection) abstractSection)
                            .getContent());
                    case SectionType.ACHIEVEMENT, SectionType.QUALIFICATIONS ->
                            writeWithException(((ListSection) abstractSection).getContentList(), dos, dos::writeUTF);
                    case SectionType.EXPERIENCE, SectionType.EDUCATION ->
                            writeWithException(((CompanySection) abstractSection).getCompanies(), dos, company -> {
                                dos.writeUTF(company.getName());
                                dos.writeBoolean(company.getWebsite() == null);
                                if (company.getWebsite() != null) {
                                    dos.writeUTF(company.getWebsite());
                                }
                                writeWithException(company.getPeriods(), dos, period -> {
                                    dos.writeUTF(period.getStartDate().toString());
                                    dos.writeUTF(period.getEndDate().toString());
                                    dos.writeUTF(period.getTitle());
                                    dos.writeBoolean(period.getDescription() == null);
                                    if (period.getDescription() != null) {
                                        dos.writeUTF(period.getDescription());
                                    }
                                });
                            });
                }
            });
        }
    }

    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.getContacts().put(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.getSections().put(sectionType, switch (sectionType) {
                    case SectionType.OBJECTIVE, SectionType.PERSONAL -> new TextSection(dis.readUTF());
                    case SectionType.ACHIEVEMENT, SectionType.QUALIFICATIONS ->
                            new ListSection(readWithException(dis.readInt(), dis::readUTF));
                    case SectionType.EXPERIENCE, SectionType.EDUCATION ->
                            new CompanySection(readWithException(dis.readInt(), () -> new Company(dis.readUTF(),
                                    dis.readBoolean() ? null : dis.readUTF(), readWithException(dis.readInt(), () ->
                                    new Period(LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()),
                                            dis.readUTF(), dis.readBoolean() ? null : dis.readUTF())))));
                });
            }
            return resume;
        }
    }

    private <T> void writeWithException(Collection<T> coll, DataOutputStream dos,
                                        CustomConsumer<T> action) throws IOException {
        Objects.requireNonNull(action);
        dos.writeInt(coll.size());
        for (T t : coll) {
            action.customAccept(t);
        }
    }

    private <T> List<T> readWithException(int size, CustomSupplier<T> action) throws IOException {
        Objects.requireNonNull(action);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(action.customGet());
        }
        return list;
    }
}