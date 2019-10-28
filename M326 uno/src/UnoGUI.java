import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
/**
Die Klasse UnoGUI ist das Spiel-GUI

	@author:   Sven Hinder
	@since:    05.07.2019
	@version:  V1.0
*/
public class UnoGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UnoControll controller;
	Deck d;
	//North
	Player playing, waiting1, waiting2, waiting3;
	JPanel waitingPlayers, waitingStatistics1, waitingStatistics2, waitingStatistics3;
	//Center
	JPanel boardCommentator, boardPanel, playedCardsDeck, playedCards, deck, playingStatistics, commentatorPanel;
	JTextArea commentator;
	String comments;
	JLabel deckImg;
	JScrollPane jsp1;
	boolean deckVisible;
	JButton uno;
	//South
	JPanel hand;
	JScrollPane jsp2;
	
	/** Erstellt das GUI
	 * @param c der Controller
	 * @param d das Deck
	 */
	public UnoGUI(UnoControll c, Deck d){
		controller = c;
		this.d = d;
		controller.addGUI(this);
		comments = "";
		deckVisible = true;
		playingChange();
		init();
	}
	
	/** Setzt den Kommentar
	 * @param comment ist der Kommentar
	 */
	public void setComment(String comment) {
		comments += "\n" + comment;
	}
	
	/** Ändert wer am spielen oder am warten ist.
	 * 
	 */
	private void playingChange() {
		for (int i=0; i < 4; i++) {
			if (controller.getPlayer(i).getIsWaiting() == false) {
				playing = controller.getPlayer(i);
			}
		}
		waiting1 = playing.getNextPlayer();
		waiting2 = waiting1.getNextPlayer();
		waiting3 = waiting2.getNextPlayer();
	}
	
	/** Erstellt die GUI-Komponenten.
	 * 
	 */
	private void init() {
		setTitle("UNO");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    UIManager.put("Label.font", new FontUIResource("Segoe Print", Font.PLAIN, 20));
	    UIManager.put("Label.foreground", Color.white);
	    UIManager.put("Panel.background", new Color(49, 49, 49));
	    UIManager.put("TextArea.font", new FontUIResource("Segoe Print", Font.PLAIN, 14));
	    UIManager.put("Button.font", new FontUIResource("Segoe Print", Font.PLAIN, 20));
	    //North
	    waitingPlayers = new JPanel();
	    waitingPlayers.setLayout(new GridLayout(1, 3, 0, 0));
	    getContentPane().add(waitingPlayers, BorderLayout.NORTH);
	    
	    waitingStatistics1 = new JPanel();
	    waitingStatistics1.setLayout(new GridLayout(3, 1, 0, 15));
	    waitingStatistics1.add(new JLabel(waiting1.getName()));
	    waitingStatistics1.add(new JLabel("Puntestand: " + waiting1.getPoints()));
	    waitingStatistics1.add(new JLabel("Karten in der Hand: " + waiting1.getCntCards()));
	    waitingPlayers.add(waitingStatistics1);
	    
	    waitingStatistics2 = new JPanel();
	    waitingStatistics2.setLayout(new GridLayout(3, 1, 0, 15));
	    waitingStatistics2.add(new JLabel(waiting2.getName()));
	    waitingStatistics2.add(new JLabel("Puntestand: " + waiting2.getPoints()));
	    waitingStatistics2.add(new JLabel("Karten in der Hand: " + waiting2.getCntCards()));
	    waitingPlayers.add(waitingStatistics2);
	    
	    waitingStatistics3 = new JPanel();
	    waitingStatistics3.setLayout(new GridLayout(3, 1, 0, 15));
	    waitingStatistics3.add(new JLabel(waiting3.getName()));
	    waitingStatistics3.add(new JLabel("Puntestand: " + waiting3.getPoints()));
	    waitingStatistics3.add(new JLabel("Karten in der Hand: " + waiting3.getCntCards()));
	    waitingPlayers.add(waitingStatistics3);
	    
	    //CenterLeft
	    boardCommentator = new JPanel();
	    boardCommentator.setLayout(new GridLayout(1, 2));
	    getContentPane().add(boardCommentator, BorderLayout.CENTER);
	    
	    boardPanel = new JPanel();
	    boardPanel.setLayout(new BorderLayout());
	    boardCommentator.add(boardPanel);
	    
	    playedCardsDeck = new JPanel();
	    playedCardsDeck.setLayout(new FlowLayout(0, 150, 275)); 
	    boardPanel.add(playedCardsDeck, BorderLayout.CENTER);
	    
	    uno = new JButton("UNO");
	    playedCardsDeck.add(uno);
	    uno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (playing.getCntCards() == 2) {
					playing.notifyUNO();
					commentator.setText(comments + "\n" + playing.getName() + " sagt UNO!");
					comments = commentator.getText();
					uno.setEnabled(false);
				} else {
					uno.setEnabled(false);
				}
			}
	    });
	    
	    playedCards = new JPanel();
	    playedCards.add(new JLabel(new ImageIcon("res/" + controller.getDeck().getTopCard().getCardPath())));
	    playedCardsDeck.add(playedCards);
	    
	    if (d.getDeckSize() != 0) {
	    deckImg = new JLabel(new ImageIcon("res/uno_card-back.png"));
	    deckImg.addMouseListener(new DrawCardListener());
	    deck = new JPanel();
	    deck.add(deckImg);
	    playedCardsDeck.add(deck);
	    }
	    playingStatistics = new JPanel();
	    playingStatistics.setLayout(new GridLayout(3, 1, 0, 15));
	    playingStatistics.add(new JLabel(playing.getName()));
	    playingStatistics.add(new JLabel("Puntestand: " + playing.getPoints()));
	    playingStatistics.add(new JLabel("Karten in der Hand: " + playing.getCntCards()));
	    boardPanel.add(playingStatistics, BorderLayout.SOUTH);
	    
	    //CenterRight
	    
	    commentator = new JTextArea(comments, 28, 1);
	    commentator.setEditable(false);
	    commentator.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	    commentatorPanel = new JPanel();
	    commentatorPanel.setLayout(new BorderLayout());
	    commentatorPanel.add(commentator);
	    
	    jsp1 = new JScrollPane(commentatorPanel);
	    jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    //jsp1.getVerticalScrollBar().setValue(commentatorPanel.getHeight());
	    boardCommentator.add(jsp1);
	    
	    //South
	    hand = new JPanel(new FlowLayout(0, 10, 0));
	    jsp2 = new JScrollPane(hand);
	    jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); 
	    getContentPane().add(jsp2, BorderLayout.SOUTH);
	    
	    for (int i=0; i < playing.getCntCards(); i++) {
	    	JLabel cardLabel = new JLabel(new ImageIcon("res/" + playing.getCard(i).getCardPath()));
	    	cardLabel.addMouseListener(new PlayCardListener(i));
	    	hand.add(cardLabel);
	    }
	    for (int i=0; i < 30 - playing.getCntCards(); i++) {
	    	JLabel cardLabel = new JLabel("               ");
	    	hand.add(cardLabel);
	    }
	    setVisible(true);
	}
	
	/** Der Listener für die Karten in der Hand
	 * 
	 */
	class PlayCardListener extends MouseAdapter{
		int index;
		
		public PlayCardListener(int i) {
			index = i;
		}
		
		public void mouseClicked(MouseEvent e) {
			playing.playCard(index);
			uno.setEnabled(true);
			playingChange();
			//jsp1.getVerticalScrollBar().setValue(commentatorPanel.getHeight());
			init();
	    }
	}
	
	/** Der Listener für das Deck
	 * 
	 */
	class DrawCardListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			playing.drawCard(1);
			commentator.setText(comments + "\n" + playing.getName() + " zieht 1 Karte");
			comments = commentator.getText();
			playing.setIsWaiting(true);
			waiting1.setIsWaiting(false);
			playingChange();
			//jsp1.getVerticalScrollBar().setValue(commentatorPanel.getHeight());
			init();
		}
	}
}
