package ru.logstore.repository;

import ru.logstore.model.User;

import java.util.List;

/**
 */
public interface UserRepository {
     // null if not found
    User get(int id);

    // null if not found
    List<User> getAll();

    User getByNameAndPass(String name, String pass);

    User getByName(String name);

}
