package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public static Rectangle playButtonW1 = new Rectangle(50, 420, 300, 80);
	public static Rectangle playButtonW2 = new Rectangle(450, 420, 300, 80);
	public static Rectangle playButtonW3 = new Rectangle(850, 420, 300, 80);
		
	public static void render(Graphics g) {
		
				
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, 1200, 700);
		
		g.drawImage(ImageLoader.img1, 50, 220, 300, 200, null);
		g.drawImage(ImageLoader.img2, 450, 220, 300, 200, null);
		g.drawImage(ImageLoader.img3, 850, 220, 300, 200, null);
		g.drawImage(ImageLoader.ibird, 130, 260, 150, 120, null);
		g.drawImage(ImageLoader.ibird, 530, 260, 150, 120, null);
		g.drawImage(ImageLoader.ibird, 930, 260, 150, 120,null);
		
		Font fnt0 = new Font("arial", Font.BOLD, 100);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Flappy Bird Reloaded", 80, 120);
				
		Font fnt1 = new Font("arial", Font.BOLD, 40);
		g.setFont(fnt1);
		g.drawString("Water World", playButtonW1.x+30, playButtonW1.y+55);
		g.drawString("Classic World", playButtonW2.x+15, playButtonW2.y+55);
		g.drawString("Space World", playButtonW3.x+30, playButtonW3.y+55);
		g2d.draw(playButtonW1);
		g2d.draw(playButtonW2);
		g2d.draw(playButtonW3);
		
		Font fnt2 = new Font("arial", Font.BOLD, 15);
		g.setFont(fnt2);
		g.drawString("Willkommen! - Klicke auf den Button der Welt, in welcher Du spielen möchtest. Um deinen Bird springen zu lassen, drücke die Leertaste.", 50, 550);
		g.drawString("Bewege den Bird zwischen den Rohren hindurch, um den Score zu erhöhen. Berührt der Bird dabei allerdings ein Rohr, den oberen oder den unteren", 50, 570);
		g.drawString("Spielrahmen, ist das Spiel beendet. Um das Spiel erneut zu starten, drücke die Leertaste. Wenn Du ins Menü zurückkehren möchtest, klicke auf den", 50, 590);
		g.drawString("roten Button in der rechten oberen Ecke. Viel Erfolg und guten Flug!", 50, 610);
		
		g.setColor(Color.red);
		g.fillOval(1145, 15, 30, 30);
		
		
	}
	
}
