package com.sd.oc.API;

import com.sd.oc.Service.ServiceInterface.UserService;
import com.sd.oc.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "UserAPI")
public class UserServiceAPI {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationAPI.class);

    private UserService userService = context.getBean(UserService.class);

    @WebMethod(operationName = "findUserById")
    public User findUserById(@WebParam(name = "user_id") int id) {
        return userService.getUserById(id);
    }

    @WebMethod(operationName = "findUserByUsername")
    public  User findUserByUsername(@WebParam(name="username") String username){
        return userService.getUserByUsername(username);
    }

    @WebMethod(operationName = "findAllUsers")
    public List<User> findAllUsers(){
        return userService.getAllUsers();
    }

    @WebMethod(operationName = "addUser")
    public void addUser(@WebParam(name="username") String username,
                        @WebParam(name="password") String password,
                        @WebParam(name = "mail") String mail){
        userService.addUser(username, password, mail);
    }
}
