package ru.job4j.magnet;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

public class StoreXML {
    private final File target;

    @XmlRootElement
    public static class Entries {
        private List<Entry> values;

        public Entries() {

        }

        public Entries(List<Entry> values) {
            this.values = values;
        }

        public List<Entry> getValues() {
            return values;
        }

        public void setValues(List<Entry> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Entry {
        private long value;

        public Entry(long value) {
            this.value = value;
        }

        public Entry() {

        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> values) {
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Entries e = new Entries(values);
            marshaller.marshal(e, this.target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
