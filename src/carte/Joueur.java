package carte;

import java.util.ArrayList;

public class Joueur {
	private String nomJoueur;
	private ArrayList<Carte> Main = new ArrayList<Carte>();

	public Joueur(String nomJoueur) {
		this.nomJoueur= nomJoueur;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public ArrayList<Carte> getMain() {
		return Main;
	}

	public void setMain(ArrayList<Carte> main) {
		Main = main;
	}
	
	
	
	
	
}
