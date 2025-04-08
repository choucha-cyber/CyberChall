package com.cadettesdelacyber.CyberChall.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Eleve {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nom;
	    private String prenom;
	    private String email;

	    // Relation avec les sessions
	    @ManyToMany(mappedBy = "eleves")
	    private List<Session> sessions;

}
