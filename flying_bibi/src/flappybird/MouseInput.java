/**
 * Projekt: My Flappy
 * @author Group1: Miriam Bucken, Biana Eichentopf, Vincent Gottschling, Rebecca Marzahn
 * @version V1 08.03.2020
 */

package flappybird;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	/**
	 * Methode mouseClicked steuert bei Klicken der Maus in bestimmtem Menübereich der jeweiligen Buttons die Auswahl der Welt durch das Setzen der entsprechenden Weltenvariable
	 */
	public void mouseClicked(MouseEvent e) {
		
		//definiert die Koordinaten des Mausklicks
		int mx = e.getX();
		int my = e.getY();
		
		//Prüfung der Koordinaten des Mausklicks
		if(mx >= 50 && mx <= 350) {
			//Setzen der Weltenvariablen bei Klick in bestimmtem Menübereich
			//Setzen der Spielvariablen auf Start, GameOver auf false
			if(my >= 420 && my <= 500) {
				//PlayW1
				Variablen.welt = 1;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
		
		if(mx >= 450 && mx <= 750) {
			if(my >= 420 && my <= 500) {
				//PlayW2
				Variablen.welt = 2;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
		if(mx >= 850 && mx <= 1150) {
			if(my >= 420 && my <= 500) {
				//PlayW3
				Variablen.welt = 3;
				Variablen.gameOver = false;
				Variablen.started= true;
				Variablen.start = true;
			}
		}
		
		//Klick auf den Menübutton setzt Spielvariablen auf false		
		if (mx >= 1100 && mx <= 1200) {
			if(my >= 0 && my <= 60) {
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
