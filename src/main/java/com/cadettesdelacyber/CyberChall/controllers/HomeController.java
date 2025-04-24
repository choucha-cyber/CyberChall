package com.cadettesdelacyber.CyberChall.controllers;

import com.cadettesdelacyber.CyberChall.services.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SessionService sessionService;

    // ============================================================
    // Section 2: Page d'accueil (connexion)
    // ============================================================

    @GetMapping("/")
    public String index(HttpSession sessionHttp) {
        Boolean estConnecte = (Boolean) sessionHttp.getAttribute("estConnecte");
        String role = (String) sessionHttp.getAttribute("role");

        if (Boolean.TRUE.equals(estConnecte)) {
            if ("admin".equals(role)) {
                return "redirect:/admin/accueil-admin";
            } else if ("eleve".equals(role)) {
                return "redirect:/eleve/accueil-eleve";
            }
        }

        return "index"; // Si non connecté
    }

    // ============================================================
    // Section 4: Page d'accueil Admin
    // ============================================================

    @GetMapping("/admin/accueil-admin")
    public String showAdminHomePage(HttpSession sessionHttp, Model model) {
        Boolean estConnecte = (Boolean) sessionHttp.getAttribute("estConnecte");
        String role = (String) sessionHttp.getAttribute("role");

        if (!Boolean.TRUE.equals(estConnecte) || !"admin".equals(role)) {
            return "redirect:/admin/connexion-admin";
        }

        String username = (String) sessionHttp.getAttribute("username");
        model.addAttribute("username", username);

        return "admin/accueil-admin";
    }

    // ============================================================
    // Section 5: Page d'accueil Élève
    // ============================================================

    @GetMapping("/eleve/accueil-eleve")
    public String showEleveDashboard(HttpSession sessionHttp, Model model) {
        Boolean estConnecte = (Boolean) sessionHttp.getAttribute("estConnecte");
        String role = (String) sessionHttp.getAttribute("role");

        if (!Boolean.TRUE.equals(estConnecte) || !"eleve".equals(role)) {
            return "redirect:/login"; // Ou vers une page spécifique
        }

        model.addAttribute("username", sessionHttp.getAttribute("username"));
        return "eleve/accuel-eleve";
    }

    // ============================================================
    // Section 6: Modules (cyberattaque, réseaux, sécurité)
    // ============================================================

    @GetMapping("/module-cyberattaque")
    public String showModuleCyberattaque() {
        return "challenge/cyberattaque/module-cyberattaque";
    }

    @GetMapping("/base-cyberattaque")
    public String showBaseCyberattaque() {
        return "challenge/cyberattaque/base-cyberattaque";
    }

    @GetMapping("/module-reseaux")
    public String showModuleReseaux() {
        return "challenge/reseau/module-reseaux";
    }

    @GetMapping("/base-reseaux")
    public String showBaseReseaux() {
        return "challenge/reseau/base-reseaux";
    }

    @GetMapping("/module-securite")
    public String showModules() {
        return "challenge/cyberattaque/module-securite";
    }

    // ============================================================
    // Section 8: Contact
    // ============================================================

    @GetMapping("/contact")
    public String afficherFormulaireContact() {
        return "contact";
    }

    // ============================================================
    // Section 9: Challenge mot de passe
    // ============================================================

    @GetMapping("/challenge-motdepasse")
    public String afficherChallengeMotdepasse() {
        return "/challenge/cyberattaque/challenge-motdepasse";
    }

    // ============================================================
    // Section 10: Quiz cyberattaque
    // ============================================================

    @GetMapping("/quiz-cyberattaque")
    public String showQuizCyberattaques() {
        return "challenge/cyberattaque/quiz-cyberattaque";
    }
}