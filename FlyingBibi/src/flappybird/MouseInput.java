package flappybird;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	public void mouseClicked(MouseEvent e) {
				
		int mx = e.getX();
		int my = e.getY();
		
		if(mx >= 50 && mx <= 350) {
			if(my >= 420 && my <= 500) {
				//PlayW1
				System.out.println("ja");
				Variablen.welt = 1;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
		
		if(mx >= 450 && mx <= 750) {
			if(my >= 420 && my <= 500) {
				//PlayW2
				System.out.println("ja");
				Variablen.welt = 2;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
		if(mx >= 850 && mx <= 1150) {
			if(my >= 420 && my <= 500) {
				//PlayW3
				System.out.println("ja");
				Variablen.welt = 3;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
			

		/**
		 * 
		public static Rectangle playButtonW1 = new Rectangle(50, 420, 300, 80);
		public static Rectangle playButtonW2 = new Rectangle(450, 420, 300, 80);
		public static Rectangle playButtonW3 = new Rectangle(850, 420, 300, 80);
		*/
		
		if (mx >= 1100 && mx <= 1200) {
			if(my >= 0 && my <= 60) {
				System.out.println("Ende");
				Variablen.gameOver = false;
				Variablen.started = false;
				Variablen.start = false;
			}
		}
	}	

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
