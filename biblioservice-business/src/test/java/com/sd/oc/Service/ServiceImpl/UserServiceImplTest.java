package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.Service.ConfigurationService;
import com.sd.oc.Service.ServiceInterface.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationService.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Test
    public void getUserById() {
        assertEquals("melissa.hamon",userService.getUserById(1).getUsername());
    }

    @Test
    public void getUserByIdWithIdNotExit() {
        assertNull(userService.getUserById(75));
    }

    @Test
    public void getUserByUsername() {
        assertEquals(1,userService.getUserByUsername("melissa.hamon").getUser_id());
    }

    @Test
    public void getUserByUsernameWithUserNameNotExit() {
        assertNull(userService.getUserByUsername("robert.notexist"));
    }

    @Test
    @Transactional
    @Rollback
    public void addUser() {
        userService.addUser("user.username", "motdepasse", "user@mail.com");
        assertNotNull(userService.getUserByUsername("user.username"));
        assertTrue(passwordEncoder.matches("motdepasse", userService.getUserByUsername("user.username").getPassword()));
    }

    @Test
    public void getAllUsers() {
        assertEquals(15, userService.getAllUsers().size());
    }

    @Test
    public void testIfPseudoNotUsed() {
        assertTrue(userService.testIfPseudoNotUsed("notUsedPseudo"));
    }

    @Test
    public void testIfPseudoNotUsedWithUsedPseudo() {
        assertFalse(userService.testIfPseudoNotUsed("melissa.hamon"));
    }
}
