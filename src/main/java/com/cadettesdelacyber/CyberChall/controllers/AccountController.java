package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.models.SessionTemporaire;
import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.services.SessionService;
import com.cadettesdelacyber.CyberChall.services.SessionTemporaireService;
import com.cadettesdelacyber.CyberChall.utils.QrCodeUtils;
import com.cadettesdelacyber.CyberChall.services.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private  SessionTemporaireService sessionTemporaireService;

    // Page principale de compte - accessible uniquement si connecté
    @GetMapping("/admin/account")
    public String afficherCompte(HttpSession sessionHttp, Model model) {
        Admin admin = (Admin) sessionHttp.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/connexion-admin";
        }

        // Sessions classiques
        List<Session> sessions = sessionService.findByAdmin(admin);
        model.addAttribute("sessions", sessions);

        // Sessions temporaires
        List<SessionTemporaire> sessionTemporaires = sessionTemporaireService.getSessionsParAdmin(admin);
        System.out.println("Sessions temporaires : " + sessionTemporaires.size());
        model.addAttribute("sessionTemporaires", sessionTemporaires);

        Map<String, String> qrCodes = new HashMap<>();
        for (SessionTemporaire s : sessionTemporaires) {
            try {
                String url = "http://localhost:4040/admin/accueil-admin?" + s.getToken();
                String qrBase64 = QrCodeUtils.generateQRCodeBase64(url, 200, 200);
                s.setQrCodeBase64(qrBase64);  // bien s'assurer que l'objet a un champ pour ça
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("admin", admin);
        return "admin/account";
    }

    
    @PostMapping("/admin/account")
    public String createSessionForAccount(@RequestParam String titre,
                                          @RequestParam String description,
                                          @RequestParam List<SousModule> sousModules,
                                          @RequestParam String dateDebut,
                                          @RequestParam int duree,
                                          @RequestParam String token,
                                          @RequestParam Long id,
                                          HttpServletRequest request) {

        // Vérification de la session utilisateur, récupération de l'admin connecté
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/";  // Si l'utilisateur n'est pas connecté, redirige vers la page de connexion
        }

        String username = (String) session.getAttribute("username");
        Admin admin = adminService.findByUsername(username); // Récupère l'admin

        // Création de la session avec les paramètres récupérés
        Session newSession = sessionService.createSession(
            LocalDate.parse(dateDebut), // Conversion de la date
            duree,  // Durée (en mois)
            token,  // Le token de la session
            sousModules,  // Liste des sous-modules
            admin  // L'admin connecté
        );

        // Redirection vers la page account (n'oubliez pas avec un message de session créée)
        return "redirect:/admin/account";  // Renvoie vers la page 'account' pour afficher la/les session-s créée-s
    }


    // GET - Afficher le formulaire de création de compte
    @GetMapping("/admin/create-account")
    public String showCreateAccount() {
        return "admin/create-account";
    }

    // POST - Traiter le formulaire de création ( EVOLUTION : A FAIRE AVEC EMAIL PLUTOT QUE USERNAME)
    @PostMapping("/admin/create-account")
    public String createAccount(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        if (adminService.findByUsername(username) != null) {
            model.addAttribute("error", "Ce nom d'utilisateur existe déjà.");
            return "admin/create-account";
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password); // le service encode le mot de passe

        adminService.saveAdmin(admin); // méthode qui encode et enregistre
        System.out.println(admin + "sauvegardé");

        return "redirect:/";
    }
}