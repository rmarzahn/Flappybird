package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Renderer extends JPanel{
	
	ImageLoader il = new ImageLoader();
	
	private static final long serialVersionUID = 1L;
	
	//Funktion paintComponent der Mutterklasse wird aufgerufen
	protected void paintComponent(Graphics g) {
			
		//nur components werden im jframe angezeigt
		if (Variablen.started && Variablen.start) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (Variablen.welt == 1) {
				g.drawImage(ImageLoader.img1, Variablen.backgroundX1, 0, 1200, 700, null);
				Variablen.flappybird.repaint(g);
				g.drawImage(ImageLoader.ibird,Variablen.bird.x-5, Variablen.bird.y-3, 50, 40, null);
				g.setColor(Color.red);
				g.fillOval(1145, 15, 30, 30);
			}
			if (Variablen.welt == 2) {
				g.drawImage(ImageLoader.img2, Variablen.backgroundX1, 0, 1200, 700, null);
				Variablen.flappybird.repaint(g);
				g.drawImage(ImageLoader.ibird,Variablen.bird.x-5, Variablen.bird.y-3, 50, 40, null);
				g.setColor(Color.red);
				g.fillOval(1145, 15, 30, 30);
			}
			if(Variablen.welt == 3) {
				g.drawImage(ImageLoader.img3, Variablen.backgroundX1, 0, 1200, 700, null);
				Variablen.flappybird.repaint(g);
				g.drawImage(ImageLoader.ibird,Variablen.bird.x-5, Variablen.bird.y-3, 50, 40, null);
				g.setColor(Color.red);
				g.fillOval(1145, 15, 30, 30);
			}
			repaint();
			//Variablen.flappybird.repaint(g);
		}
		else if(!Variablen.started) {
			Menu.render(g);
		}
	}
	
}
