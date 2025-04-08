package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROF")
	public class Professeur extends Admin {
	    private String matiere;

}
