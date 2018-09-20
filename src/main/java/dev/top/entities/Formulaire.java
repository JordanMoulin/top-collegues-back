package dev.top.entities;

public class Formulaire {
	private String matricule;
	private String pseudo;
	private String urlImage;

	public Formulaire() {
		super();
	}

	public Formulaire(String matricule, String pseudo, String urlImage) {
		super();
		this.matricule = matricule;
		this.pseudo = pseudo;
		this.urlImage = urlImage;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
}
