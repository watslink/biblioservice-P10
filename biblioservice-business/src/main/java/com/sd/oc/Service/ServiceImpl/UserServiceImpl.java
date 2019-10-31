package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.DAO.UserDAO;
import com.sd.oc.Service.ServiceInterface.UserService;
import com.sd.oc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    private static Logger logger = LogManager.getLogger("UserServiceImpl");
    @Override
    public User getUserById(int id) {
        Optional<User> optUser=userDAO.findById(id);
        return optUser.orElse(null);
    }

    @Override
    public User getUserByUsername(String username)  {
        return userDAO.findByUsername(username);
    }

    @Override
    public void addUser(String username, String password, String mail) {
        if(testIfPseudoNotUsed(username)){
            User user=new User(username, passwordEncoder.encode(password), mail);
            userDAO.save(user);
            logger.info("User "+username+" is added");
        }
        else
        logger.warn("User "+username+" is not added, cause: username already exist");
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>)userDAO.findAll();
    }

    @Override
    public boolean testIfPseudoNotUsed(String username) {
        for(User user:userDAO.findAll()){
            if(user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
}
