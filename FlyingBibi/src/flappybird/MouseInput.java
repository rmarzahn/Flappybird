package flappybird;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	public void mousePressed(MouseEvent e) {
		
		System.out.println("nein");
		
		int mx = e.getX();
		int my = e.getY();
		
		if(mx >= 70 && mx <= 620) {
			if(my >= 200 && my <= 350) {
				//PlayW1
				System.out.println("ja");
				Variablen.started= true;
				Variablen.start = true;
			}
		}
			
		/**
	 	public static Rectangle playButtonW1 = new Rectangle(70, 200, 550, 150);
		public static Rectangle playButtonW2 = new Rectangle(70, 400, 550, 150);
		public static Rectangle helpButton = new Rectangle(300, 580, 100, 50)
		*/
	}	

	public void mouseClicked(MouseEvent e) {
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
