package com.nik.UserAuthService.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
public class EmergencyContact {
    private String name;
    private String phone;
    private String relationship;

    // Getters and setters...
}