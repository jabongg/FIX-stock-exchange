package com.staywell.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carUserID;

    String name;
    String email;
    String phone;
}
