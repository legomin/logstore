package ru.logstore.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class User extends NamedEntity {

    protected String password;

    public User() {
    }

     public User(Integer id, String name, String password) {
        super(id, name);
        this.password = password;
   }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", name=" + name +
                ')';
    }
}
