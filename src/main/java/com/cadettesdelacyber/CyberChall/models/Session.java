package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private String token;  // Lien unique généré
    private String lien;

    private String modules; // Liste des modules en format "Module1,Module2,Module3"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Admin qui a créé la session

    // === Constructeurs ===
    public Session() {}

    // Constructeur pour créer une session avec les informations essentielle
    
    public Session(String titre, String description, String modules, User user) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = LocalDate.now();  // Date de début = date actuelle
        this.dateFin = dateDebut.plusMonths(1); // 1 mois de validité
        this.token = UUID.randomUUID().toString();  // Génère un lien unique pour cette session
        this.lien = "https://challenges.html?modules=" + modules;  // Génère le lien complet
        this.modules = modules;  // Liste des modules
        this.user = user;  // Admin qui crée la session
    }

    public Session(Long id, String titre, String description, LocalDate dateDebut, LocalDate dateFin, String token,
			String lien, String modules, User user) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.token = token;
		this.lien = lien;
		this.modules = modules;
		this.user = user;
	}

	// === Getters & Setters ===
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    

    public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}