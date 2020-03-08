/**
 * Projekt: My Flappy
 * @author Group1: Miriam Bucken, Biana Eichentopf, Vincent Gottschling, Rebecca Marzahn
 * @version V1 08.03.2020
 */

package flappybird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage img1;
	public static BufferedImage img2;
	public static BufferedImage img3;
	public static BufferedImage ibird;
	
	/**
	 * Methode ImageLoader lädt die für das Spiel benötigten Bilder ein (Hintergrund, Spielfigur), wenn das nicht möglich ist, wird eine Fehlermeldung ausgegeben	
	 */
	public ImageLoader() {
		try {
			img1 = ImageIO.read(new File("res/water.jpg"));
			ibird = ImageIO.read(new File("res/bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Bilder können nicht geladen werden!");
		}			
		
		try {
			img2 = ImageIO.read(new File("res/bg.png"));
			ibird = ImageIO.read(new File("res/bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Bilder können nicht geladen werden!");
		}
		try {
			img3 = ImageIO.read(new File("res/space2.jpeg"));
			ibird = ImageIO.read(new File("res/bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Bilder können nicht geladen werden!");
		}
	}
		
}
	
