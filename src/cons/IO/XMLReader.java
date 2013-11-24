package cons.IO;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cons.Map;

public class XMLReader {
	
	public Map readXMLFile(String directory){
		Map map = null;
		try {
			XMLDecoder encoder = new XMLDecoder(new FileInputStream(directory));
			map = (Map) encoder.readObject();
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			return map != null ? map : new Map(0, 0, null);
	}
}