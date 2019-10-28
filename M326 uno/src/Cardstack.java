import java.util.Vector;

/**
Die Klasse Cardstack ist der Kartenstapel die alle Spieler haben und im Deck vorhanden sind.

	@author:   Sven Hinder
	@since:    05.07.2019
	@version:  V1.0
*/
public class Cardstack {
	private Vector<Card> stack;
	
	
	/** Erstellt einen neuen Kartenstapel
	 * 
	 */
	public Cardstack() {
		stack = new Vector<Card>();
	}
	
	/** Eine Karte wird dem Kartenstapel hizugefügt.
	 * @param c wird zu Kartenstapel hizugefügt.
	 */
	public void addCard(Card c) {
		stack.addElement(c);
	}
	
	/** Eine Karte wird dem Kartenstapel entfernt.
	 * @param index gibt an welche Karte vom Kartenstapel entfernt werden soll.
	 * @return die Karte wird vom Kartenstapel entfernt und weiter gegeben.
	 */
	public Card removeCard(int index) {
		Card c = stack.get(index);
		stack.remove(index);
		return c;
	}
	
	/** Gibt die Grösse des Kartenstapels zurück.
	 * @return gibt die grösse vom Kartenstapel zurück.
	 */
	public  int getSize() {
		return stack.size();
	}
	
	
	/** Gibt die Karte zurück.
	 * @param mit index wird angegeben welche Karte zurückgegeben wird.
	 * @return gibt die Karte zurück.
	 */
	public Card getCard(int index) {
		return stack.get(index);
	}
	
}

