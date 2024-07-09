package com.intuit.models.repository;


import com.intuit.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


// Interface extending JpaRepository to handle CRUD operations for TodoItem entities
public interface UsersRepository extends JpaRepository<Users, UUID> {
   Users findByEmail(String emailId);
}
