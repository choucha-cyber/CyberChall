package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

	@GetMapping("/deconnexion")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Invalider la session pour déconnecter l'utilisateur
        session.invalidate();

        // Ajouter un message flash pour la redirection
        redirectAttributes.addFlashAttribute("message", "Vous êtes bien déconnecté.");

        // Rediriger vers la page d'accueil
        return "redirect:/";
    }
}
