package com.cadettesdelacyber.CyberChall.controllers;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.services.AdminService;
import com.cadettesdelacyber.CyberChall.services.ModuleService;
import com.cadettesdelacyber.CyberChall.services.SessionService;
import com.cadettesdelacyber.CyberChall.utils.QrCodeUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
/*
@Controller
//@RequestMapping("/session") desactivation provisoire car 2 fichier controller pour les session temporaire
public class SessionViewController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ModuleService moduleService;

    // ==========================================
    // 1. Page d’accueil d'affichage du formulaire de sessions
    // ==========================================
    @GetMapping("/session")
    public String showSessions(HttpServletRequest request, Model model) {
        // Vérification de la session utilisateur, récupération de l'admin connecté
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/";  // Rediriger en page d'accueil si l'utilisateur n'est pas connecté
        }

        String username = (String) session.getAttribute("username");
        Admin admin = adminService.findByUsername(username);

        // Récupérer toutes les sessions de l'admin
        List<Session> sessions = sessionService.findByAdmin(admin);

        // Ajouter les sessions au modèle
        model.addAttribute("sessions", sessions);
        
        // ⚠️ Ajouter ici la liste des modules avec leurs sous-modules
        model.addAttribute("modules", moduleService.findAllWithSousModules());

        return "session/session";
    }
    // ==================================
    // 2. Création d’une nouvelle session
    // ==================================
    @PostMapping("")
    public String createSession(@RequestParam("sousModules") List<SousModule> sousModules,
                                @RequestParam("dateDebut") String dateDebutStr,
                                @RequestParam("duree") int duree,
                                @RequestParam("token") String token,
                                @RequestParam("id") Long adminId,
                                Model model,
                                HttpServletRequest request) {

        Admin admin = adminService.findById(adminId);
        if (admin == null) {
            return "redirect:/admin/connexion-admin";
        }

        // Conversion de la date
        LocalDate dateDebut = LocalDate.parse(dateDebutStr);

        // Création de la session
        Session sessionCreee = sessionService.createSession(
            dateDebut,
            duree,
            token,
            sousModules,
            admin
        );

        // Génération d'un lien temporaire vers la page d'accueil-admin avec les sous-modules sélectionnés
        //String sessionLink = "/admin/accueil-admin?sousModules=" + String.join(",", sousModules);
        String sessionLink = "/admin/accueil-admin";
        if (!sousModules.isEmpty()) {
            sessionLink += "?" + sousModules.stream()
                                  .map(m -> "sousModules=" + m)
                                  .collect(Collectors.joining("&"));
        }

        model.addAttribute("sessionLink", sessionLink);

        // (Optionnel) Stockage des sous-modules en session si tu veux les lire depuis la page d’accueil
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("sousModulesActuels", sousModules);

        // (Optionnel) Génération du QR code si tu actives ça plus tard
        try {
            String qrCodeBase64 = QrCodeUtils.generateQRCodeBase64(sessionLink, 200, 200);
            model.addAttribute("qrCode", qrCodeBase64);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("qrCodeError", "Erreur lors de la génération du QR code.");
        }

        // Ajouter la session créée pour affichage
        model.addAttribute("createdSession", sessionCreee);

        return "account";  // Redirige vers la page du compte (affichage des sessions de l'admin connecté)
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
 
 // Petite classe DTO interne
 static class QuickSessionRequest {
     private List<String> modules;

     public List<String> getModules() {
         return modules;
     }

     public void setModules(List<String> modules) {
         this.modules = modules;
     }
 }

}

*/