package flying_bibi;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel{
	private static final long serialVersionUID=1L;
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		flyingBibi.flyingBibi.repaint(g);
	}
}
//Nur ein kleiner Git Test
