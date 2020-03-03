package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public static Rectangle playButtonW1 = new Rectangle(200, 450, 320, 80);
	public static Rectangle playButtonW2 = new Rectangle(700, 450, 320, 80);
	//public static Rectangle helpButton = new Rectangle(535, 580, 150, 60);
		
	public static void render(Graphics g) {
		
				
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, 1200, 700);
		
		g.drawImage(ImageLoader.img1, 200, 250, 320, 200, null);
		g.drawImage(ImageLoader.img2, 700, 250, 320, 200, null);
		g.drawImage(ImageLoader.ibird, 780, 290, 150, 120, null);
		g.drawImage(ImageLoader.ibird, 280, 290, 150, 120,null);
		
		Font fnt0 = new Font("arial", Font.BOLD, 150);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Flappy Bird", 195, 160);
				
		Font fnt1 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt1);
		g.drawString("Water World", playButtonW1.x+10, playButtonW1.y+55);
		g.drawString("Space World", playButtonW2.x+10, playButtonW2.y+55);
		//g.drawString("Help", helpButton.x+20, helpButton.y+45);
		g2d.draw(playButtonW1);
		g2d.draw(playButtonW2);
		//g2d.draw(helpButton);
		
		Font fnt2 = new Font("arial", Font.BOLD, 12);
		g.setFont(fnt2);
		g.drawString("Willkommen! - Klicke auf den Button der Welt, in welcher Du spielen möchtest. Um deinen Bird springen zu lassen, drücke die Leertaste.", 200, 570);
		g.drawString("Bewege den Bird zwischen den Rohren hindurch, um den Score zu erhöhen. Berührt der Bird dabei allerdings ein Rohr, den oberen oder den", 200, 590);
		g.drawString("unteren Spielrahmen, ist das Spiel beendet. Um das Spiel erneut zu starten, drücke die Leertaste. Wenn Du ins Menü zurückkehren möchtest,", 200, 610);
		g.drawString("klicke auf den roten Button in der rechten oberen Ecke. Viel Erfolg und guten Flug!", 200, 630);
		
		g.setColor(Color.red);
		g.fillOval(1145, 15, 30, 30);
		
		
	}
	
}
