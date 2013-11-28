package cons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

class Deserialization {

	private ObjectInputStream ois;
	private InputStream fis = null;
	//System.getProperty("java.io.tmpdir"): Temp-Verzeichnis des aktuellen Nutzers
	private static String serLocation = System.getProperty("java.io.tmpdir") + "serData.ser.txt";
	private static File serFile = new File(serLocation);
	private final Main main;
	
	Deserialization(final Main main)
	{
		this.main = main;
	}
	
	void deserialize(){
		
		if (!serFile.exists())
			return;
		try
		{
			String text = null;
		    String zeile = null;
			   
		            final BufferedReader bufferedReader = new BufferedReader(new FileReader(serFile));
		            
		            while ((zeile = bufferedReader.readLine()) != null)
		                text += zeile;
		            
		            bufferedReader.close();
		          
		    if (text == "" || text == null)
				return;
		    
			fis = new FileInputStream(serLocation);
			ois = new ObjectInputStream(fis);
			main.getPlayer().xPos = ois.readInt();
			main.getPlayer().yPos = ois.readInt();
			main.getPlayer().lookDirection = ois.readInt();
			main.getPlayer().mapId = ois.readInt();
			
			switch(main.getPlayer().mapId)
			{
				case 0: main.getPlayer().place = MapUtils.getMaps()[0]; break;
				case 1: main.getPlayer().place = MapUtils.getMaps()[1]; break;
				case 2: main.getPlayer().place = MapUtils.getMaps()[2]; break;
				case 3: main.getPlayer().place = MapUtils.getMaps()[3]; break;
				case 4: main.getPlayer().place = MapUtils.getMaps()[4]; break;
				case 5: main.getPlayer().place = MapUtils.getMaps()[5]; break;
				case 6: main.getPlayer().place = MapUtils.getMaps()[6]; break;
				case 7: main.getPlayer().place = MapUtils.getMaps()[7]; break;
				case 8: main.getPlayer().place = MapUtils.getMaps()[8]; break;
				case 9: main.getPlayer().place = MapUtils.getMaps()[9]; break;
			}
			
			main.getPlayer().place.drawImage(main);
			main.drawMap();
			main.drawMenu();
			ois.close();
			System.out.println("Deserialisierung erfolgreich!");
		}
		catch(final IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fis.close();
			}
			catch (final IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
} 