package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnexionController {

    @GetMapping("/connexion")
    public String afficherFormulaireConnexion() {
        return "connexion";
    }

    @PostMapping("/connexion")
    public String traiterConnexion(@RequestParam String pseudo,
                                   @RequestParam String password,
                                   @RequestParam String role,
                                   Model model) {

        // Simulation d'authentification (à remplacer avec un vrai service plus tard)
        if ("admin".equals(role)) {
            if ("admin@demo.com".equals(pseudo) && "adminpass".equals(password)) {
                return "redirect:/dashboard"; // page pour admins
            }
        } else if ("eleve".equals(role)) {
            if ("eleve@demo.com".equals(pseudo) && "elevepass".equals(password)) {
                return "redirect:/eleves"; // page pour élèves
            }
        }

        model.addAttribute("error", "Pseudo, mot de passe ou rôle incorrect.");
        return "connexion";
    }
}
