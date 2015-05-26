package com.epam.student.krynytskyi.xml;

import com.epam.student.krynytskyi.xml.model.RoleXml;
import com.epam.student.krynytskyi.xml.model.Security;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Securitymarshaling {
    public static void main(String[] args) throws JAXBException {
        Security security = new Security();
        RoleXml roleXml = new RoleXml();
        roleXml.setRole("CLIENT");
        roleXml.setRedirectTo("/products");
        List<String> urls = new ArrayList<>();
        urls.add("/login");
        urls.add("/registration");
        roleXml.setUrls(urls);

        RoleXml anonymous = new RoleXml();
        anonymous.setRole("ANONYMOUS");
        urls = new ArrayList<>();
        anonymous.setRedirectTo("/login");
        urls.add("/order");
        urls.add("/logOut");
        anonymous.setUrls(urls);

        List<RoleXml> roleXmls = new ArrayList<>();
        roleXmls.add(anonymous);
        roleXmls.add(roleXml);
        security.setRoleXmls(roleXmls);

        JAXBContext jaxbContext = JAXBContext.newInstance(Security.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the employees list in console
        jaxbMarshaller.marshal(security, System.out);

        //Marshal the employees list in file
        jaxbMarshaller.marshal(security, new File("d:/xml/security.xml"));

    }

}
