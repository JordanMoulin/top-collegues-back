package dev.top.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.top.entities.Collegue;
import dev.top.entities.Formulaire;
import dev.top.exceptions.InvalidMatriculeException;
import dev.top.exceptions.PseudoInvalideException;
import dev.top.exceptions.ServiceException;
import dev.top.repos.CollegueRepo;

@Service
public class CollegueService {

	private CollegueRepo collegueRepo;
	private RestTemplate template = new RestTemplate();

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
	public Optional<Collegue> findWithPseudo(String pseudo) {
		return this.collegueRepo.findByPseudo(pseudo);
	}

	/**
	 * 
	 * @param nom
	 * @param avisUtilisateur
	 * @return
	 * @throws ServiceException
	 * @throws PseudoInvalideException
	 */
	public Collegue modifierScore(String pseudo, String avisUtilisateur)
			throws ServiceException, PseudoInvalideException {

		return this.collegueRepo.findByPseudo(pseudo).map(collegueTrouve -> {

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

	public Collegue findByMatricule(Formulaire form) throws InvalidMatriculeException {
		Collegue newCollegue;
		String matricule = form.getMatricule();
		Collegue[] collegue = template
				.getForObject("http://collegues-api.cleverapps.io/collegues?matricule=" + matricule, Collegue[].class);
		if (collegue.length == 0) {
			throw new InvalidMatriculeException();
		}
		System.out.println(collegue[0]);
		if (StringUtils.isBlank(form.getUrlImage())) {
			newCollegue = new Collegue(form.getPseudo(), collegue[0].getNom(), collegue[0].getPrenom(),
					collegue[0].getAdresse(), 0, collegue[0].getPhoto());
		} else {
			newCollegue = new Collegue(form.getPseudo(), collegue[0].getNom(), collegue[0].getPrenom(),
					collegue[0].getAdresse(), 0, form.getUrlImage());
		}
		if (!collegueExiste(form.getPseudo())) {
			this.collegueRepo.save(newCollegue);
		}
		return newCollegue;
	}

	public boolean collegueExiste(String pseudo) {
		Optional<Collegue> collegue = this.collegueRepo.findByPseudo(pseudo);
		return collegue.isPresent();
	}
}
