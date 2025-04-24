package com.cadettesdelacyber.CyberChall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cadettesdelacyber.CyberChall.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    // Définition de la méthode pour rechercher un utilisateur par son nom et son rôle
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.role = :role")
    User findByUsernameAndRole(@Param("username") String username, @Param("role") String role);

}

