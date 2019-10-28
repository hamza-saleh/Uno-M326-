/**
Von der Klasse Deck werden die Karten gezogen und abgelegt.

	@author:   Sven Hinder
	@since:    05.07.2019
	@version:  V1.0
*/
public class Deck {
	private Cardstack deckCards, playedCards;
	
	/** Erstellt das Deck.
	 * @param d ist ein Kartenstapel der verdeckten Karten.
	 * @param p ist ein Kartenstapel der gespielten karten.
	 */
	public Deck(Cardstack d, Cardstack p) {
		deckCards = d;
		playedCards = p;
		populateDeck();
	}
	
	/** Das Deck gibt eine Karte an den Spieler.
	 * @return Die Karte.
	 */
	public Card givesCard() {
		if (getDeckSize() == 0) {
			refillDeck();
		}
		return deckCards.removeCard(getRandomNumber());
	}
	
	/** Der Stapel der gespielten Karten nimmt die Karte entgegen.
	 * @param c ist die gespielte Karte.
	 */
	public void takeCard(Card c) {
		if (getDeckSize() == 0) {
			refillDeck();
		}
		playedCards.addCard(c);
	}
	
	/** Gibt die oberste Karte der gespielten Karten zurück.
	 * @return die oberste Karte der gespielten Karten.
	 */
	public Card getTopCard() {
		return playedCards.getCard(playedCards.getSize() - 1);
	}
	
	/** Der Kartenstapel wird zurückgesetzt.
	 * 
	 */
	public void newRound() {
		deckCards = new Cardstack();
		playedCards = new Cardstack();
		populateDeck();
	}
	
	/** Gibt zurück wieviel verdeckte Karten es noch hat.
	 * @return wieviel verdeckte Karten es noch hat.
	 */
	public int getDeckSize() {
		return deckCards.getSize();
	}
	
	/** Die gespielten Karten werden zu verdeckten Karten.
	 * 
	 */
	private void refillDeck() {
		System.out.print("refill ---------------------");
	    deckCards = playedCards;
	    playedCards = new Cardstack();
	    takeCard(deckCards.removeCard(deckCards.getSize() - 1));
	}
	
	/** Füllt die leeren Kartenstapel.
	 * 
	 */
	private void populateDeck() {
		for (Integer y=0; y < 2; y++) {
			for (Integer i=0; i < 10; i++) {
				deckCards.addCard(new Card("blue", i.toString()));
				deckCards.addCard(new Card("green", i.toString()));
				deckCards.addCard(new Card("red", i.toString()));
				deckCards.addCard(new Card("yellow", i.toString()));
			}
		}
		takeCard(deckCards.removeCard(getRandomNumber()));
	}
	
	/** gibt eine zufällig Zahl zurück.
	 * @return Zahl zwischen 0-Deckgrösse.
	 */
	private int getRandomNumber() {
        return (int)(Math.random() * ((deckCards.getSize() - 1) + 1)) + 0;
    }
	
}
