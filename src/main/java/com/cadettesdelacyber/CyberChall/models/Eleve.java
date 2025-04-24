package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Eleve extends User {

    private String niveau;     // Ex: 5ème, 3ème, etc.

    // Constructeur par défaut (requis par JPA)
    public Eleve() {
        super();
        this.setRole("eleve");
    }

    // Constructeur pratique pour l'authentification
    public Eleve(String username, String password) {
        super(username, password);
        this.setRole("eleve");
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}