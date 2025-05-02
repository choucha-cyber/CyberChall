package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modules")
public class SousModulesController {
	
	// ============================================================
    // Section 1: Sous-modules Cyberattaques : Affichages ==> GET
    // ============================================================
    @GetMapping("/cyberattaque/exploitation-vulnerabilite")
    public String showExploitationVulnerabilitePage() {
        return "/cyberattaque/exploitation-vulnerabilite";  
    }
    
    @GetMapping("/cyberattaque/force-brute")
    public String showForceBrutePage() {
        return "/cyberattaque/force-brute";  
    }
    
    @GetMapping("/cyberattaque/types-cyberattaques")
    public String showTypesCyberattaquesPage() {
        return "/cyberattaque/types-cyberattaques";  
    }
    
    // =================================================================
    // Section 2: Sous-modules Protections des données : Affichages ==> GET
    // =================================================================
    @GetMapping("/protection/donnee-perso")
    public String showProtectionDonneePage() {
        return "/protection/donnee-perso";  
    }
    
    @GetMapping("/protection/rgpd")
    public String showRgpdPage() {
        return "/protection/rgpd";  
    }
    
    @GetMapping("/protection/tracking")
    public String showTrackingPage() {
        return "/protection/tracking";  
    }
    
    // =================================================================
    // Section 3: Sous-modules Réseaux : Affichages ==> GET
    // =================================================================
    @GetMapping("/reseau/algorithmes")
    public String showAlgorithmesPage() {
        return "/reseau/algorithmes";  
    }
    
    @GetMapping("/reseau/bots-fake")
    public String showBotsFakePage() {
        return "/reseau/bots-fake";  
    }
    
    @GetMapping("/reseau/cyber-harcelement")
    public String showcyberHarcelementPage() {
        return "/reseau/cyber-harcelement";  
    }
    
    @GetMapping("/reseau/deepfakes")
    public String showDeepfakesPage() {
        return "/reseau/deepfakes";  
    }
    
    @GetMapping("/reseau/e-reutation")
    public String showEreputationPage() {
        return "/reseau/e-reutation";  
    }
    
    @GetMapping("/reseau/propagande")
    public String showPropagandePage() {
        return "/reseau/propagande";  
    }
    
    @GetMapping("/reseau/responsabilite-numerique")
    public String showresponsabiliteNumeriquePage() {
        return "/reseau/responsabilite-numerique";  
    }
    
    // =================================================================
    // Section 4: Sous-modules Sécurité des appareils : Affichages ==> GET
    // =================================================================
    @GetMapping("/securite/bluetooth-iot")
    public String showBluetoothIotPage() {
        return "/securite/bluetooth-iot";  
    }
    
    @GetMapping("/securite/securite-appareils")
    public String showSecuriteAppareilPage() {
        return "/securite/securite-appareils";  
    }
    
    @GetMapping("/securite/wifi-puplic-vpn")
    public String showWifiPublicVpnPage() {
        return "/securite/wifi-puplic-vpn";  
    }
}
