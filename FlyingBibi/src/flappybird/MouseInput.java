package flappybird;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	public void mouseClicked(MouseEvent e) {
				
		int mx = e.getX();
		int my = e.getY();
		
		if(mx >= 200 && mx <= 520) {
			if(my >= 450 && my <= 530) {
				//PlayW1
				System.out.println("ja");
				Variablen.welt = 1;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
		
		if(mx >= 700 && mx <= 1020) {
			if(my >= 450 && my <= 530) {
				//PlayW2
				System.out.println("ja");
				Variablen.welt = 2;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
			

		/**
		 * 
		public static Rectangle playButtonW1 = new Rectangle(200, 450, 320, 80);
		public static Rectangle playButtonW2 = new Rectangle(700, 450, 320, 80);
		public static Rectangle helpButton = new Rectangle(535, 580, 150, 60);
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
