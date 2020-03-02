package flappybird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Renderer extends JPanel{
	
	ImageLoader il = new ImageLoader();
	
	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(il.img1, FlappyBird.backgroundX1, 0, 700, 700, null);
		g.drawImage(il.img2, FlappyBird.backgroundX2, 0, 700, 700, null);
		g.drawImage(il.ibird,FlappyBird.birdx, FlappyBird.birdy, 50, 40, null);
		repaint();
		FlappyBird.flappybird.repaint(g);
	}
	
}
