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

	  @ManyToOne
	   private Theme theme;

}
