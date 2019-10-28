import java.util.Vector;
/**
Die Klasse UnoControll ist der Controller im MVC.

	@author:   Sven Hinder
	@since:    05.07.2019
	@version:  V1.0
*/
public class UnoControll {
	private Vector<Player> allPlayer;
	private Deck deck;
	private UnoGUI unoGUI;
	
	/** Erstellt einen Controller.
	 * @param d ist das Deck.
	 */
	public UnoControll(Deck d) {
		allPlayer = new Vector<Player>();
		deck = d;
	}
	
	/** Ein Spieler wird dem Controller hizugef�gt.
	 * @param p ist ein Spieler
	 */
	public void addPlayer(Player p) {
		allPlayer.addElement(p);
	}
	/** Das Spiel-GUI wird dem Controller hizugef�gt.
	 * @param u ist das Spiel-GUI
	 */
	public void addGUI(UnoGUI u) {
		unoGUI = u;
	}
	
	/** Der Controller gibt den Spieler zur�ck.
	 * @param index der Gew�hlte Spieler
	 * @return den Spieler
	 */
	public Player getPlayer(int index) {
		return allPlayer.get(index);
	}
	
	/** Gibt das Deck zur�ck.
	 * @return das Deck
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/** Ein Kommentar wird gemacht.
	 * @param c ist der Kommentar
	 */
	public void makeComment(String c) {
		unoGUI.setComment(c);
	}
	
}
