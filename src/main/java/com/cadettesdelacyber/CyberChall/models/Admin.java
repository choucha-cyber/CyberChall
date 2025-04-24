package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin extends User {

    // Constructeur par défaut (requis par JPA)
    public Admin() {
        super();
        this.setRole("admin");
    }

    // Constructeur pratique pour l'authentification
    public Admin(String username, String password) {
        super(username, password);
        this.setRole("admin");
    }

}