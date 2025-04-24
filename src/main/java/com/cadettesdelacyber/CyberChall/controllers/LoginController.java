package com.cadettesdelacyber.CyberChall.controllers;

import com.cadettesdelacyber.CyberChall.services.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private SessionService sessionService;

    // Affichage page de connexion admin
    @GetMapping("/admin/connexion-admin")
    public String showAdminLoginPage() {
        return "admin/connexion-admin";  // Page de connexion admin
    }

    // Traitement connexion pour les admins
    @PostMapping("/admin/connexion-admin")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession sessionHttp,
                             Model model) {
        System.out.println("🔐 Tentative de login ADMIN avec : " + username + " / " + password);

        // Ici, on appelle directement la méthode de login du service (on suppose que le service est déjà correctement configuré)
        boolean isAuthenticated = sessionService.authenticate(username, password, "admin");

        if (isAuthenticated) {
            System.out.println("✅ Connexion réussie pour l'admin : " + username);
            // Sauvegarder les infos de connexion dans la session
            sessionHttp.setAttribute("estConnecte", true);
            sessionHttp.setAttribute("username", username);
            sessionHttp.setAttribute("role", "admin");
            return "redirect:/admin/accueil-admin";
        } else {
            System.out.println("❌ Échec de connexion - identifiants incorrects");
            model.addAttribute("error", "Identifiants incorrects");
            return "admin/connexion-admin";  // Revenir à la page de connexion admin avec un message d'erreur
        }
    }

    // Affichage page de connexion élève
    @GetMapping("/eleve/connexion-eleve")
    public String showStudentLoginPage() {
        return "eleve/connexion-eleve";  // Page de connexion élève
    }

    // Traitement connexion pour les élèves
    @PostMapping("/eleve/connexion-eleve")
    public String loginEleve(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model,
                             HttpSession session) {
        // Vérifier les identifiants pour l'élève
        boolean isAuthenticated = sessionService.authenticate(username, password, "eleve");

        if (isAuthenticated) {
            session.setAttribute("estConnecte", true);   // Définir la session comme étant connectée
            session.setAttribute("username", username);  // Sauvegarder le nom d'utilisateur
            session.setAttribute("role", "eleve");       // Sauvegarder le rôle de l'utilisateur
            return "redirect:/eleve/accueil-eleve";      // Rediriger vers l'accueil de l'élève
        } else {
            model.addAttribute("error", "Identifiants incorrects");  // Ajouter un message d'erreur
            return "eleve/connexion-eleve";  // Revenir à la page de connexion élève
        }
    }
}