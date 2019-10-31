package com.sd.oc.Service.ServiceInterface;

import com.sd.oc.model.User;

import java.util.List;

public interface UserService   {

    User getUserById(int id);

    User getUserByUsername(String username);

    void addUser(String username, String password, String mail);

    List<User> getAllUsers();

    boolean testIfPseudoNotUsed(String username);
}
