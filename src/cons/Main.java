package cons;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cons.IO.*;

public class Main extends JFrame
{	
	// === Allgemeine Objekte
	public JPanel panel = new JPanel();
	private Thread loop;
	
	// === In-Game-Variablen ===
	private Player player;
	private boolean inBattle;
	private boolean running = false;
	private boolean isWalkingEnabled = false;
	private boolean startScreenDisplayed = true;
	int menuChoice = 0;
	private final int VK_SPACE = 32;
	// === Hauptmenü-Komponenten ===
	private final JButton buttonStart = new JButton("Start", new ImageIcon("src/imgs/menu/button_start.png"));
	private final JButton buttonHelp = new JButton("Help", new ImageIcon("src/imgs/menu/button_help.png"));
	private final JButton buttonCredits = new JButton("Credits", new ImageIcon("src/imgs/menu/button_credits.png"));
	private final JButton buttonWebsite = new JButton("Website", new ImageIcon("src/imgs/menu/button_website.png"));
	private final JLabel background = new JLabel();
	private final JLabel headline = new JLabel("ConsFighter");
	public final JPanel screen = new JPanel();

	// === Hilfe- und Credits-Komponenten ===
	private final JTextArea ctext = new JTextArea();
	private final JTextArea htext = new JTextArea();
	private final JButton backToMenu = new JButton("Back");
	boolean showLevel1 = true, showLevel2 = true, showLevel3 = true, showPersons = true, showGrid = false;
	
	double zoom = 1;

	private Attack activeAttack;
	
	public static void main(String args[]) {
		new Main();
	}
	
	public Main() {
		this.init();
	}
	
