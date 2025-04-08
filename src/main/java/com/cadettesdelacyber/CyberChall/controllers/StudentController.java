package com.cadettesdelacyber.CyberChall.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadettesdelacyber.CyberChall.models.Eleve;
import com.cadettesdelacyber.CyberChall.services.EleveService;

@RestController
@RequestMapping("/eleves")
public class StudentController {

	@Autowired
	private EleveService eleveService;

	@GetMapping
	public List<Eleve> getAll() {
		return eleveService.getAllEleves();
	}

	@GetMapping("/{id}")
	public Eleve getOne(@PathVariable Long id) {
		return eleveService.getEleve(id);
	}

	@PostMapping
	public Eleve create(@RequestBody Eleve eleve) {
		return eleveService.saveEleve(eleve);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		eleveService.deleteEleve(id);
	}

	@GetMapping("/home")
	public String studentHome() {
		return "student/accueil_student"; // chemin vers accueil_student.html
	}

	@GetMapping("/contact")
	public String contactStudent() {
		return "student/contact_student";
	}

}
