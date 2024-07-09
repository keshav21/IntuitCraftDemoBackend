package com.intuit.models;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Photographers {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "uid")
    private UUID uid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "social_insurance_number")
    private String socialInsuranceNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "event_type", columnDefinition = "jsonb")
    private String eventType;

    @Column(name = "address", columnDefinition = "jsonb")
    private String address;

    @Column(name = "credit_card", columnDefinition = "jsonb")
    private String creditCard;

    @Column(name = "subscription", columnDefinition = "jsonb")
    private String subscription;

}
