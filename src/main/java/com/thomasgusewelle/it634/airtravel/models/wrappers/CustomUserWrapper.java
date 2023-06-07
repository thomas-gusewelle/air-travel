package com.thomasgusewelle.it634.airtravel.models.wrappers;

import com.thomasgusewelle.it634.airtravel.models.CustomUser;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "CustomUserWrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomUserWrapper implements Serializable {
    @XmlElement(name = "CustomUser")
    private List<CustomUser> users = new ArrayList<>();

    public List<CustomUser> getUsers() {
        return users;
    }

    public void setUsers(List<CustomUser> users) {
        this.users = users;
    }

    public void addUser(CustomUser user) {
        users.add(user);
    }

    public CustomUser findUserByUsername(String email) {
        return users.stream().filter(user -> email.equals(user.getEmail())).findFirst().orElse(null);
    }

    public void deleteUser(CustomUser user) {
        users.remove(user);
    }
}
