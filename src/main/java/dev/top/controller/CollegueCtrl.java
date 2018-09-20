package dev.top.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Collegue;
import dev.top.entities.Formulaire;
import dev.top.services.CollegueService;

@CrossOrigin
@RestController()
@RequestMapping("/collegues")
public class CollegueCtrl {

	private CollegueService collegueService;

	public CollegueCtrl(CollegueService collegueService) {
		this.collegueService = collegueService;
	}

	@GetMapping
	public ResponseEntity<List<Collegue>> findAll() {
		return ResponseEntity.ok(this.collegueService.listerCollegues());
	}

	@GetMapping("/{pseudo}")
	public ResponseEntity<Optional<Collegue>> findSpecific(@PathVariable String pseudo) {
		return ResponseEntity.ok(this.collegueService.findWithPseudo(pseudo));
	}

	@PatchMapping("/{pseudo}")
	public ResponseEntity<Collegue> patch(@PathVariable String pseudo, @RequestBody String avis) {
		Collegue collegueModifie = this.collegueService.modifierScore(pseudo, avis);
		return ResponseEntity.ok(collegueModifie);
	}

	@PostMapping("/nouveau")
	public ResponseEntity<Collegue> requestCollegue(@RequestBody Formulaire form) {
		Collegue newCollegue = this.collegueService.findByMatricule(form);
		return ResponseEntity.ok(newCollegue);
	}
}
