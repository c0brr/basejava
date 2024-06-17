package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializationStrategy {

    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                AbstractSection abstractSection = entry.getValue();
                if (abstractSection instanceof TextSection) {
                    dos.writeUTF(((TextSection) abstractSection).getContent());
                } else if (abstractSection instanceof ListSection) {
                    List<String> list = ((ListSection) abstractSection).getContentList();
                    dos.writeInt(list.size());
                    for (String content : list) {
                        dos.writeUTF(content);
                    }
                } else if (abstractSection instanceof CompanySection) {
                    List<Company> listCompanies = ((CompanySection) abstractSection).getCompanies();
                    dos.writeInt(listCompanies.size());
                    for (Company company : listCompanies) {
                        dos.writeUTF(company.getName());
                        dos.writeBoolean(company.getWebsite() == null);
                        if (company.getWebsite() != null) {
                            dos.writeUTF(company.getWebsite());
                        }
                        List<Period> listPeriods = company.getPeriods();
                        dos.writeInt(listPeriods.size());
                        for (Period period : listPeriods) {
                            dos.writeUTF(period.getStartDate().toString());
                            dos.writeUTF(period.getEndDate().toString());
                            dos.writeUTF(period.getTitle());
                            dos.writeBoolean(period.getDescription() == null);
                            if (period.getDescription() != null) {
                                dos.writeUTF(period.getDescription());
                            }
                        }
                    }
                }
            }
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
}