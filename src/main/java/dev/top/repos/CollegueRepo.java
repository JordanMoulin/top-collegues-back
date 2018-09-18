package dev.top.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.top.entities.Collegue;

public interface CollegueRepo extends JpaRepository<Collegue, Integer> {

	Collegue findByNom(String nom);
}
