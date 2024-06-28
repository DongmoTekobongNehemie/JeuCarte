package carte;

import java.util.Objects;

public class Card {
	
	private NomCarte Nom; 
	private String numCarte;

	public Card(NomCarte nom, String numCarte) {
		Nom = nom;
		this.numCarte = numCarte;
	}
	
	public NomCarte getNom() {
		return Nom;
	}

	public void setNom(NomCarte nom) {
		Nom = nom;
	}

	public String getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(String numCarte) {
		this.numCarte = numCarte;
	}

	@Override
	public String toString() {
		return "Carte [Nom=" + Nom + ", numCarte=" + numCarte + "]";
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof Card)) return false;
	    Card autreCarte = (Card) obj;
	    return Objects.equals(Nom, autreCarte.Nom) &&
	           Objects.equals(numCarte, autreCarte.numCarte);
	}
	
}
