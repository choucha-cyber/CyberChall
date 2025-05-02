package com.cadettesdelacyber.CyberChall.config;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.repositories.AdminRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createDefaultAdmin(AdminRepository userRepository,
                                         PasswordEncoder passwordEncoder) {
        return args -> {
            String defaultAdminUsername = "cadette";
            String defaultAdminPassword = "123";

            if (userRepository.findByUsername(defaultAdminUsername) == null) {
                Admin admin = new Admin();
                admin.setUsername(defaultAdminUsername);
                admin.setPassword(passwordEncoder.encode(defaultAdminPassword)); // 🔐 encodage ici !
                userRepository.save(admin);
                System.out.println("✔ Admin initialisé avec mot de passe encodé.");
            } else {
                System.out.println("ℹ Admin déjà présent en base.");
            }
        };
    }
}