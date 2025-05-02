package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //date de debut session
    private LocalDate dateDebut;

    private String dateDebutFormatted; // Ajouter ce champ pour stocker la date formatée

    
   //date de durée : un mois
    private int duree;

    //token (jeton unique) id de session
    private String token;  // Lien unique généré
    
    
   // Liste des modules sélectionnés/par session  
    @ManyToMany
    @JoinTable(
        name = "session_sous_module",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "sous_module_id")
    )
    private List<SousModule> sousModules;


    //un admin déteint une ou plusieurs sessions
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    
    
    // === Constructeurs ===
    
    public Session() {}


	public Session(Long id, LocalDate dateDebut, String dateDebutFormatted, int duree, String token,
			List<SousModule> sousModules, Admin admin) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateDebutFormatted = dateDebutFormatted;
		this.duree = duree;
		this.token = token;
		this.sousModules = sousModules;
		this.admin = admin;
	}

	// === Getters & Setters ===

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}


	public String getDateDebutFormatted() {
		return dateDebutFormatted;
	}


	public void setDateDebutFormatted(String dateDebutFormatted) {
		this.dateDebutFormatted = dateDebutFormatted;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public List<SousModule> getSousModules() {
		return sousModules;
	}


	public void setSousModules(List<SousModule> sousModules) {
		this.sousModules = sousModules;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}