package com.cadettesdelacyber.CyberChall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.models.User;
import com.cadettesdelacyber.CyberChall.repositories.SessionRepository;
import com.cadettesdelacyber.CyberChall.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepo; // Le repository Session
    
    @Autowired
    private UserRepository userRepository;
    

    // Méthode pour trouver toutes les sessions d'un utilisateur
    public List<Session> findSessionsByUser(User user) {
        return sessionRepo.findByUser(user);  // Utilise la méthode du repository pour récupérer les sessions
    }
    
    // Authentifier l'utilisateur en fonction de son nom d'utilisateur, mot de passe et rôle
    public boolean authenticate(String username, String password, String role) {
        // Chercher l'utilisateur par son nom et son rôle
        User user = userRepository.findByUsernameAndRole(username, role);

        // Si l'utilisateur existe et que le mot de passe est correct
        if (user != null && user.getPassword().equals(password)) {
            return true; // Connexion réussie
        }
        return false; // Si l'utilisateur n'existe pas ou si le mot de passe est incorrect
    }

    // === Créer une session ===
 // Dans le Service
    public Session createSession(String titre, String description, String modules, User admin) {
        // Crée une session avec les bons paramètres
        Session session = new Session(titre, description, modules, admin);
        return sessionRepo.save(session);  // Sauvegarde la session dans la base de données
    }


    // === Obtenir toutes les sessions d'un admin ===
    public List<Session> getSessionsByUser(User admin) {
        return sessionRepo.findByUser(admin);  // Trouver toutes les sessions d'un utilisateur (admin)
    }

    // === Générer un lien unique (token) pour chaque session ===
    public String generateToken() {
        return UUID.randomUUID().toString();  // Token unique pour chaque session
    }

    // === Lister les sessions créées dans le mois dernier ===
    public List<Session> getSessionsFromLastMonth(User admin) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return sessionRepo.findByUserAndDateDebutAfter(admin, oneMonthAgo);  // Sessions de l'admin dans le dernier mois
    }

    // === Statistiques ===
    public long countAllSessions() {
        return sessionRepo.count();  // Nombre total de sessions
    }

    public long countActiveSessions() {
        return sessionRepo.countByDateFinAfter(LocalDate.now());  // Nombre de sessions actives
    }

    // === Pour l'affichage sur /account ===
    public List<Session> getSessionsForAccountPage(User user) {
        return sessionRepo.findByUser(user);  // Lister les sessions liées à un utilisateur donné
    }
    
    
 // Méthode pour obtenir le rôle à partir du token
    public String getRoleByToken(HttpServletRequest request) {
        // Recherche du cookie "SESSION_TOKEN" et récupération du rôle associé
        String token = extractSessionToken(request);
        if (token != null) {
            // Ici, tu pourrais stocker les informations de rôle dans la session HTTP, par exemple
            return (String) request.getSession().getAttribute("role");  // Exemple : le rôle est stocké dans la session
        }
        return null;
    }

    // Méthode pour obtenir le username à partir du token
    public String getUsernameByToken(HttpServletRequest request) {
        String token = extractSessionToken(request);
        if (token != null) {
            // Ici, tu récupères le username depuis la session HTTP
            return (String) request.getSession().getAttribute("username");  // Exemple : l'username est dans la session
        }
        return null;
    }

    // Méthode pour extraire le token du cookie
    private String extractSessionToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (jakarta.servlet.http.Cookie cookie : request.getCookies()) { // Changement ici vers jakarta.servlet.http.Cookie
                if ("SESSION_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();  // Retourne la valeur du cookie, qui est le token
                }
            }
        }
        return null;
    }
}