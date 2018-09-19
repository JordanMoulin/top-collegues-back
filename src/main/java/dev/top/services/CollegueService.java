package dev.top.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.top.entities.Collegue;
import dev.top.exceptions.PseudoInvalideException;
import dev.top.exceptions.ServiceException;
import dev.top.repos.CollegueRepo;

@Service
public class CollegueService {

	private CollegueRepo collegueRepo;

	/**
	 * 
	 * @param collRepo
	 */
	public CollegueService(CollegueRepo collRepo) {
		super();
		this.collegueRepo = collRepo;
	}

	/**
	 * 
	 * @return
	 */
	public List<Collegue> listerCollegues() {
		return this.collegueRepo.findAll();
	}

	/**
	 * 
	 * @param nom
	 * @return
	 */
	public Optional<Collegue> findWithName(String nom) {
		return this.collegueRepo.findByNom(nom);
	}

	/**
	 * 
	 * @param nom
	 * @param avisUtilisateur
	 * @return
	 * @throws ServiceException
	 * @throws PseudoInvalideException
	 */
	public Collegue modifierScore(String nom, String avisUtilisateur) throws ServiceException, PseudoInvalideException {

		return this.collegueRepo.findByNom(nom).map(collegueTrouve -> {

			if (avisUtilisateur.contains("AIMER")) {
				collegueTrouve.setScore(collegueTrouve.getScore() + 10);
			}

			if (avisUtilisateur.contains("DETESTER")) {
				collegueTrouve.setScore(collegueTrouve.getScore() - 5);
			}

			this.collegueRepo.save(collegueTrouve);

			return collegueTrouve;
		}).orElseThrow(() -> new PseudoInvalideException());

	}
}
