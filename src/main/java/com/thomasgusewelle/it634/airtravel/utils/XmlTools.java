package com.thomasgusewelle.it634.airtravel.utils;

import com.thomasgusewelle.it634.airtravel.models.CustomUser;
import com.thomasgusewelle.it634.airtravel.models.wrappers.CustomUserWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class XmlTools {


    public void UserToXML(CustomUser user) throws JAXBException {
//        Creates JAXB instance and marshaller
        JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(CustomUser.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
//        get all users so that we can add the new user to the existing list
        CustomUserWrapper users = getUsersXML();
        users.addUser(user);
//        Push users to file
        File file = new File("src/main/java/com/thomasgusewelle/it634/airtravel/users.xml");
        marshaller.marshal(users, file);
    }

    public CustomUserWrapper getUsersXML() throws JAXBException {
//        Create JAXB and unmarshaller
        JAXBContext jaxbContext;
        CustomUserWrapper users = null;
        jaxbContext = JAXBContext.newInstance(CustomUserWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        try Catch is to catch if the file is empty
        try {
            File file = new File("src/main/java/com/thomasgusewelle/it634/airtravel/users.xml");
            users = (CustomUserWrapper) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println("XML File is Empty: Can Ingnore");
        }
//        If the file is empty we create an empty list
        if (users == null) {
            users = new CustomUserWrapper();
        }
        return users;
    }
}
