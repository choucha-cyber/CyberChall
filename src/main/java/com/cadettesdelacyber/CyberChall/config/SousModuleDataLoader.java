package com.cadettesdelacyber.CyberChall.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.services.ModuleService;
import com.cadettesdelacyber.CyberChall.services.SousModuleService;
import com.cadettesdelacyber.CyberChall.models.Module;


import jakarta.annotation.PostConstruct;

//insère à la fois le module et ses sous-modules.

@Component
public class SousModuleDataLoader {

    @Autowired
    private SousModuleService sousModuleService;

    @Autowired
    private ModuleService moduleService;

    @PostConstruct
    public void init() {
        // Liste de modules et leurs sous-modules à insérer dans la base de données
        List<ModuleWithSousModules> modulesAvecSousModules = Arrays.asList(
            new ModuleWithSousModules("Protection des données et vie privée", Arrays.asList("Données personnelles", "Tracking et cookies", "RGPD")),
            new ModuleWithSousModules("Réseaux sociaux", Arrays.asList("Les algorithmes", "Bots et faux comptes", "Propagande et manipulation de masse", "Deepfakes et contenus trompeurs", "Lutte contre le cyberharcèlement", "Empreinte numérique et réputation en ligne", "Responsabilité numérique")),
            new ModuleWithSousModules("Sécurité des appareils", Arrays.asList("Sécuriser son ordinateur et smartphone", "Wi-Fi public et VPN", "Bluetooth et IoT")),
            new ModuleWithSousModules("Cyberattaques", Arrays.asList("Types de cyberattaques", "Force brute et credential stuffing", "Exploitation des vulnérabilités"))
        );

        for (ModuleWithSousModules data : modulesAvecSousModules) {
            Module module = new Module();
            module.setNom(data.getNom());

            // Sauvegarder le module
            Module savedModule = moduleService.createModule(module);

            // Sauvegarder les sous-modules associés
            for (String sousModuleName : data.getSousModules()) {
                SousModule sousModule = new SousModule();
                sousModule.setTitre(sousModuleName);
                sousModule.setModule(savedModule); // Relier ce sous-module au module parent
                sousModuleService.saveSousModule(sousModule);
            }
        }
    }

    // Classe interne pour représenter un module avec ses sous-modules
    private static class ModuleWithSousModules {
        private String nom;
        private List<String> sousModules;

        public ModuleWithSousModules(String nom, List<String> sousModules) {
            this.nom = nom;
            this.sousModules = sousModules;
        }

        public String getNom() {
            return nom;
        }

        public List<String> getSousModules() {
            return sousModules;
        }
    }
}

