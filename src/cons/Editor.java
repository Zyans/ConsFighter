package cons;import java.awt.Color;import java.awt.Container;import java.awt.Graphics;import java.awt.Image;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.MouseAdapter;import java.awt.event.MouseEvent;import java.awt.image.BufferedImage;import javax.swing.JButton;import javax.swing.JCheckBoxMenuItem;import javax.swing.JComboBox;import javax.swing.JDialog;import javax.swing.JLabel;import javax.swing.JMenu;import javax.swing.JMenuBar;import javax.swing.JMenuItem;import javax.swing.JTextField;public class Editor extends Main {	static Field currentSelection;	static Field[] selection = new Field[100];	static int[] selectionPositionsX = new int[selection.length];	static int[] selectionPositionsY = new int[selection.length];	static int z = 1;	private boolean selectionSet;//Damit Felder nicht beim Ausw�hlen gesezt werden 	private int genStartX;//Start/Endfelder f�r Generatoren	private int genStartY;	private int genEndX;	private int genEndY;		private int mouseStartX = 0;	private int mouseStartY = 0;	private Map map;	private Person selectedPerson;	//comment	JMenuBar menu = new JMenuBar();	JMenu mMap, mEdit, mOptions, mGenerate;	JMenuItem newmap, openmap, savemap, closemap, forward, backward, editsize, generatePerson;	JCheckBoxMenuItem showGridItem, showLevel1Item, showLevel2Item, showLevel3Item, showPersonsItem;	@Override	void buttonStart_ActionPerformed(final ActionEvent evt) {		super.buttonStart_ActionPerformed(evt);		Map map = new Map(32, 32, SoundPlayer.soundCity2);		map.setRect(Field.meadow_light, 0, 0, 0, 32, 32);		map.drawImage(this);		setPlayer(new Player(5, 5, map, this));	}		public static void main(String args[]) {		new Editor();	}	@Override	void F1KeyPressed() {		z = 0;	}	@Override	void F2KeyPressed() {		z = 1;	}	@Override	void F3KeyPressed() {		z = 2;	}	@Override	void ControlKeyPressed() {		showLevel1 = z == 0;		showLevel2 = z == 1;		showLevel3 = z == 2;		showLevel1Item.getModel().setSelected(showLevel1);		showLevel2Item.getModel().setSelected(showLevel2);		showLevel3Item.getModel().setSelected(showLevel3);		getPlayer().place.drawImage(Editor.this);	}	@Override	void ControlKeyReleased() {		showLevel1 = true;		showLevel2 = true;		showLevel3 = true;		showLevel1Item.getModel().setSelected(true);		showLevel2Item.getModel().setSelected(true);		showLevel3Item.getModel().setSelected(true);		getPlayer().place.drawImage(Editor.this);	}		@Override	public void init() {		super.init();		selection[0] = Field.eHouse;		initMenubar();		screen.addMouseListener(new MouseAdapter() {			@Override			public void mousePressed(MouseEvent arg0) {				selectionSet = false;									int x = (int)((arg0.getX()+zoom*zoom) / 16 / zoom + getPlayer().xPos - 25 / zoom);				int y = (int)((arg0.getY()-16/zoom) / 16 / zoom + getPlayer().yPos - 18 / zoom);								mouseStartX = arg0.getX();				mouseStartY = arg0.getY();				genStartX = x;				genStartY = y;								for(int i=0; i<selectionPositionsX.length; i++){		            if(arg0.getX()>=selectionPositionsX[i] && arg0.getX() <= (selectionPositionsX[i]+32) && arg0.getY()>=selectionPositionsY[i] && arg0.getY() <= (selectionPositionsY[i]+32) && !isWalkingEnabled()){		                currentSelection = selection[i];		                selectionSet = true;		            }		        }								if(arg0.getButton() == arg0.BUTTON3) {					currentSelection = null;				}								map = getPlayer().place;									if(selectedPerson != null) {					selectedPerson.setxPos(x);					selectedPerson.setyPos(y);					selectedPerson.setPlace(map);					map.addPerson(selectedPerson);					selectedPerson = null;				} else if(currentSelection != Field.eFence && currentSelection != Field.eTrail && !selectionSet){					map.setRect(currentSelection, x, y, z, 1, 1);				} else if(currentSelection == Field.eFence && !selectionSet){//Zum setzen der Z�une und Wege						map.setRect(Field.eFence, genStartX, genStartY, z, 1, 1);														} else if(currentSelection == Field.eTrail && !selectionSet){						map.setRect(Field.eTrail, genStartX, genStartY, z, 1, 1);									}													map.drawImage(Editor.this);				drawMap(null);				if(Editor.this.menuChoice != 0)					drawMenu();			}						public void mouseReleased(MouseEvent arg0){				int x = (int)((arg0.getX()+zoom*zoom) / 16 / zoom + getPlayer().xPos - 25 / zoom);				int y = (int)((arg0.getY()-16/zoom) / 16 / zoom + getPlayer().yPos - 18 / zoom);								genEndX = x;				genEndY = y;				int genWidth = genEndX - genStartX;				int genHeight = genEndY - genStartY;				if(genWidth == 0)					genWidth--;				if(genHeight == 0)					genHeight--;				if(genWidth < 1) {					genWidth = 0 - genWidth - 1;					genStartX -= genWidth - 0;				}				if(genHeight < 1) {					genHeight = 0 - genHeight - 1;					genStartY -= genHeight - 0;				}								if(currentSelection == Field.eFence && !selectionSet){					map.setFence(Fence.fence1, genStartX, genStartY, z, genWidth, genEndY-genStartY);				} else if(currentSelection == Field.eTrail && !selectionSet){											if(genHeight > genWidth)							map.setVerticalPath(Trail.path3, genStartX, genStartY, genHeight);						else							map.setHorizontalPath(Trail.path3, genStartX, genStartY, genWidth);				} else if(!selectionSet){										if(currentSelection == Field.eHouse){						map.setHouse(House.house1, genStartX-1, genStartY-1);					}					else{						map.setRect(currentSelection, genStartX, genStartY, z, genWidth+1, genHeight+1);					}				}				panel.getGraphics().clearRect(0,0,800,600);				map.drawImage(Editor.this);				drawMap(null);				if(Editor.this.menuChoice != 0)					drawMenu();			}		});		screen.addMouseMotionListener(new MouseAdapter(){			public void mouseDragged(MouseEvent e) {				selectionSet = false;									Image image = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);				Graphics g = image.getGraphics();				drawMap(g);				g.setColor(new Color(255, 0, 0, 63));				int startSelectedAreaX = (int)(mouseStartX/16)*16-1;				int startSelectedAreaY = (int)(mouseStartY/16)*16-4;				int selectedAreaWidth = (int)(e.getX()/16)*16-startSelectedAreaX+15;				int selectedAreaHeight = (int)(e.getY()/16)*16-startSelectedAreaY+12;								if(selectedAreaWidth < 16) {					selectedAreaWidth = 0 - selectedAreaWidth + 16;					startSelectedAreaX -= selectedAreaWidth - 16;				}								if(selectedAreaHeight < 16) {					selectedAreaHeight = 0 - selectedAreaHeight + 16;					startSelectedAreaY -= selectedAreaHeight - 16;				}								g.fillRect(startSelectedAreaX, startSelectedAreaY, selectedAreaWidth, selectedAreaHeight);				panel.getGraphics().drawImage(image, 0, 0, null);							}		});	}		private void initMenubar() {		showGrid = true;				menu = new JMenuBar();				mMap = new JMenu("Map");		newmap = new JMenuItem("Neu");		openmap = new JMenuItem("�ffnen");		savemap = new JMenuItem("Speichern");		closemap = new JMenuItem("Schliessen");				mEdit = new JMenu("Bearbeiten");		forward = new JMenuItem("Wiederholen");		backward = new JMenuItem("R�ckg�ngig");		editsize = new JMenuItem("Mapgr�sse �ndern");				mOptions = new JMenu("Optionen");		showGridItem = new JCheckBoxMenuItem("Gitter anzeigen", showGrid);		showLevel1Item = new JCheckBoxMenuItem("Ebene 1 anzeigen", showLevel1);		showLevel2Item = new JCheckBoxMenuItem("Ebene 2 anzeigen", showLevel2);		showLevel3Item = new JCheckBoxMenuItem("Ebene 3 anzeigen", showLevel3);		showPersonsItem = new JCheckBoxMenuItem("Personen anzeigen", showPersons);				mMap.add(newmap);		mMap.add(openmap);		mMap.add(savemap);		mMap.add(closemap);		menu.add(mMap);				mEdit.add(forward);		mEdit.add(backward);		mEdit.add(editsize);		menu.add(mEdit);		mGenerate = new JMenu("Generiere");		menu.add(mGenerate);		generatePerson = new JMenuItem("Person");		mGenerate.add(generatePerson);		menu.add(mOptions);		mOptions.add(showGridItem);		mOptions.add(showLevel1Item);		mOptions.add(showLevel2Item);		mOptions.add(showLevel3Item);		mOptions.add(showPersonsItem);				setJMenuBar(menu);				newmap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				newMapActionPerformed();			}		});					editsize.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				editSizeActionPerformed();			}		});					openmap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				savemap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				closemap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				forward.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				backward.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});		showGridItem.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showGrid = showGridItem.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showLevel1Item.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showLevel1 = showLevel1Item.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showLevel2Item.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showLevel2 = showLevel2Item.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showLevel3Item.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showLevel3 = showLevel3Item.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showPersonsItem.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showPersons = showPersonsItem.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					generatePerson.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {								final JDialog dialog = new JDialog(Editor.this, "Personen-Generator", false);				dialog.setSize(300, 200);				Container c = new Container();				c.setLayout(null);								final JTextField inputText = new JTextField();				inputText.setBounds(30, 30, 230, 20);				c.add(inputText);				 				String[] data = {"Alter Mann", "Alte Frau", "Kapit�n", "M�dchen", "Programmierer", "Alter Mann", "Professor", "Nerd", "Kind", "junge Frau", "Polizist"};				final JComboBox inputPersonType = new JComboBox(data);				inputPersonType.setBounds(30, 60, 230, 20);				c.add(inputPersonType);								JButton buttonOkay = new JButton("�bernehmen");				buttonOkay.setBounds(30, 90, 230, 20);				buttonOkay.addActionListener(new ActionListener() {					@Override					public void actionPerformed(ActionEvent arg0) {						selectedPerson = new Person(null, 0, 0, inputText.getText(), PersonType.getType()[inputPersonType.getSelectedIndex()], Editor.this);						dialog.setVisible(false);					}				});				c.add(buttonOkay);								dialog.add(c);				dialog.setVisible(true);			}		});		}		@Override	void drawMenu() {		super.drawMenu();				final Graphics g = screen.getGraphics();		int imgY = 320;		int imgX = 610;		for(int i=0; i < Editor.selection.length; i++){			if(i%8==0){				imgY += 21;				imgX = 610;			}			if(Editor.selection[i] == null)				return;			g.drawImage(Editor.selection[i].getImage(), imgX, imgY, 16, 16, this);			Editor.selectionPositionsX[i] = imgX;			Editor.selectionPositionsY[i] = imgY;						imgX+=21;					}	}	@Override	void enterKeyPressed()	{		if(menuChoice == 5) {			// Editor Z-Position setzen			Editor.z = (Editor.z+1)%3;			drawMenu();		}		else {			super.enterKeyPressed();		}	}		void editSizeActionPerformed() {		final JDialog dialog = new JDialog(Editor.this, "Neue Map erstellen!", false);		dialog.setSize(300, 200);		Container c = new Container();		c.setLayout(null);				final JTextField inputWidth = new JTextField();		inputWidth.setBounds(30, 30, 230, 20);		c.add(inputWidth);				final JTextField inputHeight = new JTextField();		inputHeight.setBounds(30, 60, 230, 20);		c.add(inputHeight);				JButton buttonOkay = new JButton("�bernehmen");		buttonOkay.setBounds(30, 120, 230, 20);		buttonOkay.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent arg0) {				newMapActionPerformed();			}				private void newMapActionPerformed() {				map = getPlayer().place;				Field[][][] fields = map.getFields();				int width = Integer.parseInt(inputWidth.getText());				int height = Integer.parseInt(inputHeight.getText());				map = new Map(width, height, SoundPlayer.soundCity1);				map.setRect(Field.meadow_light, 0, 0, 0, width, height);				for(int x = 0; x < width && x < fields.length; x++) {					for(int y = 0; y < height && y < fields[x].length; y++) {						Field[][][] newFields = map.getFields();						newFields[x][y] = fields[x][y];						map.setFields(newFields);					}				}				map.drawImage(Editor.this);				setPlayer(new Player(5, 5, map, Editor.this));				getPlayer().place = map;				dialog.setVisible(false);			}		});		c.add(buttonOkay);				dialog.add(c);		dialog.setVisible(true);	}		void newMapActionPerformed() {		final JDialog dialog = new JDialog(Editor.this, "Neue Map erstellen!", false);		dialog.setSize(300, 300);		Container c = new Container();		c.setLayout(null);				final JLabel labelWidth = new JLabel("Breite:");		labelWidth.setBounds(30, 30, 230, 20);		c.add(labelWidth);				final JTextField inputWidth = new JTextField();		inputWidth.setBounds(30, 50, 230, 20);		c.add(inputWidth);				final JLabel labelHeight = new JLabel("H�he:");		labelHeight.setBounds(30, 90, 230, 20);		c.add(labelHeight);				final JTextField inputHeight = new JTextField();		inputHeight.setBounds(30, 110, 230, 20);		c.add(inputHeight);		 		String[] data = {"soundCity1", "soundCity2", "soundCity3"};		final JComboBox inputSoundPlayer = new JComboBox(data);		inputSoundPlayer.setBounds(30, 150, 230, 20);		c.add(inputSoundPlayer);				JButton buttonOkay = new JButton("�bernehmen");		buttonOkay.setBounds(30, 180, 230, 20);		buttonOkay.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent arg0) {				newMapActionPerformed();			}			private void newMapActionPerformed() {				int width = Integer.parseInt(inputWidth.getText());				int height = Integer.parseInt(inputHeight.getText());				SoundPlayer soundPlayer = null;				switch((String)inputSoundPlayer.getSelectedItem()){				case "soundCity1":					soundPlayer = SoundPlayer.soundCity1;				case "soundCity2":					soundPlayer = SoundPlayer.soundCity2;				case "soundCity3":					soundPlayer = SoundPlayer.soundCity3;				}				map = new Map(width, height, soundPlayer);				map.setRect(Field.meadow_light, 0, 0, 0, width, height);				map.drawImage(Editor.this);				setPlayer(new Player(5, 5, map, Editor.this));				getPlayer().place = map;				dialog.setVisible(false);			}		});		c.add(buttonOkay);				dialog.add(c);		dialog.setVisible(true);	}}