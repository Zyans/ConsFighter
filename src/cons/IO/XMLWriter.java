package cons.IO;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import cons.Map;

public class XMLWriter {
	
	public void writeXMLFile(Map map, String directory){
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(directory));
			encoder.writeObject(map);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
}