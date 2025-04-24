package com.cadettesdelacyber.CyberChall.config;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository) {
        return args -> {
            String defaultAdminUsername = "cadette";
            String defaultAdminPassword = "123"; // à hasher si tu as un encoder

            // Vérifie si un admin existe déjà
            if (userRepository.findByUsername(defaultAdminUsername) == null) {
                Admin admin = new Admin(); // ✅ On instancie Admin
                admin.setUsername(defaultAdminUsername);
                admin.setPassword(defaultAdminPassword); // 🔐 Pense à hasher un jour
                admin.setRole("admin");
                userRepository.save(admin);
                System.out.println("✔ Admin initialisé en base : admin / 123");
            } else {
                System.out.println("ℹ Admin déjà présent en base.");
            }
        };
    }
}