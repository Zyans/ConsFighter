package cons;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Texture {
	
	Image image;
	final String path;
	static final String HOME_DIRECTORY = "src/imgs/textures/";
	
	Texture(String path) {
		this.path = path;
		
		try {
			image = ImageIO.read(new File(HOME_DIRECTORY + path));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Image getImage() {
		return image;
	}
	
	String getPath() {
		return path;
	}
}