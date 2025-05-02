package com.cadettesdelacyber.CyberChall.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.services.ModuleService;
import com.cadettesdelacyber.CyberChall.services.SousModuleService;
import com.cadettesdelacyber.CyberChall.models.Module;

import jakarta.annotation.PostConstruct;

@Component
public class SousModuleDataLoader {

    @Autowired
    private SousModuleService sousModuleService;

    @Autowired
    private ModuleService moduleService;

    @PostConstruct
    public void init() {
        List<ModuleWithSousModules> modulesAvecSousModules = Arrays.asList(
            new ModuleWithSousModules("Protection des données et vie privée", Map.of(
                "Données personnelles", "/modules/protection/donnee-perso",
                "Tracking et cookies", "/modules/protection/tracking",
                "RGPD", "/modules/protection/rgpd"
            )),
            new ModuleWithSousModules("Réseaux sociaux", Map.of(
                "Les algorithmes", "/modules/reseau/algorithmes",
                "Bots et faux comptes", "/modules/reseau/bots-fake",
                "Propagande et manipulation de masse", "/modules/reseau/propagande",
                "Deepfakes et contenus trompeurs", "/modules/reseau/deepfakes",
                "Lutte contre le cyberharcèlement", "/modules/reseau/cyber-harcelement",
                "Empreinte numérique et réputation en ligne", "/modules/reseau/e-reutation",
                "Responsabilité numérique", "/modules/reseau/responsabilite-numerique"
            )),
            new ModuleWithSousModules("Sécurité des appareils", Map.of(
                "Sécuriser son ordinateur et smartphone", "/modules/securite/securite-appareils",
                "Wi-Fi public et VPN", "/modules/securite/wifi-puplic-vpn",
                "Bluetooth et IoT", "/modules/securite/bluetooth-iot"
            )),
            new ModuleWithSousModules("Cyberattaques", Map.of(
                "Types de cyberattaques", "/modules/cyberattaque/types-cyberattaques",
                "Force brute et credential stuffing", "/modules/cyberattaque/force-brute",
                "Exploitation des vulnérabilités", "/modules/cyberattaque/exploitation-vulnerabilite"
            ))
        );

        for (ModuleWithSousModules data : modulesAvecSousModules) {
            Module existingModule = moduleService.findByNom(data.getNom());
            if (existingModule == null) {
                Module module = new Module();
                module.setNom(data.getNom());
                existingModule = moduleService.createModule(module);
            }

            for (Map.Entry<String, String> entry : data.getSousModules().entrySet()) {
                String titre = entry.getKey();
                String lien = entry.getValue();

                boolean exists = sousModuleService.existsByTitreAndModule(titre, existingModule);
                if (!exists) {
                    SousModule sousModule = new SousModule();
                    sousModule.setTitre(titre);
                    sousModule.setLink(lien);
                    sousModule.setModule(existingModule);
                    sousModuleService.saveSousModule(sousModule);
                }
            }
        }
    }

    private static class ModuleWithSousModules {
        private String nom;
        private Map<String, String> sousModules;

        public ModuleWithSousModules(String nom, Map<String, String> sousModules) {
            this.nom = nom;
            this.sousModules = sousModules;
        }

        public String getNom() {
            return nom;
        }

        public Map<String, String> getSousModules() {
            return sousModules;
        }
    }
}