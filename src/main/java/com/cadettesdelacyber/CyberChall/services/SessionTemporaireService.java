package com.cadettesdelacyber.CyberChall.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Module;

import com.cadettesdelacyber.CyberChall.models.SessionTemporaire;
import com.cadettesdelacyber.CyberChall.repositories.SessionTemporaireRepository;

@Service
public class SessionTemporaireService {

    @Autowired
    private SessionTemporaireRepository sessionTemporaireRepository;
  

    // Ajouter cette méthode pour récupérer toutes les sessions par un administrateur
    public List<SessionTemporaire> getSessionsParAdmin(Admin admin) {
        return sessionTemporaireRepository.findByAdmin(admin);
    }

    // 🔹 Créer une session temporaire
    public SessionTemporaire creerSessionTemporaire(List<Module> modules) {
        if (modules.size() < 2 || modules.size() > 4) {
            throw new IllegalArgumentException("La session doit contenir entre 2 et 4 modules.");
        }

        SessionTemporaire session = new SessionTemporaire();
        session.setToken(UUID.randomUUID().toString());  // Génère un token unique pour la session
        session.setDateCreation(LocalDateTime.now());
        session.setDateExpiration(LocalDateTime.now().plusMonths(1));  // Durée de 1 mois
        session.setModules(modules);  // Associe les modules à la session

        // Sauvegarde la session temporaire en base de données
        return sessionTemporaireRepository.save(session);
    }
    
    public Optional<SessionTemporaire> getSessionParToken(String token) {
        return sessionTemporaireRepository.findByToken(token);
    }
    
 // 🔍 Récupérer toutes les sessions temporaires
    public List<SessionTemporaire> getToutesSessions() {
        return sessionTemporaireRepository.findAll();
    }


}