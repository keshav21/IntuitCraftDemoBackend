package com.intuit.services;



import com.intuit.components.UserComponent;
import com.intuit.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserComponent userComponent;

    public boolean doesUserExists(String email) {
        return userComponent.doesUserExists(email);
    }

    public Users createUser(Users users) {
        return userComponent.createUser(users);
    }

    public Users updateUser(Users users) {
        return userComponent.updateUser(users);
    }

    public Users getUser(String email) {
        return userComponent.getUser(email);
    }

    public boolean verifyUser(String email, String password) {
        return userComponent.verifyUser(email,password);
    }
}
