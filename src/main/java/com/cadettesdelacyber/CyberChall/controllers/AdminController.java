package com.cadettesdelacyber.CyberChall.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.services.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin> getAll() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getOne(@PathVariable Long id) {
        return adminService.getAdmin(id);
    }

    @PostMapping
    public Admin create(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
    
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model, Principal principal) {
        // Ici tu peux charger les infos de l’admin connecté si tu veux les afficher
        // Exemple :
        // Admin admin = adminService.findByEmail(principal.getName());
        // model.addAttribute("admin", admin);

        return "admin/dashboard_admin";
    }
  

    @GetMapping("/contact")
    public String contactAdmin() {
        return "admin/contact_admin";
    }

    @GetMapping("/")
    public String accueilAdmin() {
        return "admin/accueil_admin";
  
    }
}

