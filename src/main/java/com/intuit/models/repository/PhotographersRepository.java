package com.intuit.models.repository;

import com.intuit.models.Photographers;
import com.intuit.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PhotographersRepository extends JpaRepository<Photographers, Integer>  {
    @Query(value = "SELECT * FROM photographers WHERE email Like ?1%", nativeQuery = true)
    List<Photographers> findAllPhotographersByEmail(String email);
}

