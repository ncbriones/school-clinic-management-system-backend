package com.scms.api.config;

import com.scms.api.entity.Role;
import com.scms.api.entity.User;
import com.scms.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        final String adminEmail = "admin@gmail.com";

        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();

            admin.setEmail(adminEmail);
            admin.setName("Admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            System.out.println("User admin successfully added.");
        } else {
            System.out.println("User admin already exists.");
        }
    }
}
