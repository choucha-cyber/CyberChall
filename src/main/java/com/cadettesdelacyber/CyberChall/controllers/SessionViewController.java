package com.cadettesdelacyber.CyberChall.controllers;

import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.models.User;
import com.cadettesdelacyber.CyberChall.services.SessionService;
import com.cadettesdelacyber.CyberChall.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionViewController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    // ======================
    // 1. Page d’accueil des sessions
    // ======================
    @GetMapping("")
    public String showSessionPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/login"; // ou la page d'accueil
        }

        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username); // Assure-toi que cette méthode existe

        List<Session> sessions = sessionService.getSessionsByUser(user);
        model.addAttribute("sessions", sessions);

        return "session/session";
    }

    // ======================
    // 2. Page du formulaire de création de session
    // ======================
    @GetMapping("/session-creation")
    public String showSessionCreationForm() {
        return "session/session-creation";
    }

    // ======================
    // 3. Création d’une nouvelle session
    // ======================
    @PostMapping("/create")
    public String createSession(@RequestParam String titre,
                                @RequestParam String description,
                                @RequestParam String modules,
                                @RequestParam("userId") User user,
                                Model model) {
        Session newSession = sessionService.createSession(titre, description, modules, user);
        model.addAttribute("session", newSession);
        return "redirect:/session"; // Redirection vers la liste ou une vue de confirmation
    }

    // ======================
    // 4. Statistiques de sessions
    // ======================
    @GetMapping("/session-statistic")
    public String showSessionStatistics(Model model) {
    	return "session/session-statistic";
    }

    // ======================
    // Utilitaire : extraire un token de cookie (si besoin)
    // ======================
    private String extractSessionToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}