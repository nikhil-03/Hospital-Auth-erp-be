package com.nik.UserAuthService.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_auth")
public class User {
    @Id
    private String userId;
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    private LocalDate dateOfBirth;

    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Doctor-specific fields
    private String specialization;
    private Integer experience;      // years of experience
    private String education;
    private String licenseNumber;

    // Patient-specific fields
    private String bloodGroup;

    private String emergencyContactName;
    private String emergencyContactPhone;
    private String emergencyContactRelationship;

    // Lab Technician-specific fields
    private String department;

    private Boolean isActive = true;

    // Enums
    public enum Role {
        PATIENT, DOCTOR, LAB_TECHNICIAN
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // Constructors, getters, setters, etc.
}