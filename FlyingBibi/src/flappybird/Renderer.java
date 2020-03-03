package flappybird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Renderer extends JPanel{
	
	ImageLoader il = new ImageLoader();
	
	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		
		if (Variablen.started) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(ImageLoader.img1, Variablen.backgroundX1, 0, 700, 700, null);
			g.drawImage(ImageLoader.ibird,Variablen.birdx, Variablen.birdy, 50, 40, null);
			repaint();
			Variablen.flappybird.repaint(g);
		}
		else if(!Variablen.started) {
			Menu.render(g);
		}
	}
	
}
