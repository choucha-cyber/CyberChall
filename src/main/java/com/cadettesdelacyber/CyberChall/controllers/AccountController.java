package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.models.User;
import com.cadettesdelacyber.CyberChall.services.SessionService;
import com.cadettesdelacyber.CyberChall.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    // ******************************************************************
    // Page principale de compte - accessible uniquement aux admins
    // ******************************************************************
    @GetMapping("/account")
    public String showAccount(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || !"admin".equals(session.getAttribute("role"))) {
            return "redirect:/"; // Non connecté ou pas admin
        }

        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);

        if (!(user instanceof Admin)) {
            return "redirect:/"; // sécurité supplémentaire
        }

        // Récupérer toutes les sessions de l'utilisateur
        List<Session> sessions = sessionService.findSessionsByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("sessions", sessions);

        return "account"; // Afficher la page account
    }
}