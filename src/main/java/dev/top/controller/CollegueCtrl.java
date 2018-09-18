package dev.top.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@CrossOrigin
@RestController()
@RequestMapping("/collegues")
public class CollegueCtrl {

	private CollegueRepo collegueRepo;

	public CollegueCtrl(CollegueRepo collegueRepo) {
		super();
		this.collegueRepo = collegueRepo;
	}

	@GetMapping
	public List<Collegue> findAll() {
		return this.collegueRepo.findAll();
	}

	@GetMapping("/{nom}")
	public Collegue findSpecific(@PathVariable String nom) {
		return this.collegueRepo.findByNom(nom);
	}

	@PatchMapping("/{nom}")
	public @ResponseBody ResponseEntity<Collegue> patch(@PathVariable String nom, @RequestBody String avis) {
		Collegue updateScore = new Collegue();
		if (avis.contains("AIMER")) {
			Integer score = collegueRepo.findByNom(nom).getScore() + 10;
			updateScore = collegueRepo.findByNom(nom);
			updateScore.setScore(score);
		}
		if (avis.contains("DETESTER")) {
			Integer score = collegueRepo.findByNom(nom).getScore() - 5;
			updateScore = collegueRepo.findByNom(nom);
			updateScore.setScore(score);
		}
		collegueRepo.save(updateScore);
		return new ResponseEntity<Collegue>(collegueRepo.findByNom(nom), HttpStatus.OK);
	}

}
