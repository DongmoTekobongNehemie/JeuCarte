package carte;

import java.util.List;

import exceptions.EmptyListOfCardsException;

public interface JeuCarte {

	public void play() throws EmptyListOfCardsException;
	
	public void createCards(List<Card> cardsList) throws EmptyListOfCardsException;
	
	public void mixCards(List<Card> cardsList) throws EmptyListOfCardsException;
	
	public void removeCards(Card card, List<Card> cardList) throws EmptyListOfCardsException;
	
	public void distribute(List<Card> cardList, List<Player> playersLists) throws EmptyListOfCardsException;
	
	public void playOneCard(Player player, List<Card> cards, Card currentCard);
	
	public void createPlayer(List<Player> playerList) throws EmptyListOfCardsException;
	
	
	
	
}
