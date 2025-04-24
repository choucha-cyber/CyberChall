package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Challenge {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   private String titre;
	   private String description;
	   private String niveau;
	   
	   
	public Challenge(Long id, String titre, String description, String niveau) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.niveau = niveau;
	}

	public Long getId() {
		return id;
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
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

}
