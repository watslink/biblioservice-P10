package com.sd.oc.DAO;

import com.sd.oc.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
