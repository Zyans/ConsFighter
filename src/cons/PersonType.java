package cons;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class PersonType
{
	 private Image[] img = new Image[12];
	 private static PersonType[] type = new PersonType[11];
	
	PersonType(String parPath)
	{
		try
		{
			img[0] = ImageIO.read(new File(parPath + "t1.png"));
			img[1] = ImageIO.read(new File(parPath + "t2.png"));
			img[2] = ImageIO.read(new File(parPath + "t3.png"));
			img[3] = ImageIO.read(new File(parPath + "r1.png"));
			img[4] = ImageIO.read(new File(parPath + "r2.png"));
			img[5] = ImageIO.read(new File(parPath + "r3.png"));
			img[6] = ImageIO.read(new File(parPath + "b1.png"));
			img[7] = ImageIO.read(new File(parPath + "b2.png"));
			img[8] = ImageIO.read(new File(parPath + "b3.png"));
			img[9] = ImageIO.read(new File(parPath + "l1.png"));
			img[10] = ImageIO.read(new File(parPath + "l2.png"));
			img[11] = ImageIO.read(new File(parPath + "l3.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	static void load()
	{
		/*  
		 * 00 Alter Mann
		 * 01 Alte Frau
		 * 02 Kapitän
		 * 03 Mädchen
		 * 04 Programmierer
		 * 05 Professor
		 * 06 Nerd
		 * 07 Kind
		 * 08 junge Frau
		 * 09 Polizist
		 */
		
		for(int i = 0; i < type.length; i++)
		{
			type[i] = new PersonType("imgs/persons/person" + (i+1) + "/");
		}
	}

	public Image[] getImg() {
		return img;
	}

	public void setImg(Image[] img) {
		this.img = img;
	}

	public static PersonType[] getType() {
		return type;
	}

	public static void setType(PersonType[] type) {
		PersonType.type = type;
	}
}