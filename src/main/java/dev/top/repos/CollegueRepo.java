package dev.top.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.top.entities.Collegue;

public interface CollegueRepo extends JpaRepository<Collegue, Integer> {

	Optional<Collegue> findByNom(String nom);
}
