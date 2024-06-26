package carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import exceptions.EmptyListOfCardsException;

public class JeuMensonge  implements JeuCarte{

	private static final String[] listeNumcarte = { "A", "7", "8", "9", "10", "J", "Q", "K" };
	
	public JeuMensonge() {
		super();
	}

	// creation du paquet de carte
	public void creationDesCarte(ArrayList<Carte> cardList) throws EmptyListOfCardsException {
		for (int j = 0; j < listeNumcarte.length; j++) {
			cardList.add(new Carte(NomCarte.Carreau, listeNumcarte[j]));
			cardList.add(new Carte(NomCarte.Trefle, listeNumcarte[j]));
			cardList.add(new Carte(NomCarte.Coeur, listeNumcarte[j]));
			cardList.add(new Carte(NomCarte.Pique, listeNumcarte[j]));
		}
	}

	// melage des carte
	public void melangerJeu(ArrayList<Carte> liste) throws EmptyListOfCardsException {
		Collections.shuffle(liste);
		System.out.println("-------------------------Melange des carte -----------------------\n");
		afficherPaquetCarte(liste);
	}

	public void afficherPaquetCarte(ArrayList<Carte> liste) throws EmptyListOfCardsException{
		int i = 1;
		for (Carte carte : liste) {
			System.out.println(i + "- " + carte.toString() + "\n");
			i++;
		}
	}

	public void CreationJoueurs(ArrayList<Joueur> listeJoueur) throws EmptyListOfCardsException{
		Scanner clavier = new Scanner(System.in);
		System.out.println("Entre le nombre de joueurs => ");
		int nbreJoueur = clavier.nextInt();

		for (int i = 0; i < nbreJoueur; i++) {
			listeJoueur.add(new Joueur("Joueur_" + (i + 1)));
		}
		clavier.close();
	}

	public void removeCard(Carte carte, ArrayList<Carte> listeCarteJouer) throws EmptyListOfCardsException{
		listeCarteJouer
				.removeIf(car -> car.getNom().equals(carte.getNom()) && car.getNumCarte().equals(carte.getNumCarte()));
	}

	public void distributionCarte(ArrayList<Carte> listeDeCarte, ArrayList<Joueur> listeJoueur) throws EmptyListOfCardsException {
		int nbrePartMain = listeDeCarte.size() / listeJoueur.size();
		for (Joueur joueur : listeJoueur) {
			for (int i = 0; i < nbrePartMain; i++) {
				joueur.getMain().add(listeDeCarte.getLast());
				removeCard(joueur.getMain().getLast(), listeDeCarte);
			}
		}
		afficherCarteChacun(listeJoueur);
	}

	public void afficherCarteChacun(ArrayList<Joueur> listeJoueur) {
		for (Joueur joueur : listeJoueur) {
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~Main du " + joueur.getNomJoueur() + "~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int i = 1;
			for (Carte carte : joueur.getMain()) {
				System.out.println(i + "- " + carte);
				i++;
			}
			System.out.println("\n");
		}
	}

	public void jouerUneCarte(Joueur joueur, ArrayList<Carte> listeCarteJouer, Carte carteCourante) {
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(joueur.getMain().size());

		NomCarte[] values = NomCarte.values();
		int index = rand.nextInt(values.length);
		NomCarte avis = values[index];

		listeCarteJouer.add(joueur.getMain().get(nombreAleatoire));
		carteCourante.setNom(joueur.getMain().get(nombreAleatoire).getNom());
		carteCourante.setNumCarte(joueur.getMain().get(nombreAleatoire).getNumCarte());
		joueur.getMain().remove(nombreAleatoire);

		System.out.println("=> Le " + joueur.getNomJoueur() + " dit " + avis + "\n");
		System.out.println("La carte qui a ete jouer est la carte => " + carteCourante.toString() + "\n");
		int i = 1;
		System.out.println("La main du " + joueur.getNomJoueur() + " est desormais \n");
		for (Carte carte : joueur.getMain()) {
			System.out.println(i + "- " + carte);
			i++;
		}
	}

	public void refuter(Joueur jou, ArrayList<Joueur> listeJoueur, Carte carteCourante, NomCarte avis,
			ArrayList<Carte> listeCarteJouer) throws EmptyListOfCardsException {
		// Créer une liste de joueurs excluant le joueur actuel
		ArrayList<Joueur> listeAutresJoueurs = new ArrayList<>(listeJoueur);
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
	public void jouer() throws EmptyListOfCardsException {
		ArrayList<Carte> listeDeCarte = new ArrayList<Carte>();
		ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
		Carte carteCourante = new Carte(null, null);
		ArrayList<Carte> listeCarteJouer = new ArrayList<Carte>();

		creationDesCarte(listeDeCarte);
		afficherPaquetCarte(listeDeCarte);

		melangerJeu(listeDeCarte);
		CreationJoueurs(listeJoueur);
		distributionCarte(listeDeCarte, listeJoueur);

		boolean jeuEnCours = true;
		while (jeuEnCours) {
			for (Joueur joueur : listeJoueur) {

				jouerUneCarte(joueur, listeCarteJouer, carteCourante);

				NomCarte avis = NomCarte.values()[new Random().nextInt(NomCarte.values().length)];
				refuter(joueur, listeJoueur, carteCourante, avis, listeCarteJouer);

				if (joueur.getMain().isEmpty()) {
					System.out.println("Le gagnant est " + joueur.getNomJoueur() + " !");
					jeuEnCours = false;
					return;
				}
			}
		}
	}

	

}
