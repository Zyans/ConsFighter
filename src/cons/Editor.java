package cons;import java.awt.Color;import java.awt.Graphics;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.MouseAdapter;import java.awt.event.MouseEvent;import javax.swing.JCheckBoxMenuItem;import javax.swing.JMenu;import javax.swing.JMenuBar;import javax.swing.JMenuItem;public class Editor extends Main {	static Field currentSelection;	static Field[] selection = new Field[100];	static int[] selectionPositionsX = new int[selection.length];	static int[] selectionPositionsY = new int[selection.length];	static int z = 1;	private boolean selectionSet;//Damit Felder nicht beim Ausw�hlen gesezt werden 	private int genSet = 0; //Start oder Endfeld f�r Generatoren ausgew�hlt?	private int genStartX;//Start/Endfelder f�r Generatoren	private int genStartY;	private int genEndX;	private int genEndY;		JMenuBar menu = new JMenuBar();	JMenu map, edit, options;	JMenuItem newmap, openmap, savemap, closemap, forward, backward, editsize;	JCheckBoxMenuItem showGridItem, showLevel1Item, showLevel2Item, showLevel3Item, showPersonsItem;	@Override	void buttonStart_ActionPerformed(final ActionEvent evt) {		super.buttonStart_ActionPerformed(evt);		Map map = new Map(32, 32,  SoundPlayer.soundCity2);		map.setRect(Field.meadow_light, 0, 0, 0, 32, 32);		map.drawImage(this);		setPlayer(new Player(5, 5, map, this));	}		public static void main(String args[]) {		new Editor();	}	@Override	public void init() {		super.init();		initMenubar();		screen.addMouseListener(new MouseAdapter() {			@Override			public void mousePressed(MouseEvent arg0) {				selectionSet = false;									int x = (int)((arg0.getX()+zoom*zoom) / 16 / zoom + getPlayer().xPos - 25 / zoom);				int y = (int)((arg0.getY()-16/zoom) / 16 / zoom + getPlayer().yPos - 18 / zoom);								Map map = getPlayer().place;								for(int i = 0; i<selectionPositionsX.length; i++){                    if(arg0.getX()>=selectionPositionsX[i] && arg0.getX() <= (selectionPositionsX[i]+32) && arg0.getY()>=selectionPositionsY[i] && arg0.getY() <= (selectionPositionsY[i]+32) && !isWalkingEnabled()){                        currentSelection = selection[i];                        selectionSet = true;                                            }                }								if(currentSelection != Field.eFence && currentSelection != Field.eTrail && !selectionSet) {					if(x >= 0 && y >= 0 && x < map.getWidth() && y < map.getHeight())						map.setRect(currentSelection, x, y, z, 1, 1);				}				else if(currentSelection == Field.eFence && !selectionSet){//Zum setzen der Z�une und Wege					if(genSet == 0) {						genSet = 1;						genStartX = x;						genStartY = y;						map.setRect(Field.eFence, genStartX, genStartY, z, 1, 1);					}					else {						genSet = 0;						genEndX = x;						genEndY = y;						if(genEndX>genStartX && genEndY>genStartY)							map.setFence(Fence.fence1, genStartX, genStartY, z, genEndX-genStartX, genEndY-genStartY);					}				} else if(currentSelection == Field.eTrail && !selectionSet){					if(genSet == 0) {						genSet = 1;						genStartX = x;						genStartY = y;						map.setRect(Field.eTrail, genStartX, genStartY, z, 1, 1);					}					else {						genSet = 0;						genEndX = x;						genEndY = y;						if(genEndX>genStartX && genEndY-genStartY < genEndX-genStartX)							map.setHorizontalPath(Trail.path3, genStartX, genStartY, genEndX-genStartX);						if(genEndY>genStartY && genEndX-genStartX < genEndY-genStartY)							map.setVerticalPath(Trail.path3, genStartX, genStartY, genEndY-genStartY);					}				}									map.drawImage(Editor.this);				getPlayer().place = map;				drawMap();				if(Editor.this.menuChoice != 0)					drawMenu();			}		});	}		private void initMenubar() {		showGrid = true;				menu = new JMenuBar();				map = new JMenu("Map");		newmap = new JMenuItem("Neu");		openmap = new JMenuItem("�ffnen");		savemap = new JMenuItem("Speichern");		closemap = new JMenuItem("Schliessen");				edit = new JMenu("Bearbeiten");		forward = new JMenuItem("Wiederholen");		backward = new JMenuItem("R�ckg�ngig");		editsize = new JMenuItem("Mapgr�sse �ndern");				options = new JMenu("Optionen");		showGridItem = new JCheckBoxMenuItem("Gitter anzeigen", showGrid);		showLevel1Item = new JCheckBoxMenuItem("Ebene 1 anzeigen", showLevel1);		showLevel2Item = new JCheckBoxMenuItem("Ebene 2 anzeigen", showLevel2);		showLevel3Item = new JCheckBoxMenuItem("Ebene 3 anzeigen", showLevel3);		showPersonsItem = new JCheckBoxMenuItem("Personen anzeigen", showPersons);				map.add(newmap);		map.add(openmap);		map.add(savemap);		map.add(closemap);		menu.add(map);				edit.add(forward);		edit.add(backward);		edit.add(editsize);		menu.add(edit);		menu.add(options);		options.add(showGridItem);		options.add(showLevel1Item);		options.add(showLevel2Item);		options.add(showLevel3Item);		options.add(showPersonsItem);				setJMenuBar(menu);				newmap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				openmap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				savemap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				closemap.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				forward.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				backward.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});				editsize.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {			}		});			showGridItem.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showGrid = showGridItem.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showLevel1Item.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showLevel1 = showLevel1Item.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showLevel2Item.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showLevel2 = showLevel2Item.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showLevel3Item.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showLevel3 = showLevel3Item.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});					showPersonsItem.addActionListener(new ActionListener() {			@Override			public void actionPerformed(ActionEvent e) {				showPersons = showPersonsItem.getModel().isSelected();				getPlayer().place.drawImage(Editor.this);			}		});		}		@Override	void drawMenu() {		super.drawMenu();				final Graphics g = screen.getGraphics();		g.setColor(new Color(255, 127, 0)); // Orange		g.drawString("Z-Index (" + Editor.z + ")", 610, 200);		int imgY = 320;		int imgX = 610;		for(int i=0; i < Editor.selection.length; i++){			if(i%8==0){				imgY += 21;				imgX = 610;			}			if(Editor.selection[i] == null)				return;			g.drawImage(Editor.selection[i].getImage(), imgX, imgY, 16, 16, this);			Editor.selectionPositionsX[i] = imgX;			Editor.selectionPositionsY[i] = imgY;						imgX+=21;					}	}	@Override	void upArrowKeyPressed() {		if(menuChoice == 0)		{			getPlayer().walkDirection = 0;		}		else		{			menuChoice--;			if(menuChoice == 0)				menuChoice = 5;			drawMenu();		}	}	@Override	void downArrowKeyPressed() {		if(menuChoice == 0)		{			getPlayer().walkDirection = 2;		}		else		{			menuChoice++;			if(menuChoice > 5)				menuChoice = 1;			drawMenu();		}	}	@Override	void enterKeyPressed()	{		if(menuChoice == 5) {			// Editor Z-Position setzen			Editor.z = (Editor.z+1)%3;			drawMenu();		}		else {			super.enterKeyPressed();		}	}}