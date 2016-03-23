package ru.logstore.service;


import ru.logstore.model.User;

import java.util.List;

/**
 */
public interface UserService {

    User get(int id);

    User getByName(String name);

    User getByNameAndPass(String name, String pass);

    String getLoggedUser();

}
