package carte;

import exceptions.EmptyListOfCardsException;

public class Main {
	
	public static void main(String[] args) throws EmptyListOfCardsException {
		 JeuMensonge jeu = new JeuMensonge();
		 jeu.jouer();
	}
}
