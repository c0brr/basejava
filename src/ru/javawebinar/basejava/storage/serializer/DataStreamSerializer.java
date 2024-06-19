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

            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                AbstractSection section = null;
                switch (sectionType) {
                    case SectionType.OBJECTIVE, SectionType.PERSONAL -> section = new TextSection(dis.readUTF());
                    case SectionType.ACHIEVEMENT, SectionType.QUALIFICATIONS -> {
                        int size = dis.readInt();
                        List<String> list = new ArrayList<>();
                        for (int j = 0; j < size; j++) {
                            list.add(dis.readUTF());
                        }
                        section = new ListSection(list);
                    }
                    case SectionType.EXPERIENCE, SectionType.EDUCATION -> {
                        int sizeCompanies = dis.readInt();
                        List<Company> companies = new ArrayList<>();
                        for (int j = 0; j < sizeCompanies; j++) {
                            String name = dis.readUTF();
                            String website = dis.readBoolean() ? null : dis.readUTF();
                            int sizePeriods = dis.readInt();
                            List<Period> periods = new ArrayList<>();
                            for (int k = 0; k < sizePeriods; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String description = dis.readBoolean() ? null : dis.readUTF();
                                periods.add(new Period(startDate, endDate, title, description));
                            }
                            companies.add(new Company(periods, name, website));
                        }
                        section = new CompanySection(companies);
                    }
                }
                resume.addSection(sectionType, section);
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
}