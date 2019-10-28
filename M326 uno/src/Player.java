/**
   Die Klasse Player ist dazu da um Spieler zu erstellen und ihre Eigenschaften zu speichern.
   
	@author:   Sven Hinder
	@since:    05.07.2019
	@version:  V1.0
*/
public class Player {
	private String name;
	private int points, initCards;
	private boolean uno, isWaiting, roundWon;
	private Cardstack cStack;
	private Deck deck;
	private Player nextPlayer;
	private UnoControll controller;
	
	/** Der Spieler wird erstellt.
	 * @param n ist der Name des Spielers
	 * @param initCards ist die Anzahl Startkarten.
	 * @param c ein neuer Kartenstapel
	 * @param d Das deck des Spiels
	 * @param controller der Controller vom Programm
	 */
	public Player(String n, int initCards, Cardstack c, Deck d, UnoControll controller){
		name = n;
		points = 0;
		uno = false;
		isWaiting = true;
		roundWon = false;
		cStack = c;
		deck = d;
		this.initCards = initCards;
		this.controller = controller;
		drawCard(this.initCards);
		controller.addPlayer(this);
	}
	
	/** Setzt fest ob der Spielr wartet.
	 * @param b true/false
	 */
	public void setIsWaiting(boolean b) {
		isWaiting = b;
	}
	
	/** Setzt den Folgenden Spieler fest
	 * @param p ist der folgende Spieler.
	 */
	public void setNextPlayer(Player p) {
		nextPlayer = p;
	}
	
	/** Der Spieler ruft UNO! aus.
	 * 
	 */
	public void notifyUNO() {
			uno = true;
	}
	
	/** Der Spieler zieht Karte(n).
	 * @param cntCards ist die Anzahl Karten die der Spieler ziehen muss.
	 */
	public void drawCard(int cntCards) {
		for (int i=0; i < cntCards; i++) {
		cStack.addCard(deck.givesCard());
		}
	}
	
	/** Gibt die Anzahl Karten des Spielers zurück.
	 * @return die Anzahl Karten in der Hand.
	 */
	public int getCntCards() {
		return cStack.getSize();
	}
	
	/** Gibt den Namen zurück.
	 * @return der Name
	 */
	public String getName() {
		return name;
	}
	
	/** Gibt zurück ob der Spieler diese Runde gewonnen hat.
	 * @return true/false
	 */
	public boolean getIfRoundWon() {
		return roundWon;
	}
	
	/** Setzt fest ob der Spieler wartet.
	 * @return true/false
	 */
	public boolean getIsWaiting() {
		return isWaiting;
	}
	
	/** Gibt den folgenden Spieler zurück.
	 * @return der folgende Spieler
	 */
	public Player getNextPlayer() {
		return nextPlayer;
	}
	
	/** Gibt die Punkte des Spielers zurück.
	 * @return Anzahl Punkte des Spielers
	 */
	public int getPoints() {
		return points;
	}
	
	/** Gibt die Gewählte Karte des Spielers zurück.
	 * @param index gibt an welche Karte zurückgegeben wird.
	 * @return die Karte
	 */
	public Card getCard(int index){
		return cStack.getCard(index);
	}
	
	/** Der Spieler spielt die Karte
	 * @param index gibt an welche Karte gespielt wird.
	 */
	public void playCard(int index) {
		if (cStack.getSize() == 2 && uno == false) {
			System.out.println("no uno");
			drawCard(1);
			controller.makeComment(name + " zieht 1 Karten als Strafe weil er nicht UNO! gesagt hat.");
		}
		Card c = cStack.getCard(index);
		if (brakesRule(c) == false){
			cStack.removeCard(index);
			deck.takeCard(c);
			controller.makeComment(name + " spielt : " + c.getCardName());
			if (cStack.getSize() == 0) {
				roundWon = true;
				uno = false;
				points += collectPoints(0);
				controller.makeComment(name + " hat die Runde gewonnen und hat jetzt " + points + " Punkte.");
				if (gameWon() == true) {
					controller.makeComment(name + " hat das Spiel gewonnen!");
					System.out.println(name + " hat das Spiel gewonnen!");
					System.exit(0);
				} else {
					deck.newRound();
					newRound();
					nextPlayer.newRound();
					nextPlayer.getNextPlayer().newRound();
					nextPlayer.getNextPlayer().getNextPlayer().newRound();
				}
			}
			
		} else {
			controller.makeComment(name + " du Idiot! Diese Karte ist nicht spielbar");
		}
		this.setIsWaiting(true);
		nextPlayer.setIsWaiting(false);
	}
	
	/** Der Spieler zählt die Punkte zusammen die er gewonnen hat.
	 * @param p ist die Anzahl punkte
	 * @return die Anzahl punkte
	 */
	public int collectPoints(int p) {
		if (nextPlayer.getIfRoundWon() == false) {
			for (int i=0; i < cStack.getSize(); i++) {
				p += Integer.parseInt(cStack.getCard(i).getAttribute());
			}
			return nextPlayer.collectPoints(p);
		} else {
			for (int i=0; i < cStack.getSize(); i++) {
				p += Integer.parseInt(cStack.getCard(i).getAttribute());
			}
			return  p + 500;
		}
	}
	
	/** Der Spieler gibt die Karten zurück um ein neues Spielt zu starten.
	 * 
	 */
	public void newRound() {
		cStack = new Cardstack();
		drawCard(this.initCards);
	}
	
	/** Der Spieler überprüft ob er eine Regel gebrochen hat.
	 * @param c die gespielte Karte
	 * @return true/false
	 */
	private boolean brakesRule(Card c) {
		boolean ruleBroken;
		if (c.getAttribute().equals(deck.getTopCard().getAttribute())) {
			ruleBroken = false;
		}
		else if (c.getColor().equals(deck.getTopCard().getColor())) {
			ruleBroken = false;
		}
		else {
			ruleBroken = true;
		}
		return ruleBroken;
	}
	
	/** Übersprüft ob ein Spieler das Spiel gewonnen hat.
	 * @return true/false
	 */
	private boolean gameWon() {
		boolean won = false;
		if (points >= 500) {
			won = true;
		}
		return won;
	}
}
