package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cadettesdelacyber.CyberChall.models.Admin;

import jakarta.servlet.http.HttpSession;

/*   Cette méthode dans la classe GlobalVariablesController 
 * sert à injecter automatiquement certaines variables 
 * dans tous les modèles (Model) de toutes les vues Thymeleaf de ton application, 
 * sans avoir à les redéfinir à chaque fois dans chaque contrôleur  */
@ControllerAdvice
public class GlobalVariablesController {

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            model.addAttribute("admin", admin);
            model.addAttribute("estConnecte", true);
        } else {
            model.addAttribute("estConnecte", false);
        }
    }
}
