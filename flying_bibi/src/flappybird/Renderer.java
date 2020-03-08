/**
 * Projekt: My Flappy
 * @author Group1: Miriam Bucken, Biana Eichentopf, Vincent Gottschling, Rebecca Marzahn
 * @version V1 08.03.2020
 */

package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Renderer extends JPanel{
	
	//definiert einen neuen ImageLoader zur Einlesung der Bilder
	ImageLoader il = new ImageLoader();
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Methode paintComponent zeichnet die Objekte g der Klasse Graphics
	 */
	protected void paintComponent(Graphics g) {
			
		//Prüfung auf Spielstatus
		//wenn das Spiel gestartet ist, wird Spiel mit entsprechenden Angaben geladen
		if (Variablen.started && Variablen.start) {
			//zeichnen der Components g
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
								 RenderingHints.VALUE_ANTIALIAS_ON);
			//Prüfung der Weltenauswahl und zeichnen des entsprechenden Hintergrunds, Spielfigur und Menübutton
			//Positionierung des Spielfigurbildes auf der vorher definierten Spielfigur
			if (Variablen.welt == 1) {
				g.drawImage(ImageLoader.img1,
				Variablen.backgroundX1,0, 1200, 700, null);
				Variablen.flappybird.repaint(g);
				g.drawImage(ImageLoader.ibird,
				Variablen.bird.x-5, Variablen.bird.y-3, 50, 40, null);
				g.setColor(Color.red);
				g.fillOval(1145, 15, 30, 30);
			}
			if (Variablen.welt == 2) {
				g.drawImage(ImageLoader.img2,
				Variablen.backgroundX1, 0, 1200, 700, null);
				Variablen.flappybird.repaint(g);
				g.drawImage(ImageLoader.ibird,
				Variablen.bird.x-5, Variablen.bird.y-3, 50, 40, null);
				g.setColor(Color.red);
				g.fillOval(1145, 15, 30, 30);
			}
			if(Variablen.welt == 3) {
				g.drawImage(ImageLoader.img3, 
				Variablen.backgroundX1, 0, 1200, 700, null);
				Variablen.flappybird.repaint(g);
				g.drawImage(ImageLoader.ibird, 
				Variablen.bird.x-5, Variablen.bird.y-3, 50, 40, null);
				g.setColor(Color.red);
				g.fillOval(1145, 15, 30, 30);
			}
			repaint();
		}
		
		//wenn Spiel nicht gestartet ist, wird das Menü geladen
		else if(!Variablen.started) {
			Menu.render(g);
		}
	}
	
}
