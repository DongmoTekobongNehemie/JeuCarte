package carte;

import java.util.ArrayList;

public class Player {
	private String nomJoueur;
	private ArrayList<Card> Main = new ArrayList<Card>();

	public Player(String nomJoueur) {
		this.nomJoueur= nomJoueur;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public ArrayList<Card> getMain() {
		return Main;
	}

	public void setMain(ArrayList<Card> main) {
		Main = main;
	}
	
	
	
	
	
}
