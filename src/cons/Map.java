package cons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Map
{  
	private Person[][] persons;
	private Image img1; // 1. Ebene
	private Image img2; // 2. Ebene
	private Image img3; // 3. Ebene
	private Image imgP; // Personen
	private Image imgPart; // Umgebung Spieler
	private Field[][][] fields;
	private SoundPlayer soundBackground;
	private int height, width;
	
    public Map(int width, int height, SoundPlayer soundBackground)
    {
		this.height = height;
		this.width = width;
		this.soundBackground = soundBackground;
		fields = new Field[width][height][3];
		persons = new Person[width][height];
	}
    
    public Map() {
		this.height = 10;
		this.width = 10;
		this.soundBackground = null;
		fields = new Field[width][height][3];
		persons = new Person[width][height];
    }
  
    void addPerson(Person person)
	{
		persons[person.getxPos()][person.getyPos()] = person;
	}
	  
	void removePerson(Person person)
	{
		persons[person.getxPos()][person.getyPos()] = null;
	}
	
	void setRect(Field field, int x, int y, int z, int width, int height)
	{
		for(int i = x; i < width + x; i++)
		{
			for(int j = y; j < height + y; j++)
			{
				fields[i][j][z] = field;
			}
		}
	}
	
	void setHorizontalPath(Trail trail, int x, int y, int width)
	{
		setRect(trail.t, x+1, y, 0, width, 1);
		setRect(trail.b, x+1, y+1, 0, width, 1);
		setRect(trail.tl, x, y, 0, 1, 1);
		setRect(trail.bl, x, y+1, 0, 1, 1);
		setRect(trail.tr, x+width+1, y, 0, 1, 1);
		setRect(trail.br, x+width+1, y+1, 0, 1, 1);
	}
	
	void setVerticalPath(Trail trail, int x, int y, int height)
	{
		setRect(trail.l, x, y+1, 0, 1, height);
		setRect(trail.r, x+1, y+1, 0, 1, height);
		setRect(trail.tl, x, y, 0, 1, 1);
		setRect(trail.tr, x+1, y, 0, 1, 1);
		setRect(trail.bl, x, y+height, 0, 1, 1);
		setRect(trail.br, x+1, y+height, 0, 1, 1);
	}
	
	public void setMountain(Mountain m, int x, int y, int width, int height)
	{
		setRect(m.t, x + 1, y, 1, width, 1);
		setRect(m.tl, x, y, 1, 1, 1);
		setRect(m.l, x, y + 1, 1, 1, height);
		setRect(m.tr, x + width, y, 1, 1, 1);
		setRect(m.r, x + width, y + 1, 1, 1, height - 1);
		setRect(m.m, x + 1, y + 1, 0, width - 1, height - 1);
		setRect(m.br, x + width, y + height, 1, 1, 1);
		setRect(m.bl, x, y + height, 1, 1, 1);
		setRect(m.b, x + 1, y + height, 1, width - 1, 1);
	}
	
	void setGrassMountain(Mountain m, int x, int y, int width, int height)
	{
		setRect(m.tl, x, y, 1, 1, 1);
		setRect(m.l, x, y + 1, 1, 1, height);
		setRect(m.tr, x + width, y, 1, 1, 1);
		setRect(m.r, x + width, y + 1, 1, 1, height - 1);
		setRect(Field.meadow_light, x + 1, y + 1, 0, width - 1, height - 1);
		setRect(m.br, x + width, y + height, 1, 1, 1);
		setRect(m.bl, x, y + height, 1, 1, 1);
		setRect(m.b, x + 1, y + height, 1, width - 1, 1);
		setRect(m.gras_t, x + 2, y, 1, width - 2, 1);
		setRect(m.gras_tl, x + 1, y, 1, 1, 1);
		setRect(m.gras_tr, x + width - 1, y, 1, 1, 1);
	}
	
	void setFence(Fence fence, int x, int y, int z, int width, int height)
	{
		setRect(fence.tb, x + 1, y, z, width - 1, 1);
		setRect(fence.tb, x + 1, y + height, z, width - 1, 1);
		setRect(fence.tl, x, y, z, 1, 1);
		setRect(fence.l, x, y + 1, z, 1, height - 1);
		setRect(fence.bl, x, y + height, z, 1, 1);
		setRect(fence.tr, x + width, y, z, 1, 1);
		setRect(fence.r, x + width, y + 1, z, 1, height - 1);
		setRect(fence.br, x + width, y + height, z, 1, 1);
	}
	
	public void setFountain(int x, int y)
	{
		fields[x][y][2] = Field.fountain_t1;
		fields[x+1][y][2] = Field.fountain_t2;
		fields[x+2][y][2] = Field.fountain_t3;
		this.setRect(Field.cantGoThrough, x, y-2, 1, 3, 2);
		fields[x][y][1] = Field.fountain_b1;
		fields[x+1][y][1] = Field.fountain_b2;
		fields[x+2][y][1] = Field.fountain_b3;
	}
	
	void setLetterbox(int x, int y)
	{
		fields[x][y][1] = Field.letterbox_b;
		fields[x][y][2] = Field.letterbox_t;
	}
	
	void setHugeGrass(int x, int y, int width, int height)
	{	
		this.setRect(Field.hugegrass_t, x, y, 2, width, height);
		this.setRect(Field.hugegrass_b, x, y, 1, width, height);
	}
	
	void setRoomplant(int x, int y)
	{
		fields[x][y][1] = Field.roomplant_b;
		fields[x][y][2] = Field.roomplant_t;
	}
	
	void setStatue1(int x, int y)
	{
		fields[x][y][1] = Field.statue1_b;
		fields[x][y][2] = Field.statue1_t;
	}
	
	void setStatue2(int x, int y)
	{
		fields[x][y][1] = Field.statue2_b;
		fields[x][y][2] = Field.statue2_t;
	}
	
	void setShip(int x, int y)
	{
		fields[x][y][1] = Field.ship_l;
		fields[x+1][y][1] = Field.ship_r;
	}
	
	void setKühlschrank(int x, int y)
	{
		fields[x][y][1] = Field.kühlschrank_b;
		fields[x][y][2] = Field.kühlschrank_t;
	}
	
	void setTree1(int x, int y)
	{
		fields[x][y][1] = Field.tree1_b;
		fields[x][y][2] = Field.tree1_t;
	}
	
	void setTree2(int x, int y)
	{
		fields[x][y][1] = Field.tree2_b1;
		fields[x+1][y][1] = Field.tree2_b2;
		fields[x][y][2] = Field.tree2_t1;
		fields[x+1][y][2] = Field.tree2_t2;
	}
	
	void setHouse(House h, int x, int y)
	{
		if(h.equals(House.house7))
		{
			for(int i = 0; i < 3; i++)
			{
				fields[x+i][y][1] = h.getB()[i];
				fields[x+i][y][2] = h.getT()[i];
			}
			for(int i = 0; i < 4; i++)
			{
				fields[x+i+3][y+1][1] = h.getB()[i+3];
				fields[x+i+3][y+1][2] = h.getT()[i+3];
			}
			for(int i = 0; i < 3; i++)
			{
				fields[x+i+7][y][1] = h.getB()[i+7];
				fields[x+i+7][y][2] = h.getT()[i+7];
			}
			return;
		}

		for(int i = 0; i < h.getB().length; i++)
		{
			fields[x+i][y][1] = h.getB()[i];
			fields[x+i][y][2] = h.getT()[i];
		}
		setRect(Field.cantGoThrough, x, y-h.getDepth(), 1, h.getB().length, h.getDepth());
	}
	
	void setWater(int x, int y, int width, int height)
	{
		setRect(Field.water_tl, x, y, 0, 1, 1);
		setRect(Field.water_t, x+1, y, 0, width - 2, 1);
		setRect(Field.water_tr, x + width - 1, y, 0, 1, 1);
		setRect(Field.water_l, x, y+1, 0, 1, height - 2);
		setRect(Field.water_bl, x, y + height - 1, 0, 1, 1);
		setRect(Field.water_b, x+1, y + height - 1, 0, width-2, 1);
		setRect(Field.water_br, x + width - 1, y + height - 1, 0, 1, 1);
		setRect(Field.water_r, x + width - 1, y+1, 0, 1, height - 2);
		setRect(Field.water_m, x + 1, y + 1, 0, width - 2, height - 2);
	}
	
	void setIsland(Field fill, int x, int y, int width, int height)
	{
		setRect(Field.island_tl, x, y, 0, 1, 1);
		setRect(Field.island_t, x+1, y, 0, width - 2, 1);
		setRect(Field.island_tr, x + width - 1, y, 0, 1, 1);
		setRect(Field.island_l, x, y+1, 0, 1, height - 2);
		setRect(Field.island_bl, x, y + height - 1, 0, 1, 1);
		setRect(Field.island_b, x+1, y + height - 1, 0, width-2, 1);
		setRect(Field.island_br, x + width - 1, y + height - 1, 0, 1, 1);
		setRect(Field.island_r, x + width - 1, y+1, 0, 1, height - 2);
		setRect(fill, x + 1, y + 1, 0, width - 2, height - 2);
	}
	
	void setRedCouch(int x, int y)
	{
		fields[x][y][1] = Field.redcouch_t;
		fields[x][y+1][1] = Field.redcouch_b;
	}
	
	void setRock(int x, int y)
	{
		fields[x][y][1] = Field.rock_bl;
		fields[x+1][y][1] = Field.rock_br;
		fields[x][y][2] = Field.rock_tl;
		fields[x+1][y][2] = Field.rock_tr;
	}
	
	void setPcdesk(int x, int y)
	{
		fields[x][y][1] = Field.pcdesk_bl;
		fields[x+1][y][1] = Field.pcdesk_br;
		fields[x][y][2] = Field.pcdesk_tl;
		fields[x+1][y][2] = Field.pcdesk_tr;
	}
	
	void setSofa(int x, int y)
	{
		fields[x][y][1] = Field.sofa_bl;
		fields[x+1][y][1] = Field.sofa_br;
		fields[x][y][2] = Field.sofa_tl;
		fields[x+1][y][2] = Field.sofa_tr;
	}
	
	void drawImage(Main main)
	{
		img1 = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);
		img2 = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);
		imgP = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);
		img3 = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);

		Graphics g1 = img1.getGraphics();
		Graphics g2 = img2.getGraphics();
		Graphics gP = imgP.getGraphics();
		Graphics g3 = img3.getGraphics();
		g3.setColor(new Color(20, 20, 20));
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				if(fields[x][y][0]!=null && main.showLevel1)
					g1.drawImage(fields[x][y][0].getImage(), x * 16 + 200, y * 16 + 200, main);
				if(fields[x][y][1]!=null && main.showLevel2)
					g2.drawImage(fields[x][y][1].getImage(), x * 16 + 200, y * 16 + 200, main);
				if(persons[x][y]!=null && main.showPersons)
					gP.drawImage(persons[x][y].getImage(), x * 16 + 200 - persons[x][y].getStepX() + 14 - persons[x][y].getImage().getWidth(main), y * 16 + 211 - persons[x][y].getStepY() - persons[x][y].getImage().getHeight(main), main);
				if(fields[x][y][2]!=null && main.showLevel3)
					g3.drawImage(fields[x][y][2].getImage(), x * 16 + 200, y * 16 + 200 - fields[x][y][2].getImage().getHeight(main), main);
			}
		}

		if(main.showGrid) {
			for(int x = 0; x < width; x++)
				g3.drawLine(199 + x * 16, 200, 199+x * 16, height * 16 + 200);
			for(int y = 0; y < height; y++)
				g3.drawLine(200, 200 + y * 16, width * 16 + 200, 200 + y * 16);
		}
		
		main.drawMap(null);
	}
	
	void drawImage1(Main main)
	{
		img1 = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);

		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				if(fields[x][y][0]!=null)
					img1.getGraphics().drawImage(fields[x][y][0].getImage(), x * 16 + 200, y * 16 + 200, main);
			}
		}
	}
	
	void drawImage2(Main main)
	{
		img2 = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);

		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				if(fields[x][y][1]!=null)
					img2.getGraphics().drawImage(fields[x][y][1].getImage(), x * 16 + 200, y * 16 + 200, main);
			}
		}
	}
	
	void drawImageP(Main main)
	{
		imgP = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);

		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				if(persons[x][y]!=null)
					imgP.getGraphics().drawImage(persons[x][y].getImage(), x * 16 + 200 - persons[x][y].getStepX() + 14 - persons[x][y].getImage().getWidth(main), y * 16 + 211 - persons[x][y].getStepY() - persons[x][y].getImage().getHeight(main), main);
			}
		}
	}
	
	void drawPartOfImage(Main main, int x1, int y1, int x2, int y2)
	{
		Image partOfImg = new BufferedImage((x2 - x1) * 16, (y2 - y1) * 16, BufferedImage.TRANSLUCENT);
		imgPart = new BufferedImage(width * 16 + 200, height * 16 + 200, BufferedImage.TRANSLUCENT);

		Graphics g = partOfImg.getGraphics();
		g.setColor(new Color(20, 20, 20));

		int rx1 = x1;
		int ry1 = y1;
		int rx2 = x2;
		int ry2 = y2;
		
		if(rx2 > main.getPlayer().place.width - 1) rx2 = main.getPlayer().place.width - 1;

		rx1--;
		ry1--;

		if(main.showLevel1)
		for(int y = ry1; y < ry2 - 1; y++)
		{
			for(int x = rx1; x < rx2 - 1; x++)
			{
				if(y >= 0 && x >= 0 && x < fields.length && y < fields[x].length && fields[x][y][0]!=null)
					g.drawImage(fields[x][y][0].getImage(), (x - rx1) * 16, (y - ry2 + 5) * 16, main);
			}
		}

		for(int y = ry1; y < ry2 - 1; y++)
		{
			for(int x = rx1; x < rx2 - 1; x++)
			{
				if(main.showPersons && y >= 0 && x >= 0 && x < persons.length && y < persons[x].length && persons[x][y]!=null)
					g.drawImage(persons[x][y].getImage(), (x - rx1) * 16 - persons[x][y].stepX + 14 - persons[x][y].getImage().getWidth(main), (y - ry1) * 16 - persons[x][y].stepY + 11 - persons[x][y].getImage().getHeight(main), main);
				if(main.showLevel2 && y >= 0 && x >= 0 && x < fields.length && y < fields[x].length && fields[x][y][1]!=null)
					g.drawImage(fields[x][y][1].getImage(), (x - rx1) * 16, (y - ry2 + 5) * 16, main);
				if(main.showLevel3 && y >= 0 && x >= 0 && x < fields.length && y < fields[x].length && fields[x][y][2]!=null)
					g.drawImage(fields[x][y][2].getImage(), (x - rx1) * 16, (y - ry2 + 5) * 16 - fields[x][y][2].getImage().getHeight(main), main);
			}
			
			if(y >= 0 && y == main.getPlayer().yPos)
			{
				Image pimg = main.getPlayer().getPlayerImage();
				g.drawImage(pimg, (main.getPlayer().xPos - x1 + 1) * 16 - main.getPlayer().stepX + 14 - pimg.getWidth(main), (y - y1 + 1) * 16 - main.getPlayer().stepY + 11 - pimg.getHeight(main), main);
			}
		}

		if(main.showPersons)
		for(int x = rx1; x < rx2 - 1; x++)
		{
			for(int y = ry2 - 1; y < ry2 && x >= 0 && y < persons[x].length; y++)
			{
				if(y >= 0 && x >= 0 && x < persons.length && y < persons[x].length && persons[x][y]!=null)
					g.drawImage(persons[x][y].getImage(), (x - rx1) * 16 - persons[x][y].stepX + 14 - persons[x][y].getImage().getWidth(main), (y - ry1) * 16 - persons[x][y].stepY + 11 - persons[x][y].getImage().getHeight(main), main);
			}
		}

		if(main.showLevel3)
		for(int x = rx1; x < rx2 - 1; x++)
		{
			for(int y = ry2 - 1; y < ry2 + 10 && x >= 0 && x < fields.length && y < fields[x].length; y++)
			{
				if(y >= 0 && x >= 0 && x < fields.length && y < fields[x].length && fields[x][y][2]!=null)
					g.drawImage(fields[x][y][2].getImage(), (x - rx1) * 16, (y - ry2 + 5) * 16 - fields[x][y][2].getImage().getHeight(main), main);
			}
		}
		
		if(main.showGrid) {
			for(int y = 0; y < 5; y++)
				g.drawLine(-1, y * 16, width * 16-1, y * 16);
			for(int x = 0; x < 5; x++)
				g.drawLine(x * 16-1, 0, x * 16-1, height * 16);
		}

		imgPart.getGraphics().drawImage(partOfImg, x1 * 16 + 184, y1 * 16 + 184, main);
	}

	
	Person[][] getPersons() {
		return persons;
	}

	void setPersons(Person[][] persons) {
		this.persons = persons;
	}

	Image getImg1() {
		return img1;
	}

	void setImg1(Image img1) {
		this.img1 = img1;
	}

	Image getImg2() {
		return img2;
	}

	void setImg2(Image img2) {
		this.img2 = img2;
	}

	Image getImgP() {
		return imgP;
	}

	void setImgP(Image imgP) {
		this.imgP = imgP;
	}

	Image getImg3() {
		return img3;
	}

	void setImg3(Image img3) {
		this.img3 = img3;
	}

	Image getImgPart() {
		return imgPart;
	}

	void setImgP2(Image imgPart) {
		this.imgPart = imgPart;
	}

	Field[][][] getFields() {
		return fields;
	}

	void setFields(Field[][][] fields) {
		this.fields = fields;
	}

	SoundPlayer getSoundBackground() {
		return soundBackground;
	}

	void setSoundBackground(SoundPlayer background) {
		this.soundBackground = background;
	}

	int getHeight() {
		return height;
	}

	void setHeight(int height) {
		this.height = height;
	}

	int getWidth() {
		return width;
	}

	void setWidth(int width) {
		this.width = width;
	}
}