package cons;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Field {
	
	private Image img;
	private boolean canGoThrough = true;
	private double walkThroughSpeed = 100;
	private int dialogNr = 0;
	private String message;
	
	private static Main main;
	
	private static final String FIELDS_HOME_DIRECTORY = "imgs/fields/";
	static final Texture TEXTURE = new Texture("texture.png");

	// === Boden ===

	static final Field meadow_dark = new Field("wiese.png");
	static final Field meadow_light = new Field(TEXTURE, 0, 0, 16);
	static final Field sand = new Field(TEXTURE, 0, 2, 16);
	static final Field mud = new Field(TEXTURE, 1, 2, 16);
	static final Field dielen = new Field(TEXTURE, 4, 0, 16);
	static final Field fliesen = new Field(TEXTURE, 3, 0, 16);
	
	// === Editor Felder === (Werden im Menue des Editors angezeigt,werden aber nicht direkt verwendet)
	
	static final Field eFence = new Field("fences/fence1/tb.png", true);
	
	static final Field eTrail = new Field("trails/trail3/tl.png", true);
	static final Field eHouse = House.house1.getB()[0];
	
	// === Sammlungen ===
	
		// === Wasser ===
	
		 static final Field water_m = new Field(TEXTURE, 7, 1, 16).setCanGoThrough(false);
		 static final Field water_br = new Field(TEXTURE, 8, 2, 16).setCanGoThrough(false);
		 static final Field water_b = new Field(TEXTURE, 7, 2, 16).setCanGoThrough(false);
		 static final Field water_bl = new Field(TEXTURE, 6, 2, 16).setCanGoThrough(false);
		 static final Field water_l = new Field(TEXTURE, 6, 1, 16).setCanGoThrough(false);
		 static final Field water_tl = new Field(TEXTURE, 6, 0, 16).setCanGoThrough(false);
		 static final Field water_t = new Field(TEXTURE, 7, 0, 16).setCanGoThrough(false);
		 static final Field water_tr = new Field(TEXTURE, 8, 0, 16).setCanGoThrough(false);
		 static final Field water_r = new Field(TEXTURE, 8, 1, 16).setCanGoThrough(false);
		
		// === Island ===
	
		 static final Field island_br = new Field(TEXTURE, 8, 4, 16).setCanGoThrough(false);
		 static final Field island_b = water_t;
		 static final Field island_bl = new Field(TEXTURE, 7, 4, 16).setCanGoThrough(false);
		 static final Field island_l = water_r;
		 static final Field island_tl = new Field(TEXTURE, 7, 3, 16).setCanGoThrough(false);
		 static final Field island_t = water_b;
		 static final Field island_tr = new Field(TEXTURE, 8, 3, 16).setCanGoThrough(false);
		 static final Field island_r = water_l;
		
		// === Fels ===
		 
		 static final Field shadowstairs = new Field("shadowstairs.png");
		 static final Field stairs = new Field(TEXTURE, 3, 1, 16);
		
	// === Bäume ===
		
		// === Baum 1 ===
		
		 static final Field tree1_b = new Field(TEXTURE, 5, 5, 16).setCanGoThrough(false);
		 static final Field tree1_t = new Field(TEXTURE, 5, 4, 16);
		
		// === Baum 2 ==
		
		 static final Field tree2_b1 = new Field(TEXTURE, 3, 6, 16).setCanGoThrough(false);
		 static final Field tree2_b2 = new Field(TEXTURE, 4, 6, 16).setCanGoThrough(false);
		 static final Field tree2_t1 = new Field(TEXTURE, 3, 4, 32);
		 static final Field tree2_t2 = new Field(TEXTURE, 4, 4, 32);
		
	// === Dekoration ===
		
		 static final Field busch = new Field(TEXTURE, 1, 1, 16).setCanGoThrough(false);
		 static final Field blumentopf = new Field(TEXTURE, 2, 0, 16).setCanGoThrough(false);
		 static final Field rock = new Field(TEXTURE, 2, 1, 16).setCanGoThrough(false);
		 static final Field flower = new Field(TEXTURE, 0, 1, 16);
		 static final Field cantGoThrough = new Field(TEXTURE, 4, 1, 16).setCanGoThrough(false);
		 static final Field window = new Field(TEXTURE, 4, 1, 40);
		 static final Field kühlschrank_b = new Field(TEXTURE, 2, 3, 16).setCanGoThrough(false);
		 static final Field kühlschrank_t = new Field(TEXTURE, 2, 2, 16);
		 static final Field stairs_t = new Field(TEXTURE, 3, 3, 16);
		 static final Field stairs_b = new Field(TEXTURE, 3, 2, 16);
		 static final Field ship_l = new Field(TEXTURE, 0, 4, 16).setCanGoThrough(false);
		 static final Field ship_r = new Field(TEXTURE, 1, 4, 16).setCanGoThrough(false);
		 static final Field sign1 = new Field(TEXTURE, 6, 5, 16).setCanGoThrough(false);
		 static final Field sign2 = new Field(TEXTURE, 6, 6, 16).setCanGoThrough(false).setMessage("Willkommen in Hemera-Town: „Der Stadt des Friedens.“");
		 static final Field hugegrass_t = new Field(TEXTURE, 5, 0, 16);
		 static final Field hugegrass_b = new Field(TEXTURE, 5, 1, 16);
		 static final Field redcouch_t = new Field(TEXTURE, 6, 3, 16).setCanGoThrough(false);
		 static final Field redcouch_b = new Field(TEXTURE, 6, 4, 16).setCanGoThrough(false);
	
		// === Briefkasten ===
	
		static final Field letterbox_t = new Field(TEXTURE, 5, 2, 16).setMessage("Deine Hand passt nicht durch den Schlitz.");
		static final Field letterbox_b = new Field(TEXTURE, 5, 3, 16).setCanGoThrough(false);
		
		// === Brunnen ===

		static Field fountain_t1 = new Field("fountain/t1.png");
		static Field fountain_t2 = new Field("fountain/t2.png");
		static Field fountain_t3 = new Field("fountain/t3.png");
		static Field fountain_b1 = new Field("fountain/b1.png").setCanGoThrough(false);
		static Field fountain_b2 = new Field("fountain/b2.png").setCanGoThrough(false);
		static Field fountain_b3 = new Field("fountain/b3.png").setCanGoThrough(false);
		
		// === Statue 1 ===
		
		static Field statue1_t = new Field(TEXTURE, 2, 7, 16).setMessage("Diese antike Statue wurde aus sehr edlem Marmor gefertigt.");
		static Field statue1_b = new Field(TEXTURE, 2, 8, 16).setCanGoThrough(false);
		
		// === Statue 2 ===
		
		static Field statue2_t = new Field(TEXTURE, 3, 7, 16).setMessage("Eine alte Marmorstatue. Über das Schild wächst Moos.");
		static Field statue2_b = new Field(TEXTURE, 3, 8, 16).setCanGoThrough(false);
		// === Zimmerpflanze ===
	
		 static final Field roomplant_t = new Field(TEXTURE, 2, 4, 16);
		 static final Field roomplant_b = new Field(TEXTURE, 2, 5, 16).setCanGoThrough(false);
		
		// === Fels ===
	
		 static final Field rock_bl = new Field(TEXTURE, 7, 6, 16).setCanGoThrough(false);
		 static final Field rock_br = new Field(TEXTURE, 8, 6, 16).setCanGoThrough(false);
		 static final Field rock_tl = new Field(TEXTURE, 7, 5, 16).setCanGoThrough(false);
		 static final Field rock_tr = new Field(TEXTURE, 8, 5, 16).setCanGoThrough(false);
		
		// === PC-Desk ===
	
		 static final Field pcdesk_bl = new Field(TEXTURE, 0, 6, 16).setCanGoThrough(false).setMessage("Der PC ist gesperrt, du kannst nicht auf den Quellcode zugreifen.");
		 static final Field pcdesk_br = new Field(TEXTURE, 1, 6, 16).setCanGoThrough(false).setMessage("Der PC ist gesperrt, du kannst nicht auf den Quellcode zugreifen.");
		 static final Field pcdesk_tl = new Field(TEXTURE, 0, 5, 16);
		 static final Field pcdesk_tr = new Field(TEXTURE, 1, 5, 16);
		
		// === Sofa ===
	
		 static final Field sofa_bl = new Field(TEXTURE, 0, 8, 16).setCanGoThrough(false);
		 static final Field sofa_br = new Field(TEXTURE, 1, 8, 16).setCanGoThrough(false);
		 static final Field sofa_tl = new Field(TEXTURE, 0, 7, 16);
		 static final Field sofa_tr = new Field(TEXTURE, 1, 7, 16);
		
	// === Wände ===
		
		// === Wand 1 ===

		 static final Field wall1_s = new Field("wall_1/s.png").setCanGoThrough(false);
		 static final Field wall1_l = new Field("wall_1/l.png").setCanGoThrough(false);
		 static final Field wall1_m = new Field("wall_1/m.png").setCanGoThrough(false);
		 static final Field wall1_r = new Field("wall_1/r.png").setCanGoThrough(false);

	
	
	
	Field(String imgPath) {
		setImage(imgPath);		
	}
	
	Field(String imgPath, boolean addToEditor) {
		setImage(imgPath);
		int i;
        if(addToEditor){
			for(i=0; i<Editor.selection.length; i++){
	            if(Editor.selection[i] == null)
	                break;
	        }
	        if(i<Editor.selection.length)
	            Editor.selection[i] = this;
        }
		
	}
	
			
	Field(Texture texture, int x, int y, int height) {
        setImage(texture, x, y, height);
        int i;
        for(i=0; i<Editor.selection.length; i++){
            if(Editor.selection[i] == null)
                break;
        }
        if(i<Editor.selection.length)
            Editor.selection[i] = this;
            
    }
	
			
	Field(Texture texture, int x, int y, int height, boolean addToEditor) {
        setImage(texture, x, y, height);
        if(!addToEditor)
        	return;
        int i;
        for(i=0; i<Editor.selection.length; i++){
            if(Editor.selection[i] == null)
                break;
        }
        if(i<Editor.selection.length)
            Editor.selection[i] = this;
            
    }
	
	Image getImage() {
		return img;
	}
	
	Field setImage(String imgPath) {
		try
		{
			img = ImageIO.read(new File(FIELDS_HOME_DIRECTORY + imgPath));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public Field setImage(Texture texture, int x, int y, int height) {
		img = new BufferedImage(16, height, BufferedImage.TRANSLUCENT);
		img.getGraphics().drawImage(texture.getImage(), -16 * x, -16 * y, null);
		return this;
	}
	
	double getWalkThroughSpeed()
	{
		return this.walkThroughSpeed;
	}
	
	Field setWalkThroughSpeed(double walkThroughSpeed)
	{
		this.walkThroughSpeed = walkThroughSpeed;
		return this;
	}
	
	boolean isCanGoThrough() {
		return this.canGoThrough;
	}
	
	Field setCanGoThrough(boolean canGoThrough)
	{
		this.canGoThrough = canGoThrough;
		return this;
	}
	
	String getMessage() {
		return this.message;
	}
	
	Field setMessage(String message)
	{
		this.message = message;
		return this;
	}	

	private void switchDialogNr() {
		this.dialogNr = (this.dialogNr + 1) % 2;
	}
	
	static void load(Main mainObj)
	{
		main = mainObj;
	}
	
	void onSpacePressed()
	{
		if(message == null)
			return;
		
		SoundPlayer.soundBeep.play();
		this.switchDialogNr();
		main.drawMap(null);
		
		if(dialogNr == 1)
		{
			main.drawMap(null);
			main.printMonolog(message);
			main.setWalkingEnabled(false);
		}
		else
		{
			main.setWalkingEnabled(true);
			main.drawMap(null);
		}
	}
	
	void onWalkOn()
	{
		
	}
}