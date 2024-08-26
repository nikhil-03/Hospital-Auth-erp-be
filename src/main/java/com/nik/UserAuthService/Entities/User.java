package com.nik.UserAuthService.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_auth")
public class User {
    private String id;
    private String username;
    private String password;
    private String role;
    private boolean verified=false;
    private Data joiningDate;
}
