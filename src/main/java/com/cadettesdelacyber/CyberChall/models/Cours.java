package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cours")
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String contenu;

	@OneToOne
	@JoinColumn(name = "sousmodule_id", unique = true)
	private SousModule sousModule;

	public Cours() {

	}

	public Cours(Long id, String contenu, SousModule sousModule) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.sousModule = sousModule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public SousModule getSousModule() {
		return sousModule;
	}

	public void setSousModule(SousModule sousModule) {
		this.sousModule = sousModule;
	}

}
