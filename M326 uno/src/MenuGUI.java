import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
/**
Ist ein GUI wo man die Namen der Spieler und die Anzahlt Startkarten eingeben kann und wird mit den Spielstart geschlossen.

	@author:   Dennis Küenzi
	@since:     05.07.2019
	@version:  V1.0
*/
public class MenuGUI extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JTextField inputName1;
  private JTextField inputName2;
  private JTextField inputName3;
  private JTextField inputName4;
  private JTextField inputNumber;
  private JLabel input1;
  private JLabel input2;
  private JLabel input3;
  private JLabel input4;
  private JLabel input5;
  private JButton start;
  private JButton exit;
  private JPanel startPanel;
  private Vector<String> allNames;
  static Deck d;
  static MenuGUI m;
  
  /** Erstellt das GUI
 *  
 */
MenuGUI() {
	 allNames = new Vector<String>();
    init();
    allNames.addElement(inputName1.getText());
    allNames.addElement(inputName2.getText());
    allNames.addElement(inputName3.getText());
    allNames.addElement(inputName4.getText());
  }

  /** Die GUI-Komponenten werden erstellt.
 * 
 */
private void init() {
    setTitle("Menü");
    setResizable(false);
    
    UIManager.put("Label.font", new FontUIResource("Segoe Print", Font.PLAIN, 17));
    UIManager.put("TextField.font", new FontUIResource("Segoe Print", Font.PLAIN, 17));
    UIManager.put("Button.font", new FontUIResource("Segoe Print", Font.PLAIN, 17));
    UIManager.put("Label.foreground", Color.white);
    UIManager.put("Panel.background", new Color(49, 49, 49));
    

    startPanel = new JPanel();
    startPanel.setLayout(new GridLayout(6, 2, 1, 0));

    input1 = new JLabel("Spieler 1:");
    startPanel.add(input1);
    inputName1 = new JTextField();
    startPanel.add(inputName1);

    input2 = new JLabel("Spieler 2:");
    startPanel.add(input2);
    inputName2 = new JTextField();
    startPanel.add(inputName2);

    input3 = new JLabel("Spieler 3:");
    startPanel.add(input3);
    inputName3 = new JTextField();
    startPanel.add(inputName3);

    input4 = new JLabel("Spieler 4:");
    startPanel.add(input4);
    inputName4 = new JTextField();
    startPanel.add(inputName4);

    input5 = new JLabel("Anzahl Startkarten:");
    startPanel.add(input5);
    inputNumber = new JTextField("7");
    startPanel.add(inputNumber);
    inputNumber.addKeyListener(new KeyAdapter() {
    	
    /* (non-Javadoc)
     * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {

        } else {
          inputNumber.setText("7");
        }

        try {
          if (Integer.parseInt(inputNumber.getText()) > 19) {
            inputNumber.setText("7");
          }
        } catch (NumberFormatException nfe) {

        }
      }
    });

    start = new JButton("Starten");
    startPanel.add(start);
    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  UnoControll controller = new UnoControll(d);
  		System.out.println(inputName1.getText() + inputName2.getText() + inputName3.getText());
  		Player p1 = new Player(inputName1.getText(), Integer.parseInt(inputNumber.getText()), new Cardstack(), d, controller);
  		Player p2 = new Player(inputName2.getText(), Integer.parseInt(inputNumber.getText()), new Cardstack(), d, controller);
  		Player p3 = new Player(inputName3.getText(), Integer.parseInt(inputNumber.getText()), new Cardstack(), d, controller);
  		Player p4 = new Player(inputName4.getText(), Integer.parseInt(inputNumber.getText()), new Cardstack(), d, controller);
  		
  		p1.setIsWaiting(false);
  		p1.setNextPlayer(p2);
  		p2.setNextPlayer(p3);
  		p3.setNextPlayer(p4);
  		p4.setNextPlayer(p1);
  		
  		new UnoGUI(controller, d);
        dispose();
      }
    });

    exit = new JButton("Beenden");
    startPanel.add(exit);
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });

    getContentPane().add(startPanel);

    setVisible(true);
  }
  
  /** Startet das Programm 
 * @param args keine Ahnung
 */
public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		d = new Deck(new Cardstack(), new Cardstack());
		m = new MenuGUI();
		m.setSize(400, 200);
		
		
	}
}