package com.cadettesdelacyber.CyberChall.repositories;

import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    // Toutes les sessions d'un utilisateur (admin ou autre)
    List<Session> findByUser(User user);
    
    // Sessions d'un utilisateur après une date spécifique
    List<Session> findByUserAndDateDebutAfter(User user, LocalDate dateDebut);

    // Nombre de sessions actives (fin après aujourd'hui)
    long countByDateFinAfter(LocalDate date);

}