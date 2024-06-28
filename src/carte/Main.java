package carte;

import exceptions.EmptyListOfCardsException;

public class Main {

	public static void main(String[] args) {

		JeuMensonge jeuMensonge = new JeuMensonge();
		try {
			jeuMensonge.play();

		} catch (EmptyListOfCardsException e) {
			e.getMessage();
		}

	}

}
