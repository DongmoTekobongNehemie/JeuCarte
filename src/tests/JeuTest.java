package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import carte.Carte;
import carte.JeuMensonge;
import carte.Joueur;
import carte.NomCarte;
import exceptions.EmptyListOfCardsException;

class JeuTest {
	private JeuMensonge jeu;
	private ArrayList<Carte> listeDeCarte;
	private ArrayList<Joueur> listeJoueur;
    private static final String[] listeNumcarte = { "A", "7", "8", "9", "10", "J", "Q", "K" };
    private ArrayList<Carte> listeCarteJouer;
    private Carte carteCourante;

	@BeforeEach
	void setUp() throws EmptyListOfCardsException {
		jeu = new JeuMensonge();
		listeDeCarte = new ArrayList<Carte>();
		listeJoueur = new ArrayList<Joueur>();
		jeu.creationDesCarte(listeDeCarte);
		Joueur joueur1 = new Joueur("Joueur_1");
		Joueur joueur2 = new Joueur("Joueur_2");
		listeJoueur.add(joueur1);
		listeJoueur.add(joueur2);
		listeCarteJouer = new ArrayList<Carte>();
		carteCourante = new Carte(null, null);
	}

	// Les methodes pour tester la methode melange de carte
	@Test
	@DisplayName("")
	void listeVide() {
		listeDeCarte = null;
		assertThrows(NullPointerException.class, () -> {
			jeu.melangerJeu(listeDeCarte);
        });
	}
	
	@Test
	void verifierShuffle() throws EmptyListOfCardsException {
		NomCarte premier = listeDeCarte.get(0).getNom();
		jeu.melangerJeu(listeDeCarte);
		assertNotEquals(premier, listeDeCarte.get(0).getNom());
	}
	
	//verification de la distribution des cartes 
	@Test
	void listeCarteVide() {
		listeDeCarte = null;
		assertThrows(NullPointerException.class, ()->{
			jeu.distributionCarte(listeDeCarte, listeJoueur);
		});
	}
	
	@Test
	void listeJoueurvide() {
		listeJoueur = null; 
		assertThrows(NullPointerException.class, ()->{
			jeu.distributionCarte(listeDeCarte, listeJoueur);
		});
	}
	
	@Test
	void verifierDistribution() {
		assertEquals(listeJoueur.get(0).getMain().size(),listeJoueur.get(1).getMain().size(),"chaque joueur doit avoir le meme nombre de carte");
	}
	
	// verification de la methode removeCard
	@Test
	void listeCarteNull() {
		Carte carte = null; 
		assertThrows(NullPointerException.class, ()->{
			jeu.removeCard(carte, listeDeCarte);
		});
	}
	
	@Test
	void listeCartesVide() {
		listeDeCarte= null;
		Carte carte = new Carte(NomCarte.Coeur, listeNumcarte[0]);
		assertThrows(NullPointerException.class, ()->{
			jeu.removeCard(carte, listeDeCarte);
		});
	}
	
	@Test
	void conditionRespecter() throws EmptyListOfCardsException {
		Carte car = listeDeCarte.get(0);
		jeu.removeCard(car, listeDeCarte);
		assertTrue(!listeDeCarte.contains(car));
	}
	 
	@Test
	void conditionNonRespecter() throws EmptyListOfCardsException {
		Carte car = new Carte(NomCarte.Coeur, "0");
		jeu.removeCard(car, listeDeCarte);
		assertEquals(32, listeDeCarte.size(),"la lsite doit avoir ne doit pas changer");
	}
	
	//  verification de la methode de jeu d'une carte
	@Test
	void joueurNull() {
		Joueur j = null;
		assertThrows(NullPointerException.class, ()->{
			jeu.jouerUneCarte(j, listeCarteJouer, carteCourante);
		});
	}
	
	void listeCarteJouerNull() {
		listeCarteJouer = null;
		assertThrows(NullPointerException.class, ()->{
			jeu.jouerUneCarte(listeJoueur.get(0), listeCarteJouer, carteCourante);
		});
	}
	
	@Test
	void verifierJouerUneCarte() throws EmptyListOfCardsException {
		jeu.distributionCarte(listeDeCarte, listeJoueur);
		jeu.jouerUneCarte(listeJoueur.get(1), listeCarteJouer, carteCourante);
		assertEquals(listeCarteJouer.getLast().getNom(), carteCourante.getNom());
	}
	
	// verification d ela methode refuter
    @Test
    void refuterJoueurNull() {
    	
    	Joueur j = null;
    	assertThrows(NullPointerException.class, ()-> { 
    		jeu.refuter(j, listeJoueur, carteCourante, NomCarte.Coeur, listeCarteJouer);
    	});
    }

    @Test
    void refuterListeJoueurVide() {
    	listeJoueur= null;
    	assertThrows(NullPointerException.class, ()-> { 
    		jeu.refuter(new Joueur("Jou1") , listeJoueur, carteCourante, NomCarte.Coeur, listeCarteJouer);
    	});
    }
    
    
    @Test
    void refuterAvisVide() {
    	NomCarte avis = null;
    	assertThrows(NullPointerException.class, ()-> { 
    		jeu.refuter(new Joueur("jou") , listeJoueur, carteCourante, avis, listeCarteJouer);
    	});
    }
    
    
}
