package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import carte.Card;
import carte.JeuMensonge;
import carte.Player;
import carte.NomCarte;
import exceptions.EmptyListOfCardsException;

class JeuTest {
	private JeuMensonge jeu;
	private List<Card> listeDeCarte;
	private List<Player> listeJoueur;
    private static final String[] listeNumcarte = { "A", "7", "8", "9", "10", "J", "Q", "K" };
    private List<Card> listeCarteJouer;                                                                                                                                                                                             ;
    private Card carteCourante;

	@BeforeEach
	void setUp() throws EmptyListOfCardsException {
		jeu = new JeuMensonge();
		listeDeCarte = new ArrayList<Card>();
		listeJoueur = new ArrayList<Player>();
		jeu.createCards(listeDeCarte);
		Player joueur1 = new Player("Joueur_1");
		Player joueur2 = new Player("Joueur_2");
		listeJoueur.add(joueur1);
		listeJoueur.add(joueur2);
		listeCarteJouer = new ArrayList<Card>();
		carteCourante = new Card(null, null);
	}

	// Les methodes pour tester la methode melange de carte
	@Test
	@DisplayName("")
	void listeVide() {
		listeDeCarte = null;
		assertThrows(NullPointerException.class, () -> {
			jeu.mixCards(listeDeCarte);
        });
	}
	
	@Test
	void verifierShuffle() throws EmptyListOfCardsException {
		NomCarte premier = listeDeCarte.getFirst().getNom();
		jeu.mixCards(listeDeCarte);
		assertFalse(listeDeCarte.getFirst().getNom().equals(premier));
	} 
	
	//verification de la distribution des cartes 
	@Test
	void listeCarteVide() {
		listeDeCarte = null;
		assertThrows(NullPointerException.class, ()->{
			jeu.distribute(listeDeCarte, listeJoueur);
		});
	}
	
	@Test
	void listeJoueurvide() {
		listeJoueur = null; 
		assertThrows(NullPointerException.class, ()->{
			jeu.distribute(listeDeCarte, listeJoueur);
		});
	}
	
	@Test
	void verifierDistribution() {
		assertEquals(listeJoueur.get(0).getMain().size(),listeJoueur.get(1).getMain().size(),"chaque joueur doit avoir le meme nombre de carte");
	}
	
	// verification de la methode removeCard
	@Test
	void listeCarteNull() {
		Card carte = null; 
		assertThrows(NullPointerException.class, ()->{
			jeu.removeCards(carte, listeDeCarte);
		});
	}
	
	@Test
	void listeCartesVide() {
		listeDeCarte= null;
		Card carte = new Card(NomCarte.Coeur, listeNumcarte[0]);
		assertThrows(NullPointerException.class, ()->{
			jeu.removeCards(carte, listeDeCarte);
		});
	}
	
	@Test
	void conditionRespecter() throws EmptyListOfCardsException {
		Card car = listeDeCarte.get(0);
		jeu.removeCards(car, listeDeCarte);
		assertTrue(!listeDeCarte.contains(car));
	}
	 
	@Test
	void conditionNonRespecter() throws EmptyListOfCardsException {
		Card car = new Card(NomCarte.Coeur, "0");
		jeu.removeCards(car, listeDeCarte);
		assertEquals(32, listeDeCarte.size(),"la lsite doit avoir ne doit pas changer");
	}
	
	//  verification de la methode de jeu d'une carte
	@Test
	void joueurNull() {
		Player j = null;
		assertThrows(NullPointerException.class, ()->{
			jeu.playOneCard(j, listeCarteJouer, carteCourante);
		});
	}
	
	void listeCarteJouerNull() {
		listeCarteJouer = null;
		assertThrows(NullPointerException.class, ()->{
			jeu.playOneCard(listeJoueur.get(0), listeCarteJouer, carteCourante);
		});
	}
	
	@Test
	void verifierJouerUneCarte() throws EmptyListOfCardsException {
		jeu.distribute(listeDeCarte, listeJoueur);
		jeu.playOneCard(listeJoueur.get(1), listeCarteJouer, carteCourante);
		assertEquals(listeCarteJouer.getLast().getNom(), carteCourante.getNom());
	}
	
	// verification d ela methode refuter
    @Test
    void refuterJoueurNull() {
    	
    	Player j = null;
    	assertThrows(NullPointerException.class, ()-> { 
    		jeu.contradict(j, listeJoueur, carteCourante, NomCarte.Coeur, listeCarteJouer);
    	});
    }

    @Test
    void refuterListeJoueurVide() {
    	listeJoueur= null;
    	assertThrows(NullPointerException.class, ()-> { 
    		jeu.contradict(new Player("Jou1") , listeJoueur, carteCourante, NomCarte.Coeur, listeCarteJouer);
    	});
    }
    
    
    @Test
    void refuterAvisVide() {
    	NomCarte avis = null;
    	assertThrows(NullPointerException.class, ()-> { 
    		jeu.contradict(new Player("jou") , listeJoueur, carteCourante, avis, listeCarteJouer);
    	});
    }
    
    
}
