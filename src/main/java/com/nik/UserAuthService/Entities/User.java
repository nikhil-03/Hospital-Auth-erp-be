package com.nik.UserAuthService.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_auth")
@Builder
public class User {
    @Id
    private String userId;
    private String username;
    private String password;
    private String role;
    private boolean verified=false;
    private LocalDate joiningDate;
}
