package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    
    private String image;
    
    //lien de redirection vers le module en particulier (page de détails : base + quizz)
    private String description;
    
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SousModule> sousModules;

    // 🛠️ Constructeurs
    public Module() {}

	public Module(Long id, String nom, String image, String description, List<SousModule> sousModules) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.image = image;
		this.sousModules = sousModules;
	}

    // ✅ Getters & Setters
	public Module(String nom, String description) {
    	this.nom = nom;
        this.description = description;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<SousModule> getSousModules() {
		return sousModules;
	}

	public void setSousModules(List<SousModule> sousModules) {
		this.sousModules = sousModules;
	}
   
}