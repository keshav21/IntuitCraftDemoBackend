package com.intuit.components;

import com.intuit.models.Users;
import com.intuit.models.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class UserComponent {

    @Autowired
    private UsersRepository usersRepository;

    public boolean doesUserExists(String email) {
        Users user = usersRepository.findByEmail(email);
        return user != null && user.getEmail().equals(email);
    }

    public Users createUser(Users users) {
        String hashedPassword = hashPassword(users.getPassword());
        users.setPassword(hashedPassword);
        users.setId(UUID.randomUUID());
        return usersRepository.save(users);
    }

    public Users updateUser(Users users) {
        return usersRepository.save(users);
    }

    public Users getUser(String email) {
        Users user = usersRepository.findByEmail(email);
        if (user != null && user.getEmail().equals(email)) {
            return user;
        }
        return null;
    }

    public boolean verifyUser(String email, String password) {
        Users user = usersRepository.findByEmail(email);
        String hashedPassword = hashPassword(password);
        return user != null && user.getEmail().equals(email) && user.getPassword().equals(hashedPassword);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
