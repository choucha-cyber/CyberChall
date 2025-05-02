package com.cadettesdelacyber.CyberChall.services;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.repositories.AdminRepository;
import com.cadettesdelacyber.CyberChall.repositories.SessionRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	 public SessionService(SessionRepository sessionRepository) {
	        this.sessionRepository = sessionRepository;
	    }

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public void save(Session session) {
        sessionRepository.save(session);
    }
   

    // === Authentification ===
    public boolean authenticate(String username, String password) {
        Admin admin = adminRepository.findByUsername(username); // Recherche de l'admin en base
        if (admin != null) {
            return passwordEncoder.matches(password, admin.getPassword()); // Comparaison du mot de passe en clair avec celui encodé
        }
        return false; // L'admin n'existe pas ou le mot de passe est incorrect
    }

    // === Créer une session ===
    @Transactional
    public Session createSession(LocalDate dateDebut, int duree, String token, List<SousModule> sousModules, Admin admin) {
        Session session = new Session();
        session.setDateDebut(dateDebut);
        session.setDateDebutFormatted(dateDebut.toString());
        session.setDuree(duree);
        session.setToken(token);
        session.setSousModules(sousModules);
        session.setAdmin(admin);

        // ❗ SAUVEGARDE EN BASE
        return sessionRepository.save(session);
    }

    public List<Session> findByAdmin(Admin admin) {
        return sessionRepository.findByAdmin(admin);
    }



    // === Récupérer les sessions liées à un admin ===
    public List<Session> getSessionsByAdmin(Admin admin) {
        return sessionRepository.findByAdmin(admin);
    }

    // === Statistiques ===
    public long countAllSessions() {
        return sessionRepository.count();
    }

    // === Génération de token ===
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    // === Extraction d'infos depuis le cookie session ===
    public String getRoleByToken(HttpServletRequest request) {
        String token = extractSessionToken(request);
        if (token != null) {
            return (String) request.getSession().getAttribute("role");
        }
        return null;
    }

    public String getUsernameByToken(HttpServletRequest request) {
        String token = extractSessionToken(request);
        if (token != null) {
            return (String) request.getSession().getAttribute("username");
        }
        return null;
    }

    private String extractSessionToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("SESSION_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}