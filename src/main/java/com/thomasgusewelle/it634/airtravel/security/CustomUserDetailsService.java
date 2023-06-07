package com.thomasgusewelle.it634.airtravel.security;

import com.thomasgusewelle.it634.airtravel.models.CustomUser;
import com.thomasgusewelle.it634.airtravel.models.wrappers.CustomUserWrapper;
import com.thomasgusewelle.it634.airtravel.utils.XmlTools;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CustomUserDetailsService implements UserDetailsService {
//    private XmlTools utils = new XmlTools();


    @Override
//    The username is the same as email
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserWrapper users;
        try {
            users = getUsersXML();
        } catch (JAXBException e) {
            throw new UsernameNotFoundException("Error getting users");
        }
        CustomUser user = users.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void createUser(CustomUser user) throws JAXBException {
        UserToXML(user);
    }

    public void deleteUser(CustomUser user) throws JAXBException {
        DeleteUserXml(user);
    }

    private static void UserToXML(CustomUser user) throws JAXBException {
//        Creates JAXB instance and marshaller
        JAXBContext jaxbContext;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        jaxbContext = JAXBContext.newInstance(CustomUserWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

//        get all users so that we can add the new user to the existing list
        CustomUserWrapper users = getUsersXML();
        users.addUser(user);
//        Push users to file
        File file = new File("src/main/java/com/thomasgusewelle/it634/airtravel/users.xml");
        marshaller.marshal(users, file);
    }

    private static CustomUserWrapper getUsersXML() throws JAXBException {
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

    private static void DeleteUserXml(CustomUser user) throws JAXBException {
//        Creates JAXB instance and marshaller
        JAXBContext jaxbContext;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        jaxbContext = JAXBContext.newInstance(CustomUserWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

//        get all users so that we can add the new user to the existing list
        CustomUserWrapper users = getUsersXML();
        users.deleteUser(user);
//        Push users to file
        File file = new File("src/main/java/com/thomasgusewelle/it634/airtravel/users.xml");
        marshaller.marshal(users, file);
    }
}
