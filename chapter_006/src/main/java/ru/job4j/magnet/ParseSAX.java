package ru.job4j.magnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class ParseSAX extends DefaultHandler {
    private static final Logger LOG = LogManager.getLogger(Config.class.getName());
    private final File source;
    private long result;

    public ParseSAX(File source) {
        this.source = source;
    }

    public long parseXML() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(source, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return this.result;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("entry")) {
            String value = attributes.getValue("field");
            this.result += Long.parseLong(value);
        }
    }

}
