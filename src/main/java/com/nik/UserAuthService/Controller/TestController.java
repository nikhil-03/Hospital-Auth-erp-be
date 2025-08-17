package com.nik.UserAuthService.Controller;

import com.nik.UserAuthService.Entities.RegistrationRequest;
import com.nik.UserAuthService.Entities.User;
import com.nik.UserAuthService.Services.UserAuthServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:5173", "http://localhost:4173", "http://127.0.0.1:5173", "http://127.0.0.1:3000"}, allowCredentials = "true")
public class TestController {
    
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    
    @Autowired
    UserAuthServices userAuthServices;
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Server is running! CORS should work now.");
    }
    
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) {
        try {
            logger.info("Received registration request for email: {}", request.getEmail());
            
            // Validate required fields
            if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
                logger.warn("Registration failed: First name is required");
                return ResponseEntity.badRequest().body("First name is required");
            }
            if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
                logger.warn("Registration failed: Last name is required");
                return ResponseEntity.badRequest().body("Last name is required");
            }
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                logger.warn("Registration failed: Email is required");
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                logger.warn("Registration failed: Password is required");
                return ResponseEntity.badRequest().body("Password is required");
            }
            if (request.getPhone() == null || request.getPhone().trim().isEmpty()) {
                logger.warn("Registration failed: Phone is required");
                return ResponseEntity.badRequest().body("Phone is required");
            }
            if (request.getAddress() == null || request.getAddress().trim().isEmpty()) {
                logger.warn("Registration failed: Address is required");
                return ResponseEntity.badRequest().body("Address is required");
            }
            if (request.getCity() == null || request.getCity().trim().isEmpty()) {
                logger.warn("Registration failed: City is required");
                return ResponseEntity.badRequest().body("City is required");
            }
            if (request.getState() == null || request.getState().trim().isEmpty()) {
                logger.warn("Registration failed: State is required");
                return ResponseEntity.badRequest().body("State is required");
            }
            if (request.getZipCode() == null || request.getZipCode().trim().isEmpty()) {
                logger.warn("Registration failed: Zip code is required");
                return ResponseEntity.badRequest().body("Zip code is required");
            }
            
            // Create User entity
            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setPhone(request.getPhone());
            user.setDateOfBirth(request.getDateOfBirth());
            user.setRegistrationDate(LocalDate.now());
            
            // Set gender enum
            if (request.getGender() != null) {
                try {
                    user.setGender(User.Gender.valueOf(request.getGender().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    logger.warn("Registration failed: Invalid gender: {}", request.getGender());
                    return ResponseEntity.badRequest().body("Invalid gender. Must be MALE, FEMALE, or OTHER");
                }
            }
            
            // Set required address fields
            user.setAddress(request.getAddress());
            user.setCity(request.getCity());
            user.setState(request.getState());
            user.setZipCode(request.getZipCode());
            
            // Set role enum
            if (request.getRole() != null) {
                try {
                    String roleStr = request.getRole().toUpperCase();
                    if (roleStr.equals("DOCTOR")) {
                        user.setRole(User.Role.DOCTOR);
                    } else if (roleStr.equals("PATIENT")) {
                        user.setRole(User.Role.PATIENT);
                    } else if (roleStr.equals("LAB_TECHNICIAN")) {
                        user.setRole(User.Role.LAB_TECHNICIAN);
                    } else {
                        logger.warn("Registration failed: Invalid role: {}", request.getRole());
                        return ResponseEntity.badRequest().body("Invalid role. Must be DOCTOR, PATIENT, or LAB_TECHNICIAN");
                    }
                } catch (IllegalArgumentException e) {
                    logger.warn("Registration failed: Invalid role: {}", request.getRole());
                    return ResponseEntity.badRequest().body("Invalid role. Must be DOCTOR, PATIENT, or LAB_TECHNICIAN");
                }
            }
            
            // Set doctor-specific fields
            user.setSpecialization(request.getSpecialization());
            user.setExperience(request.getExperience());
            user.setEducation(request.getEducation());
            user.setLicenseNumber(request.getLicenseNumber());
            
            // Set patient-specific fields
            user.setBloodGroup(request.getBloodGroup());
            
            // Set emergency contact fields
            if (request.getEmergencyContact() != null) {
                user.setEmergencyContactName(request.getEmergencyContact().getName());
                user.setEmergencyContactPhone(request.getEmergencyContact().getPhone());
                user.setEmergencyContactRelationship(request.getEmergencyContact().getRelationship());
            }
            
            // Set lab technician-specific fields
            user.setDepartment(request.getDepartment());
            
            // Set default values
            user.setIsActive(true);
            
            logger.info("Attempting to save user with ID: {}", user.getUserId());
            User savedUser = userAuthServices.addUser(user);
            logger.info("User registered successfully with ID: {}", savedUser.getUserId());
            
            return ResponseEntity.ok(savedUser);
            
        } catch (Exception e) {
            logger.error("Registration failed with exception: ", e);
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
    
    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("I am Nikhil Prakash");
        return userAuthServices.customData();
    }
}

