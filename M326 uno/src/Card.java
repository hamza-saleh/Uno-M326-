/**
   Die Klasse Card ist dazu da um Karten zu erstellen und ihre Eigenschaften zu speichern.
   
   	@author:   Sven Hinder
	@since:    05.07.2019
	@version:  V1.0
*/
public class Card {
	private String color, attribute;
	
	/** Erstellt eine Neue Karte.
	 * @param c ist die Farbe von der Karte und kann nur red, blue, green oder yellow sein.
	 * @param a ist der Wert der Karte. Erlaubt sind nur Zahlen von 0-9. (Eventuell sp�ter noch Spezialwerte)
	 */
	public Card(String c, String a) {
		color = c;
		attribute = a;
	}
	
	/** Gibt die Farbe zur�ck.
	 * @return gibt die Farbe der Karte zur�ck.
	 */
	public String getColor() {
		return color;
	}
	
	/** Gibt den Wert zur�ck.
	 * @return  den Wert der Karte zur�ck.
	 */
	public String getAttribute() {
		return attribute;
	}
	
	/** Gibt vollen Namen des Bildes zur�ck.
	 * @return gibt den vollen Namen des Bildes der Karte zur�ck.
	 */
	public String getCardPath() {
		return "uno_card-" + color + attribute + ".png";
	}
	
	/** Gibt den Namen zur�ck.
	 * @return gibt den Namen der Karte zur�ck.
	 */
	public String getCardName() {
		String farbe = "";
		switch (color) {
		case "red":
			farbe = "Rot";
			break;
		case "blue":
			farbe = "Blau";
			break;
		case "green":
			farbe = "Gr�n";
			break;
		case "yellow":
			farbe = "Gelb";
			break;
		}
		return farbe + " " + attribute;
	}
}
