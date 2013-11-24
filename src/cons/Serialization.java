package cons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import cons.Main;

class Serialization
{
	private ObjectOutputStream oos;
	private OutputStream fos = null;	
	//System.getProperty("java.io.tmpdir"): Temp-Verzeichnis des aktuellen Nutzers
	private static String serLocation = System.getProperty("java.io.tmpdir") + "serData.ser.txt";
	private static File serFile = new File(serLocation);
	private final Main main;
	
	Serialization(final Main main)
	{
		this.main = main;
	}
	
	void serialize(){
		
		final boolean isEmpty = serFile.length() == 0;
		
		if(!serFile.exists()) {
			try {
				serFile.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
			try {
				fos = new FileOutputStream(serLocation);
				oos = new ObjectOutputStream(fos);

				oos.writeInt(main.getPlayer().xPos);
				oos.writeInt(main.getPlayer().yPos);
				oos.writeInt(main.getPlayer().lookDirection);
				oos.writeInt(main.getPlayer().mapId);
				
				oos.close();
				
				System.out.println("Serialisierung erfolgreich!");
				
			} catch(final IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch(final Exception ex){
					ex.printStackTrace();
				}
			}
		} 
}