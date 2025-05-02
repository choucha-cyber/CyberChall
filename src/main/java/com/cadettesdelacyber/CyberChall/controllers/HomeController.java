package com.cadettesdelacyber.CyberChall.controllers;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.services.ModuleService;

@Controller
public class HomeController {
	
	@Autowired
	private ModuleService moduleService;


    // ============================================================
    // Section 1: Page d'accueil --> connexion (si connecté -->accueil-admin
    // ============================================================

	@GetMapping("/")
	public String index(HttpSession sessionHttp) {
	    Boolean estConnecte = (Boolean) sessionHttp.getAttribute("estConnecte");

	    if (Boolean.TRUE.equals(estConnecte)) {
	        return "redirect:/admin/accueil-admin";
	    }

	    return "index";
	}

    // ============================================================
    // Section 2: Page d'accueil Admin :  IMPORTANT !! user externe accède a cette page par QRcode avec sous-modules selectionnés
    // ============================================================

	@GetMapping("/admin/accueil-admin")
	public String accueilAdmin(HttpSession session,
	                           @RequestParam(required = false) List<String> sousModules,
	                           Model model) {
	    
	    Admin admin = (Admin) session.getAttribute("admin");
	    
	    if (admin != null) {
	        // Cas 1 : un admin est connecté → il voit tous les modules
	        model.addAttribute("estConnecte", true);
	        model.addAttribute("admin", admin);
	        model.addAttribute("modules", moduleService.getAllModules());  // Récupère tous les modules disponibles
	        model.addAttribute("isSessionMode", false);
	        
	        
	    } else if (sousModules != null && !sousModules.isEmpty()) {
	        // Cas 2 : un élève accède via lien/QR → modules limités
	        model.addAttribute("estConnecte", false);
	        model.addAttribute("modules", sousModules);  // Juste les modules de la session
	        model.addAttribute("isSessionMode", true);
	    } else {
	        // Accès non autorisé
	        return "redirect:/";
	    }

	    return "admin/accueil-admin";  // Le même template, mais logique conditionnelle dans Thymeleaf
	}


    // ============================================================
    // Section 3: Module cyberattaque
    // ============================================================

	@GetMapping("/module-cyberattaque")
    public String showModuleCyberattaque() {
        return "challenge/cyberattaque/module-cyberattaque";
    }

    @GetMapping("/base-cyberattaque")
    public String showBaseCyberattaque() {
        return "challenge/cyberattaque/base-cyberattaque";
    }
    
    @GetMapping("/quiz-cyberattaque")
    public String showQuizCyberattaques() {
        return "challenge/cyberattaque/quiz-cyberattaque";
    }
    
    // ============================================================
    // Section 4: Module reseaux
    // ============================================================
    

    @GetMapping("/module-reseaux")
    public String showModuleReseaux() {
        return "challenge/reseau/module-reseaux";
    }

    @GetMapping("/base-reseau")
    public String showBaseReseaux() {
        return "challenge/reseau/base-reseaux";
    }
    
    @GetMapping("/quiz-reseau")
    public String showQuizzReseaux() {
        return "challenge/reseau/quiz-reseau";
    }
    
    // ============================================================
    // Section 5: Module Securite
    // ============================================================
    
    @GetMapping("/module-securite")
    public String showModuleSecurite() {
        return "challenge/securite/module-securite";
    }
    
    @GetMapping("/base-securite")
    public String showBaseSecurite() {
        return "challenge/securite/base-securite";
    }
    
    @GetMapping("/quiz-securite")
    public String showQuizSecurite() {
        return "challenge/securite/quiz-securite";
    }
    
    // ============================================================
    // Section 6: Challenge mot de passe
    // ============================================================

    @GetMapping("/challenge-motdepasse")
    public String afficherChallengeMotdepasse() {
        return "/challenge/cyberattaque/challenge-motdepasse";
    }


    // ============================================================
    // Section 7: Contact
    // ============================================================

    @GetMapping("/contact")
    public String afficherFormulaireContact() {
        return "contact";
    }
 
}