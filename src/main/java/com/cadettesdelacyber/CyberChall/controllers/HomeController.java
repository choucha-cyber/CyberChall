package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class HomeController {

	    @GetMapping("/")
	    public String index() {
	        return "index";
	    }

	    @GetMapping("/account")
	    public String accountPage() {
	        return "account"; // src/main/resources/templates/account.html
	    }

	   /* @GetMapping("/contact")
	    public String contactPage() {
	        return "contact"; // ou "contact_student" si tu veux afficher celle des élèves
	    } */

	    @GetMapping("/session")
	    public String loginPage() {
	        return "/session/session";
	    }
	    
	    @GetMapping("/session_creation")
	    public String sessionPage() {
	        return "/session/session_creation";
	    }
	    
	    @GetMapping("/contact")
	    public String afficherFormulaireContact() {
	        return "contact"; // ou "student/contact_student" selon ton arborescence
	    }

	    /*@PostMapping("/envoyer-message")
	    public String traiterFormulaireContact(@RequestParam String nom,
	                                           @RequestParam String email,
	                                           @RequestParam String message,
	                                           Model model) {
	        // Traitement ici
	        model.addAttribute("confirmation", "Merci pour ton message !");
	        return "contact";
	    } */
	}
