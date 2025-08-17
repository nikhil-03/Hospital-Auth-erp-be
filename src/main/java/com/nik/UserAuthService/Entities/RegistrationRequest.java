package com.nik.UserAuthService.Entities;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String role;
    
    // Doctor-specific fields
    private String specialization;
    private Integer experience;
    private String education;
    private String licenseNumber;
    
    // Patient-specific fields
    private String bloodGroup;
    
    // Emergency contact fields
    private EmergencyContact emergencyContact;
    
    // Lab Technician-specific fields
    private String department;
    
    @Data
    public static class EmergencyContact {
        private String name;
        private String phone;
        private String relationship;
    }
} 