	/** Initialisierungsmethode */
	public void init()
	{
		// Menü-Theme spielen
		SoundPlayer.soundMenu.play();

		// Panel-Eigenschaften setzen
		panel.setBackground(new Color(225,225,225));
		panel.setLayout(null);
		panel.setBounds(250, 40, 800, 600);

		// Listener initialisieren
		
		MouseListener MOUSELISTENER = new MouseAdapter()
		{
			public void mousePressed(final MouseEvent e)
			{
				e.getComponent().requestFocusInWindow();
				if(inBattle){
					calculateSelectedAttack(getPlayer().fighter, Person.fighter, e);
				}
			}
			public void mouseEntered(final MouseEvent e)
			{
				e.getComponent().requestFocusInWindow();
			}
		};
		
		final KeyAdapter KEYADAPTER = new KeyAdapter()
		{
			public void keyPressed(final KeyEvent e)
			{
				if(isRunning())
					keyPressedAction(e);
			}

			public void keyReleased(final KeyEvent e)
			{
				if(isRunning())
					keyReleasedAction(e);
			}
		};
		
		// Listener hinzufügen
		panel.addMouseListener(MOUSELISTENER);
		screen.addMouseListener(MOUSELISTENER);
		panel.addKeyListener(KEYADAPTER);
		screen.addKeyListener(KEYADAPTER);

		final Container CONTENTPANE = getContentPane();
		CONTENTPANE.setLayout(null);
		CONTENTPANE.add(panel);

		background.setBounds(0, 0, 800, 600);

		// Komponentenecken einstellen
		headline.setBounds(260, 50, 340, 100);
		buttonStart.setBounds(200, 200, 400, 50);
		buttonHelp.setBounds(200, 300, 400, 50);
		buttonCredits.setBounds(200, 400, 400, 50);
		buttonWebsite.setBounds(200, 500, 400, 50);
		backToMenu.setBounds(200, 500, 400, 50);
		screen.setBounds(0, 0, 800, 600);

		
		// Komponenten hinzufügen
		panel.add(buttonStart);
		panel.add(buttonHelp);
		panel.add(buttonCredits);
		panel.add(buttonWebsite);
		panel.add(headline);
		panel.add(background);
	
	
		// Start-Button
		buttonStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent e)
			{ 
				buttonStart_ActionPerformed(e);
			}
		});

		// Hilfe-Button
		buttonHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent e)
			{ 
				buttonHelp_ActionPerformed(e);
			}
		});

		// Credits-Button
		buttonCredits.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent e)
			{ 
				buttonCredits_ActionPerformed(e);
			}
		});

		// Exit-Button
		buttonWebsite.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent e)
			{ 
				buttonWebsite_ActionPerformed(e);
			}
		});

		// BackToMenu-Button
		backToMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent e)
			{ 
				backToMenu_ActionPerformed(e);
			}
		});
		
		// Überschrift

		headline.setFont(new Font("Segoe UI", 0, 50));
		headline.setForeground(Color.white);

		// Credits-Anzeige
		
		ctext.setBounds(50, 50, 750, 50);
		ctext.setLineWrap(true);
		ctext.setWrapStyleWord(true);
		ctext.setOpaque(false);
		ctext.setAlignmentX(CENTER_ALIGNMENT);
		ctext.setText("Programmed by Lukas Tassanyi, Jonas Bürkner and Markus Lötzsch");

		// Hilfe-Anzeige
		
		htext.setBounds(50, 50, 750, 350);
		htext.setFont(new Font("Consolas", 0, 16));
		htext.setLineWrap(true);
		htext.setWrapStyleWord(true);
		htext.setOpaque(false);
		htext.setAlignmentX(CENTER_ALIGNMENT);
		htext.setText(
			"=== STEUERUNG ===\n\n" +
			"Pfeiltasten   ...    laufen und im Menü navigieren\n" +
			"Leertaste     ...    interagieren\n" +
			"Escape        ...    Menü\n\n\n" +
			"=== ALLGEMEINES ===\n\n" +
			"Version       ...    Alpha 4\n" +
			"Webseite      ...    psychohouse.bplaced.com"
		);
		
		setLocation(5, 5);
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


	public void paint(final Graphics g)
	{
		if(startScreenDisplayed) // im Startmenü?
		{
			// Hintergrundbild aktualisieren
			final ImageIcon backgroundIcon = new ImageIcon("src/imgs/menu/background.jpg");
			backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT)); // Skalieren auf passende Größe
			background.setIcon(backgroundIcon);
		}
		super.paint(g);
		
		if(isRunning())
			drawMap();
	}
	private void keyPressedAction(final KeyEvent e)
	{
		final int keyCode = e.getKeyCode();

		switch(keyCode)
		{
		case VK_UP: // Pfeiltaste nach oben gedrückt?
			upArrowKeyPressed();
			break;
		case VK_RIGHT: // Pfeiltaste nach rechts gedrückt?
			rightArrowKeyPressed();
			break;
		case VK_DOWN: // Pfeiltaste nach unten gedrückt?
			downArrowKeyPressed();
			break;
		case VK_LEFT: // Pfeiltaste nach links gedrückt?
			leftArrowKeyPressed();
			break;
		case VK_ESCAPE: // Escape gedrückt?
			escapeKeyPressed();
			break;
		case VK_ENTER: // Enter gedrückt?
			enterKeyPressed();
			break;
		case VK_SPACE:
			spaceKeyPressed(); // Leertaste gedrückt?
			break;
		}
		
			
	}

	private void keyReleasedAction(final KeyEvent e)
	{
		final int keyCode = e.getKeyCode();
		final int walkdir = player.walkDirection;

		if ((keyCode == VK_UP && walkdir == 0) || (keyCode == VK_RIGHT && walkdir == 1) || (keyCode == VK_DOWN && walkdir == 2) || (keyCode == VK_LEFT && walkdir == 3))
			player.walkDirection = 4;
		
	}

	private void rightArrowKeyPressed()
	{
		player.walkDirection = 1; 
	}

	private void leftArrowKeyPressed()
	{
		player.walkDirection = 3; 
	}

	void upArrowKeyPressed()
	{
		if(menuChoice == 0)
		{
			player.walkDirection = 0;
		}
		else
		{
			menuChoice--;
			if(menuChoice == 0)
				menuChoice = 4;
			drawMenu();
		}
	}

	void downArrowKeyPressed()
	{
		if(menuChoice == 0)
		{
			player.walkDirection = 2;
		}
		else
		{
			menuChoice++;
			if(menuChoice > 4)
				menuChoice = 1;
			drawMenu();
		}
	}
	
	private void escapeKeyPressed()
	{
		if(!isWalkingEnabled() && menuChoice == 0) // Darf ESC-Menü nicht aufgerufen werden?
			return;
		
		if(menuChoice == 0)
		{
			// Menü öffnen
			setWalkingEnabled(false);
			menuChoice = 1;
			drawMenu();
		}
		else
		{
			// Menü schließen
			setWalkingEnabled(true);
			menuChoice = 0;
			drawMap();
		}
		
		
	}

	void enterKeyPressed()
	{
		switch(menuChoice)
		{
		case 1:
			// Hilfe
			break;
		case 2:
			// Deserialisieren
			XMLReader reader = new XMLReader();
			Map map = reader.readXMLFile("C:/Users/Lukas/Desktop/map.xml");
			player.setOnMap(map, 5, 5);
		case 3:
			// Serialisieren
			XMLWriter writer = new XMLWriter();
			writer.writeXMLFile(player.place, "C:/Users/Lukas/Desktop/map.xml");
			break;
		case 4:
			// Beenden
			break;
		}
	}
	
	private void spaceKeyPressed()
	{
		if(menuChoice != 0)
			return;

		Field[] fields = null;
		Person person = null;
		
		// Spieler sieht nach oben?
		if(player.lookDirection == 0 && player.yPos >= 1)
		{
			fields = player.place.getFields()[player.xPos][player.yPos-1];
			person = player.place.getPersons()[player.xPos][player.yPos-1];
		}

		// Spieler sieht nach rechts?
		if(player.lookDirection == 1 && player.xPos <= player.place.getWidth() - 2)
		{
			fields = player.place.getFields()[player.xPos+1][player.yPos];
			person = player.place.getPersons()[player.xPos+1][player.yPos];
		}

		// Spieler sieht nach unten?
		if(player.lookDirection == 2 && player.yPos <= player.place.getHeight() - 2)
		{
			fields = player.place.getFields()[player.xPos][player.yPos+1];
			person = player.place.getPersons()[player.xPos][player.yPos+1];
		}
		
		// Spieler sieht nach links?
		if (player.lookDirection == 3 && player.xPos >= 1)
		{
			fields = player.place.getFields()[player.xPos-1][player.yPos];
			person = player.place.getPersons()[player.xPos-1][player.yPos];
		}
		
		if (person != null) // Person da?
			person.onClick((player.lookDirection + 2) % 4);
		if (fields[0] != null) // Feld in 1. Ebene?
			fields[0].onSpacePressed();
		if (fields[1] != null) // Feld in 2. Ebene?
			fields[1].onSpacePressed();
		if (fields[2] != null) // Feld in 3. Ebene?
			fields[2].onSpacePressed();

	}

	
	/** Startet das Spiel, sobald Start-Button aktiviert wird */
	void buttonStart_ActionPerformed(final ActionEvent evt)
	{
		hideMenu(); // Komponenten entfernen
		panel.add(screen); // Komponent hinzufügen
		// Aktualisieren
		panel.validate(); 
		panel.repaint();

		// Laden, Fortschritt alle 20% loggen
		System.out.println("  0%");
			Field.load(this);
		System.out.println(" 20%");
			House.load();
		System.out.println(" 40%");
			PersonType.load();
		System.out.println(" 60%");
			MapUtils.load(this);
			setPlayer(new Player(7, 7, MapUtils.getMaps()[0], this));
		System.out.println(" 80%");
			Teleporter.load(this);
			player.place.drawImage(this);
		
		// Variablen aktualisieren
		setRunning(true);
		setWalkingEnabled(true);

		// Thread für gleichschnelles Laufen auf jedem PC
		loop = new Loop(this);
		loop.start();

		player.place.drawImage(this);
		SoundPlayer.soundMenu.stop(); // Menü-Musik beenden, damit Stadt-Theme gespielt werden kann
		player.place.getSoundBackground().play();
		System.out.println("100%");
	}

	/** Zeigt die Hilfe an, sobald Help-Button aktiviert wird */
	private void buttonHelp_ActionPerformed(final ActionEvent evt)
	{
		// Komponenten entfernen
		hideMenu();

		// Komponenten hinzufügen
		panel.add(htext);
		panel.add(backToMenu);

		// Aktualisieren
		panel.validate();	
		panel.repaint();
	}

	/** Zeigt die Credits an, sobal Credits-Button aktiviert wird */
	private void buttonCredits_ActionPerformed(final ActionEvent evt)
	{
		// Komponenten entfernen
		hideMenu();
		
		// Komponenten hinzufügen
		panel.add(ctext);
		panel.add(backToMenu);
		
		// Aktualisieren
		panel.validate();	
		panel.repaint();
	}

	/** ÖFfnet die Homepage, sobald Website-Button aktiviert wird */
	private void buttonWebsite_ActionPerformed(final ActionEvent evt)
	{
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.psychohouse.bplaced.com");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Lädt das Hauptmenü wieder, damit erneut eine menü-Option gewählt werden kann */
	private void backToMenu_ActionPerformed(final ActionEvent evt)
	{
		// Komponenten entfernen
		panel.remove(ctext);
		panel.remove(htext);
		panel.remove(backToMenu);
		
		// Komponenten hinzufügen
		panel.add(buttonStart);
		panel.add(buttonHelp);
		panel.add(buttonCredits);
		panel.add(buttonWebsite);
		panel.add(headline);
		panel.add(background);
		
		// Aktualisieren
		startScreenDisplayed = true;
		panel.validate();
		panel.repaint();
	}
	
	/** Zeichnet die aktuelle Map */
	void drawMap() 
	{
		final Image img = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR); // Ermöglicht Bild-Aktualisierung ohne flackern
		final Graphics g = img.getGraphics();
		
		player.place.drawPartOfImage(this, player.xPos - 1, player.yPos - 1, player.xPos + 3, player.yPos + 3);

		// Initialisierung Spieler-Variablen
		final int XABS = player.xPos;
		final int YABS = player.yPos;
		final int STEPX = player.stepX;
		final int STEPY = player.stepY;
		
		final int STARTX = (int)((-XABS * 16 + 400 / zoom - zoom + STEPX - 200)*zoom);
		final int STARTY = (int)((-YABS * 16 + 300 / zoom + STEPY - 200)*zoom);
		
		// 1. Ebene
		Image img1 = player.place.getImg1();
		g.drawImage(img1, STARTX, STARTY, (int)(img1.getWidth(this)*zoom), (int)(img1.getHeight(this)*zoom), this);
		
		// 2. Ebene
		Image img2 = player.place.getImg2();
		g.drawImage(img2, STARTX, STARTY, (int)(img2.getWidth(this)*zoom), (int)(img2.getHeight(this)*zoom), this);
		
		// Personen
		Image imgP = player.place.getImgP();
		g.drawImage(imgP, STARTX, STARTY, (int)(imgP.getWidth(this)*zoom), (int)(imgP.getHeight(this)*zoom), this);
		
		// 3. Ebene
		Image img3 = player.place.getImg3();
		g.drawImage(img3, STARTX, STARTY, (int)(img3.getWidth(this)*zoom), (int)(img3.getHeight(this)*zoom), this);
		
		// Umgebung des Spielers
		Image imgPart = player.place.getImgPart();
		g.drawImage(imgPart, STARTX, STARTY, (int)(imgPart.getWidth(this)*zoom), (int)(imgPart.getHeight(this)*zoom), this);

		// Grafik ersetzen
		screen.getGraphics().drawImage(img, 0, 0, 800, 600, this);			
	}

	/** Zeichnet Hauptmenü */
	void drawMenu() 
	{
		final Graphics g = screen.getGraphics();
		SoundPlayer.soundBeep.play(); // Informiert Spieler mit Piep-Geräusch

		g.setColor(new Color(22, 23, 27)); // Dunkles Grau
		g.fillRect(590, 10, 200, 580); // Menü-Fläche

		g.setColor(new Color(255, 127, 0)); // Orange
		g.drawRect(592, 12, 196, 576); // Menü-Rahmen

		// Menü-Optionen
		
		g.drawString("Hilfe", 610, 40);
		g.drawString("Spielstand Laden", 610, 80);
		g.drawString("Spielstand Speichern", 610, 120);
		g.drawString("Schließen", 610, 160);
		g.drawString("x: " + player.xPos + " y: " + player.yPos, 610, 240); // Position des Spielers, wird vor Release entfernt
		g.drawLine(610, menuChoice * 40 + 5, 770, menuChoice * 40 + 5); // Unterstreicht die aktuell ausgewählte Menü-Option
	}

	/** Zeichnet den aktuellen Stand eines Kampfes */
	void drawBattle(Fighter playerFighter, Fighter opponentFighter, String text)
	{
		Graphics g = screen.getGraphics();
		
		SoundPlayer.soundBeep.play(); // Informiert Spieler mit Piep-Geräusch

		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 600);	// Screen-Hintergrund

		// === Fighter des Spielers ===
		
		g.setColor(Color.black);
		g.drawRect(50, 264, 256, 256); // Rahmen für SpielerFighter-Bild
		
		g.drawRect(50, 525, 256, 10); // Rahmen Gesundheitsbalken
		g.drawRect(50, 540, 256, 10); // Rahmen Energiebalken
		
		// Falls SpielerFighter-Gesundheit größer 0 ist
		if(playerFighter.getBattleHealth()>0)
		{
			g.setColor(new Color(0, 127, 255)); // Cyan
			g.fillRect(51, 526, (int)(playerFighter.getBattleHealth() / playerFighter.getHealthLimit() * 255), 9); // Gesundheits-Balken (Anteil aktueller Gesundheit von maximaler Gesundheit)
		}

		// Falls SpielerFighter-Energie größer 0 ist
		if(playerFighter.getBattleEnergy()>=0)
		{
			g.setColor(new Color(255, 127, 0));	// Orange
			g.fillRect(51, 541, (int)(playerFighter.getBattleEnergy() / playerFighter.getEnergyLimit() * 255), 9); // Energie-Balken (Anteil aktueller Energie von maximaler Energie)
		}
		
		// === Attacken ===

		g.setColor(new Color(200, 200, 200)); // Helles Grau
		
		g.fillRect(324, 264, 426, 286);	// Attackenauswahl Hintergrund
		
		drawAttackButtons(g);
		
		g.fillRect(473, 413, 119, 119); // Attackenfläche 4
		
		g.setColor(new Color(255, 255, 255)); // Weiß
		
		// === Attackenbeschreibungen ===
		
		if(playerFighter.getAttacks()[0] != null) // Falls SpielerFighter eine Attacke im ersten Slot hat
			g.drawString(playerFighter.getAttacks()[0].getAttackType().getName(), 355, 305); // Attackenbeschreibung 1
		if(playerFighter.getAttacks()[1] != null) // Falls SpielerFighter eine Attacke im zweiten Slot hat
			g.drawString(playerFighter.getAttacks()[1].getAttackType().getName(), 355, 433); // Attackenbeschreibung 2
		if(playerFighter.getAttacks()[2] != null) // Falls SpielerFighter eine Attacke im dritten Slot hat
			g.drawString(playerFighter.getAttacks()[2].getAttackType().getName(), 483, 305); // Attackenbeschreibung 3
		if(playerFighter.getAttacks()[3] != null) // Falls SpielerFighter eine Attacke im vierten Slot hat
			g.drawString(playerFighter.getAttacks()[3].getAttackType().getName(), 483, 433); // Attackenbeschreibung 4
		
		// === Gegner ===

		g.setColor(Color.black);
		g.drawRect(622, 50, 128, 128); // Rahmen für GegnerFighter-Bild
		
		g.drawRect(622, 183, 128, 5); // Rahmen Gesundheitsbalken
		g.drawRect(622, 193, 128, 5); // Rahmen EnergieBalken
		
		g.setColor(new Color(0, 127, 255)); // Cyan
		g.fillRect(623, 184, (int)(opponentFighter.getBattleHealth() / opponentFighter.getHealthLimit() * 127), 4); // Gesundheits-Balken (Anteil aktueller Gesundheit von maximaler Gesundheit)
		g.setColor(new Color(255, 127, 0)); // Orange
		g.fillRect(623, 194, (int)(opponentFighter.getBattleEnergy() / opponentFighter.getEnergyLimit() * 127), 4); // Energie-Balken (Anteil aktueller Energie von maximaler Energie)
		
		drawBattleInformation(text); // Informiert den Spieler
	}
	
	/** Zeichnet AttackButtons, damit Spieler Attacke auswählen kann */
	void drawAttackButtons(Graphics g)
	{
		g.setColor(Color.black);
		
		g.drawRect(344, 284, 120, 120); // Attackenrahmen 1
		g.drawRect(344, 412, 120, 120); // Attackenrahmen 2
		g.drawRect(472, 284, 120, 120); // Attackenrahmen 3
		g.drawRect(472, 412, 120, 120); // Attackenrahmen 4
		
		g.setColor(new Color(100, 100, 100)); // Grau
		
		g.fillRect(345, 285, 119, 119); // Attackenfläche 1
		g.fillRect(345, 413, 119, 119); // Attackenfläche 2
		g.fillRect(473, 285, 119, 119); // Attackenfläche 3
		g.fillRect(473, 413, 119, 119); // Attackenfläche 4
	}
	
	/** Berechnet mit MouseEvent (e), welche Attacke vom Spieler ausgewählt wurde und führt eine Kampfrunde */
	void calculateSelectedAttack(Fighter playerFighter, Fighter opponentFighter, MouseEvent e)
	{
		Graphics g = screen.getGraphics();
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		setWalkingEnabled(false);
		activeAttack = null;
		
		
		
		// 1. Attacke ausgewählt?
		if(mouseX >= 344 && mouseX<= 464 && mouseY >= 284 && mouseY <= 404)
		{
			drawAttackButtons(g);
			g.setColor(Color.green);
			g.fillRect(345, 285, 119, 119);
			activeAttack = playerFighter.getAttacks()[0];
		}

		// 2. Attacke ausgewählt?
		if(mouseX >= 344 && mouseX<= 464 && mouseY >= 412 && mouseY <= 532)
		{
			drawAttackButtons(g);
			g.setColor(Color.green);
			g.fillRect(345, 413, 119, 119);
			activeAttack = playerFighter.getAttacks()[1];
		}

		// 3. Attacke ausgewählt?
		if(mouseX >= 472 && mouseX<= 592 && mouseY >= 284 && mouseY <= 404)
		{
			drawAttackButtons(g);
			g.setColor(Color.green);
			g.fillRect(473, 285, 119, 119);
			activeAttack = playerFighter.getAttacks()[2];
		}

		// 4. Attacke ausgewählt?
		if(mouseX >= 472 && mouseX<= 592 && mouseY >= 412 && mouseY <= 532)
		{
			drawAttackButtons(g);
			g.setColor(Color.green);
			g.fillRect(473, 413, 119, 119);
			activeAttack = playerFighter.getAttacks()[3];
		}
		
		// Keine Attacke ausgewählt?
		if(activeAttack == null)
		{
			return;
		}
		
		// Gegner wählt Attacke
		Attack opponentAttack = opponentFighter.getAttacks()[(int)Math.round(Math.random()*3)];
		
		// Informiert Spieler über Kampfgeschehen der Runde
		String playerAttackInformation = "P: " + playerFighter.attackFighter(activeAttack, opponentFighter);
		String opponentAttackInformation = "G: " + opponentFighter.attackFighter(opponentAttack, playerFighter);
		
		// Pause, damit der Spieler die grüne, ausgewählte Attacke sieht
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException te)
		{
			te.printStackTrace();
		}
		
		// Falls der Gegner noch nicht tot ist, wird die grüne ausgewählte Attacke wieder normal
		if(inBattle)
		{
			drawBattle(playerFighter, opponentFighter, playerAttackInformation + "\n" + opponentAttackInformation);
			g.setColor(Color.black);
		}
		else
		{
			drawMap();
		}
	}
	
	/** Informiert Spieler mit Text (text) über Kampfgeschehen */
	void drawBattleInformation(String text)
	{
		Graphics g = screen.getGraphics();
		
		String[] textparts = text.split("\n"); // Teilt zur getrennten Anzeige zwischen Zeilen auf
		
		g.setColor(Color.white);
		g.fillRect(50, 50, 300, 50); // Hintergrund
		g.setColor(Color.black);
		g.drawRect(50, 50, 300, 50); // Rahmen
		
		for(int i = 0; i < textparts.length; i++)
			g.drawString(textparts[i], 55, 65 + i * 15); // Schreibt Zeilen uuntereinander
	}

	/** Ermöglicht das Wechseln von Anzeigen vor Spielstart */
	void hideMenu()
	{	
		
		startScreenDisplayed = false;
		
		// Buttons entfernen

		panel.remove(background);
		panel.remove(headline);
		panel.remove(buttonStart);
		panel.remove(buttonHelp);
		panel.remove(buttonCredits);
		panel.remove(buttonWebsite);
		
		panel.validate(); // Aktualisierung
	}

	/** Zeigt an, was eine Person sagt (text) */
	void printDialog(final String text)
	{
		final Graphics g = screen.getGraphics();
		
		g.setFont(new Font("Segoe UI", 0, 12)); // spezielle Schrift zur Unterscheidung von Monolog
		
		g.setColor(Color.white);
		g.fillRect(0, 500, 800, 100); // Fläche
		
		g.setColor(new Color(64, 64, 64));
		g.drawRect(0, 500, 799, 99); // Rahmen
		
		g.setColor(Color.black);
		g.drawString(text, 20, 520); // Text
	}

	/** Informiert Spieler mit einem Monolog (text) */
	void printMonolog(final String text)
	{
		final Graphics g = screen.getGraphics();
		
		g.setFont(new Font("Segoe UI", 0, 12)); // spezielle Schrift zur Unterscheidung von Dialog
		
		g.setColor(Color.white);
		g.fillRect(0, 500, 800, 100); // Fläche
		
		g.setColor(new Color(64, 64, 64));
		g.drawRect(0, 500, 799, 99); // Rahmen
		
		g.setColor(new Color(128, 128, 128));
		g.drawString(text, 20, 520); //Text
	}

	// === Setter und Getter ===
	
	Player getPlayer() {
		return player;
	}

	void setPlayer(final Player player) {
		this.player = player;
	}

	boolean isWalkingEnabled() {
		return isWalkingEnabled;
	}

	void setWalkingEnabled(boolean isWalkingEnabled) {
		this.isWalkingEnabled = isWalkingEnabled;
	}

	boolean isRunning() {
		return running;
	}

	void setRunning(boolean running) {
		this.running = running;
	}

	boolean isInBattle() {
		return inBattle;
	}

	void setInBattle(boolean inBattle1) {
		this.inBattle = inBattle1;
	}
}