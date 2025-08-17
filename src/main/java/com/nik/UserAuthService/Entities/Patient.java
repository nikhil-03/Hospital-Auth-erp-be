package com.nik.UserAuthService.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Patient extends User {
    private String bloodGroup;

    @Embedded
    private EmergencyContact emergencyContact;

    // Getters and setters...
}
