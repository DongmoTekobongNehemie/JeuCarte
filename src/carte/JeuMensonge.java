package carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import exceptions.EmptyListOfCardsException;

public class JeuMensonge  implements JeuCarte{

	private static final String[] numberCardsList = { "A", "7", "8", "9", "10", "J", "Q", "K" };
	
	public JeuMensonge() {
		super();
	}

	// creation du paquet de carte
	@Override
	public void createCards(List<Card> listeDeCarte) throws EmptyListOfCardsException {
		for (int j = 0; j < numberCardsList.length; j++) {
			listeDeCarte.add(new Card(NomCarte.Carreau, numberCardsList[j]));
			listeDeCarte.add(new Card(NomCarte.Trefle, numberCardsList[j]));
			listeDeCarte.add(new Card(NomCarte.Coeur, numberCardsList[j]));
			listeDeCarte.add(new Card(NomCarte.Pique, numberCardsList[j]));
		}
	}

	// melage des carte
	@Override
	public void mixCards(List<Card> listeDeCarte) throws EmptyListOfCardsException {
		Collections.shuffle(listeDeCarte);
		System.out.println("-------------------------Melange des carte -----------------------\n");
		showCards(listeDeCarte);
	}

	public void showCards(List<Card> listeDeCarte) throws EmptyListOfCardsException{
		int i = 1;
		for (Card carte : listeDeCarte) {
			System.out.println(i + "- " + carte.toString() + "\n");
			i++;
		}
	}

	public void createPlayer(List<Player> listeJoueur) throws EmptyListOfCardsException{
		Scanner clavier = new Scanner(System.in);
		System.out.println("Entre le nombre de joueurs => ");
		int nbreJoueur = clavier.nextInt();

		for (int i = 0; i < nbreJoueur; i++) {
			listeJoueur.add(new Player("Joueur_" + (i + 1)));
		}
		clavier.close();
	}
	
	@Override
	public void removeCards(Card carte, List<Card> listeDeCarte) throws EmptyListOfCardsException{
		listeDeCarte
				.removeIf(car -> car.getNom().equals(carte.getNom()) && car.getNumCarte().equals(carte.getNumCarte()));
	}
	@Override
	public void distribute(List<Card> listeDeCarte, List<Player> listeJoueur) throws EmptyListOfCardsException {
		int nbrePartMain = listeDeCarte.size() / listeJoueur.size();
		for (Player  joueur : listeJoueur) {
			for (int i = 0; i < nbrePartMain; i++) {
				joueur.getMain().add(listeDeCarte.getLast());
				removeCards(joueur.getMain().getLast(), listeDeCarte);
			}
		}
		afficherCarteChacun(listeJoueur);
	}

	public void afficherCarteChacun(List<Player> listeJoueur) {
		for (Player joueur : listeJoueur) {
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~Main du " + joueur.getNomJoueur() + "~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int i = 1;
			for (Card carte : joueur.getMain()) {
				System.out.println(i + "- " + carte);
				i++;
			}
			System.out.println("\n");
		}
	}

	@Override
	public void playOneCard(Player joueur, List<Card> listeCarteJouer, Card currentCard) {
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(joueur.getMain().size());

		NomCarte[] values = NomCarte.values();
		int index = rand.nextInt(values.length);
		NomCarte avis = values[index];

		listeCarteJouer.add(joueur.getMain().get(nombreAleatoire));
		currentCard.setNom(joueur.getMain().get(nombreAleatoire).getNom());
		currentCard.setNumCarte(joueur.getMain().get(nombreAleatoire).getNumCarte());
		joueur.getMain().remove(nombreAleatoire);

		System.out.println("=> Le " + joueur.getNomJoueur() + " dit " + avis + "\n");
		System.out.println("La carte qui a ete jouer est la carte => " + currentCard.toString() + "\n");
		int i = 1;
		System.out.println("La main du " + joueur.getNomJoueur() + " est desormais \n");
		for (Card carte : joueur.getMain()) {
			System.out.println(i + "- " + carte);
			i++;
		}
	}

	public void contradict(Player jou, List<Player> listeJoueur, Card carteCourante, NomCarte avis,
			List<Card> listeCarteJouer) throws EmptyListOfCardsException {
		// Créer une liste de joueurs excluant le joueur actuel
		;List<Player> listeAutresJoueurs = new ArrayList<>(listeJoueur);
		listeAutresJoueurs.remove(jou);

		// Vérifier qu'il reste bien des joueurs après la suppression
		if (listeAutresJoueurs.isEmpty()) {
			System.out.println("Il n'y a pas d'autres joueurs disponibles pour refuter.");
			return;
		}

		// Choisir un joueur au hasard parmi les autres joueurs
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(listeAutresJoueurs.size());

		if (carteCourante.getNom().equals(avis)) {
			listeAutresJoueurs.get(nombreAleatoire).getMain().addAll(listeCarteJouer);
		} else {
			jou.getMain().addAll(listeCarteJouer);
		}

		// Vider la liste des cartes jouées
		listeCarteJouer.clear();
	}
	
	@Override
	public void play() throws EmptyListOfCardsException {
		List<Card> listeDeCarte = new ArrayList<Card>();
		List<Player> listeJoueur = new ArrayList<Player>();
		Card carteCourante = new Card(null, null);
		List<Card> listeCarteJouer = new ArrayList<Card>();

		createCards(listeDeCarte);
		showCards(listeDeCarte);

		mixCards(listeDeCarte);
		createPlayer(listeJoueur);
		
		distribute(listeDeCarte, listeJoueur);

		boolean jeuEnCours = true;
		while (jeuEnCours) {
			for (Player joueur : listeJoueur) {

				playOneCard(joueur, listeCarteJouer, carteCourante);

				NomCarte avis = NomCarte.values()[new Random().nextInt(NomCarte.values().length)];
				contradict(joueur, listeJoueur, carteCourante, avis, listeCarteJouer);

				if (joueur.getMain().isEmpty()) {
					System.out.println("Le gagnant est " + joueur.getNomJoueur() + " !");
					jeuEnCours = false;
					return;
				}
			}
		}
	}

	

}
