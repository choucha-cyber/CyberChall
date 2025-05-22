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
    
    //lien de redirection via l'image : vers un module en particulier (page de détails : base + quizz)
    private String image;
    private String link;
    
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SousModule> sousModules;

    // 🛠️ Constructeurs
    public Module() {}

    public Module(Long id, String nom, String image, String link, List<SousModule> sousModules) {
		super();
		this.id = id;
		this.nom = nom;
		this.image = image;
		this.link = link;
		this.sousModules = sousModules;
	}

	// ✅ Getters & Setters
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

	public String getImage() {
		return image;
	}

	public void setDescription(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<SousModule> getSousModules() {
		return sousModules;
	}

	public void setSousModules(List<SousModule> sousModules) {
		this.sousModules = sousModules;
	}
	
}