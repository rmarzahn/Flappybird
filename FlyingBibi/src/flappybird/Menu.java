package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public static Rectangle playButtonW1 = new Rectangle(70, 200, 550, 150);
	//public static Rectangle playButtonW2 = new Rectangle(70, 400, 550, 150);
	public static Rectangle helpButton = new Rectangle(300, 580, 100, 50);
	
	
	public static void render(Graphics g) {
		
				
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 100);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Flappy Bird", 70, 120);
				
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButtonW1.x+250, playButtonW1.y+35);
		//g.drawString("World 2", playButtonW2.x+70, playButtonW2.y+35);
		g.drawString("Help", helpButton.x+20, helpButton.y+35);
		g2d.draw(playButtonW1);
		//g2d.draw(playButtonW2);
		g2d.draw(helpButton);
		
	}
	
}
