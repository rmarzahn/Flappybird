package flappybird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	static BufferedImage img1, img2;
	static BufferedImage ibird;
	
	public ImageLoader() {
		try {
			img1 = ImageIO.read(new File("res/bg1.png"));
			img2 = ImageIO.read(new File("res/bg2.png"));
			ibird = ImageIO.read(new File("res/bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Bilder können nicht geladen werden!");
		}
	}
	

}
