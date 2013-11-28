package cons;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Player {
	
	Image[] img = new Image[12]; // Einzelbilder
	static Main main;
	int xPos; 						
	int yPos; 						
	int lookDirection; 				// Blickrichtung
	int walkDirection = 5;          // Laufrichtung
	int stepX; 						// Bruchteil des Schrittes der X-Position
	int stepY; 						// Bruchteil des Schrittes der Y-Position
	Map place; 						// Map, auf der sich der Spieler befindet
	Fighter fighter;
	int mapId;
	
	
	Player(int xPos, int yPos, Map place, Main mainObj)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.place = place;
		main = mainObj;
		
		Point[] construction = new Point[]
		{
			new Point(4, 2, PointType.head, 10),
			new Point(5, 8, PointType.leftArm, 15),
			new Point(10, 8, PointType.rightArm, 15),
			new Point(6, 9, PointType.leftLeg, 18),
			new Point(8, 9, PointType.rightLeg, 18)
		};
		Attack[] attacks = new Attack[]
		{
			new Attack(AttackType.biss, 10),
			new Attack(AttackType.kinnhaken, 5),
			new Attack(AttackType.linkerHaken, 6),
			null
		};
		fighter = new Fighter(construction, 100, attacks, main);
		
		load();
				
	}

	/** Lässt den Spieler in Richtung walkDirection laufen */
	void walk()
	{
		switch(walkDirection)
		{
			case 0: walkOnY(-1); 
					break;
			case 1: walkOnX(1); 
					break;
			case 2: walkOnY(1); 
					break;
			case 3: walkOnX(-1); 
					break;
		}
	} 
	
	/** legt die Map des Players fest */
	void setOnMap(Map map, int xPos, int yPos)
	{
		if(map.getSoundBackground() != null && !map.getSoundBackground().equals(place.getSoundBackground()))
		{
			place.getSoundBackground().stop();
			map.getSoundBackground().play();
		}
		this.place = map;
		this.xPos = xPos;
		this.yPos = yPos;
		this.place.drawPartOfImage(main, xPos - 2, yPos - 2, xPos + 2, yPos + 2);
	}

	/** Lässt den Players nach links/rechts laufen */
	void walkOnX(int length)
	{
		if(main.isWalkingEnabled())
		{
			if(length > 0)
			{
				lookDirection = 1;
				if(xPos <= place.getWidth() - 2)
				{
					Field[] field = place.getFields()[xPos+1][yPos];
					if((field[0] != null && field[0].isCanGoThrough()) && (field[1] == null || field[1].isCanGoThrough()) && (field[2] == null || field[2].isCanGoThrough()) && place.getPersons()[xPos+1][yPos] == null)
				    {
			        	for(stepX = 0; stepX > -16; stepX--)
			        	{
			        		main.drawMap();
			        	}
			        	stepX = 0;
				    	xPos++;
				    	field[0].onWalkOn();
				    }
				}
			}
			else
			{
				lookDirection = 3;
				if(xPos >= 1)
				{
					Field[] field = place.getFields()[xPos-1][yPos];
					if((field[0] != null && field[0].isCanGoThrough()) && (field[1] == null || field[1].isCanGoThrough()) && (field[2] == null || field[2].isCanGoThrough()) && place.getPersons()[xPos-1][yPos] == null)
					{
			        	for(stepX = 0; stepX < 16; stepX++)
			        	{
			        		main.drawMap();
			        	}
			        	stepX = 0;
				    	xPos--;
				    	field[0].onWalkOn();
				    }
				}
			}
	       	main.drawMap();
		}
	}
	
	/** Lässt den Players nach oben/unten laufen */
	void walkOnY(int length)
	{
		if(main.isWalkingEnabled())
		{
			if(length > 0)
			{
				lookDirection = 2;
				if(yPos <= place.getHeight() - 2)
				{
					Field[] field = place.getFields()[xPos][yPos+1];
					if((field[0] != null && field[0].isCanGoThrough()) && (field[1] == null || field[1].isCanGoThrough()) && (field[2] == null || field[2].isCanGoThrough()) && place.getPersons()[xPos][yPos+1] == null)
					{
			        	for(stepY = 0; stepY > -16; stepY--)
			        	{
			        		main.drawMap();
			        	}
			        	stepY = 0;
				    	yPos++;
				    	field[0].onWalkOn();
				    }
				}
			}
			else
			{
				lookDirection = 0;
				if(yPos >= 1)
				{
					Field[] field = place.getFields()[xPos][yPos-1];
					if((field[0] != null && field[0].isCanGoThrough()) && (field[1] == null || field[1].isCanGoThrough()) && (field[2] == null || field[2].isCanGoThrough()) && place.getPersons()[xPos][yPos-1] == null)
					{
			        	for(stepY = 0; stepY < 16; stepY++)
			        	{
			        		main.drawMap();
			        	}
			        	stepY = 0;
				    	yPos--;
				    	field[0].onWalkOn();
				    }
				}
			}
	       	main.drawMap();
		}
	}
	
	/** Lädt alle Einzelbilder des Players */
	void load()
	{
		int nr = 7;
		
		try
		{
			img[0] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/t1.png"));
			img[1] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/t2.png"));
			img[2] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/t3.png"));
			img[3] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/r1.png"));
			img[4] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/r2.png"));
			img[5] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/r3.png"));
			img[6] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/b1.png"));
			img[7] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/b2.png"));
			img[8] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/b3.png"));
			img[9] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/l1.png"));
			img[10] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/l2.png"));
			img[11] = ImageIO.read(new File("src/imgs/persons/person" + nr + "/l3.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/** Returnt Bild des Players abhängig von Blickrichtung und Laufzustand */
	Image getPlayerImage()
	{
		if(Math.abs(stepY + stepX) / 4 < 1)
			return img[lookDirection * 3];
		else if(Math.abs(stepY + stepX) / 4 < 2)
			return img[lookDirection * 3 + 1];
		else if(Math.abs(stepY + stepX) / 4 < 3)
			return img[lookDirection * 3];
		else
			return img[lookDirection * 3 + 2];
	}
}