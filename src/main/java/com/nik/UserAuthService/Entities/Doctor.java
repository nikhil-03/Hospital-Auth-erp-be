package com.nik.UserAuthService.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Doctor extends User {
    private String specialization;
    private Integer experience;
    private String education;
    private String licenseNumber;

    // Getters and setters...
}