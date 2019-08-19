package ru.job4j.magnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class ConvertXSQT {
    private static final Logger LOG = LogManager.getLogger(ConvertXSQT.class.getName());
    private final File target;
    private final File destination;
    private final File scheme;

    public ConvertXSQT(File target, File destination, File scheme) {
        this.target = target;
        this.destination = destination;
        this.scheme = scheme;
    }

    public void convert() {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(target), new StreamResult(destination));
        } catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
