package com.epam.student.krynytskyi.xml;

import com.epam.student.krynytskyi.xml.model.Security;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class SecurityUnMarshaling {
    public Security unMarshal(String url) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Security.class);
        Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
        return (Security) jaxbUnMarshaller.unmarshal(new File(url));
    }
}
