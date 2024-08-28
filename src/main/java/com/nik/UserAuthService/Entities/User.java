package com.nik.UserAuthService.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_auth")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;
    private boolean verified=false;
    private LocalDate joiningDate;
}
