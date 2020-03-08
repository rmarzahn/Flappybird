/**
 * Projekt: My Flappy
 * @author Group1: Miriam Bucken, Biana Eichentopf, Vincent Gottschling, Rebecca Marzahn
 * @version V1 08.03.2020
 */

package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, KeyListener{
	
	/**
	 * Methode Flappy Bird ruft die Funktionalitäten des Spiels auf, welche auf Konstruktoraufrufen und Objektaufrufen beruht.
	 */
	public FlappyBird() {
		
		/**
		 * Ausgabefenster des Spiels und Objekte der Klasse JFrame werden erzeugt
		 */
		JFrame jframe = new JFrame();

		/**
		 * Objekt wird alle 20 Millisekunden aufgerufen und sorgt für die Aktualisierungsgeschwindigkeit/Anpassung aller Objekte
		 */
		Timer timer = new Timer(20, this);		
		
		/**
		 * Renderer, Random und Menü Konstruktoren der definierten Klasse werden aufgerufen
		 */
		Variablen.renderer = new Renderer();
		Variablen.rand = new Random();
		Variablen.menu = new Menu();
		
		// Reneder Components werden dem jframe hinzugefügt
		jframe.add(Variablen.renderer);	
		// Titel des jframes wird auf My Flappy gesetzt
		jframe.setTitle("My Flappy");
		// Ausführung des Spiels wird beendet, wenn das Ausgabefenster geschlossen wird
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Höhe und Breite des Ausgabefensters wird definiert
		jframe.setSize(Variablen.WIDTH, Variablen.HEIGHT);
		// Funktionalität der Leertastenbedienung wird im Ausgabefenster hinzugefügt
		jframe.addKeyListener(this);
		// Funktionalität der Mausbedienung wird im Ausgabefenster hinzugefügt
		jframe.addMouseListener(new MouseInput());
		// Größenveränderung des Ausgabefensters wird deaktiviert
		jframe.setResizable(false);
		//Sichtbarkeit des Ausgabefensters wird aktiviert
		jframe.setVisible(true);
		
		
		/**
		 * Spielfigur wird initialisiert 
		 */
		Variablen.bird = new Rectangle(Variablen.birdx, Variablen.birdy, 40, 35);
		/**
		 * Säulen des Spiels werden als dynamische ArrayList initialisiert
		 */
		Variablen.columns = new ArrayList<Rectangle>();
		
		//Vier Anfangssäulen werden initialisiert
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
			
		//Timer wird gestartet
		timer.start();
	}
	
	/**
	 * Methode addColum definiert und setzt neue Säulen im Spiel
	 * @param start setzt neue Säulen, wenn das Spiel aktiv läuft
	 */
	public static void addColumn(boolean start) {
			
			// Höhe, Breite und Abstand der Säulen wird festgelegt
			int space = 300;
			int width = 100;
			int height = 50 + Variablen.rand.nextInt(300);
			
			if (start) {
				// neue obere und untere Säulen werden im festgelegten Abstand und Position hinzugefügt, wenn das Spiel aktiv läuft
				// Säulen sind zu Beginn außerhalb des Bildschirms, alle Säulenpaare mit Abstand 2*300 Pixel
				Variablen.columns.add(new Rectangle(Variablen.WIDTH + width + Variablen.columns.size() * 300, Variablen.HEIGHT - height - 120, width, height));
				Variablen.columns.add(new Rectangle(Variablen.WIDTH + width + (Variablen.columns.size() -1) * 300, 0, width,  Variablen.HEIGHT - height - space));
			}
			else {
				// neue obere und untere Säulen werden im festgelegten Abstand und Position hinzugefügt, wenn das Spiel noch nicht aktiv läuft
				// zur x-Position der letzten Säule wird Abstand 600 addiert
				Variablen.columns.add(new Rectangle(Variablen.columns.get(Variablen.columns.size() - 1).x + 600, Variablen.HEIGHT - height - 120, width, height));
				Variablen.columns.add(new Rectangle(Variablen.columns.get(Variablen.columns.size() - 1).x, 0, width, Variablen.HEIGHT - height - space));
			}
	}
	
	/**
	 * Methode paintColumn zeichnet die Säulen im Spiel
	 * @param g übergibt Objekt g der Klasse Graphics 
	 * @param column beschreibt die zu zeichnende Säule
	 */
	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
		
	}
	
	/**
	 * Methode jump bildet die Funktionalität des Sprungs der Spielfigur ab
	 */
	public static void jump() {
		
		// Prüfung des Spielstatus		
		if (Variablen.gameOver) {
			
			/**
			 * Spielfigur wird initalisert, Säulen und Score zurückgesetzt und Bewegung der Spielfigur unterbrochen
			 */
			Variablen.bird = new Rectangle(Variablen.birdx, Variablen.birdy, 40, 35); 
			Variablen.columns.clear();
			Variablen.yMotion = 0;
			Variablen.score = 0;
			
			// Vier Anfangssäulen werden initialisiert
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			
			Variablen.gameOver = false;
		}
		
		
		if (!Variablen.started) {
			Variablen.started = true;
		}
		else if (!Variablen.gameOver) {
			
			//Spielfigur beibt für einen Frame in der Luft stehen, wewnn sie vor dem Sprung im Fallen war
			if (Variablen.yMotion > 0) {
				Variablen.yMotion = 0;
			}
			//Spielfigur geht um 10 Einheiten nach oben pro Frame (und um speed Einheiten nach rechts)
			Variablen.yMotion -= 10;
		}
	}
	
	
	/**
	 * Methode actionPerformed bildet die Bewegung der Spieligur ab, wenn eine Spielfunktion im aktiven Spiel durch den Spieler ausgeführt wurde und steuert die Geschwindigkeitserhöhung
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (Variablen.start == true) {
			
			if (Variablen.started) {
			
			//Geschwindigkeit der Säulen nach links (Einheiten pro Frame)
			int speed = 8;
			
			//Geschwindigkeitserhöhung bei Erreichen eines bestimmten Scores -> Ausgabe der Erhöhung als String im Ausgabefenster durch speedUp
			for (int i = 0; i < Variablen.columns.size(); i++) {
			
				if (Variablen.score < 20) {
					Rectangle column = Variablen.columns.get(i);
					speed = 8;
					column.x -= speed;
				}
				
				if (Variablen.score == 20) {
					Rectangle column = Variablen.columns.get(i);
					speed = 10;
					column.x -= speed;
					Variablen.speedUp = true;
				}
				
				if (Variablen.score > 20 && Variablen.score < 40) {
					Variablen.speedUp = false;
					Rectangle column = Variablen.columns.get(i);
					speed = 10;
					column.x -= speed;
				}
				
				if (Variablen.score == 40) {
					Rectangle column = Variablen.columns.get(i);
					speed = 15;
					column.x -= speed;
					Variablen.speedUp = true;
				}
				
				if (Variablen.score > 40) {
					Variablen.speedUp = false;
					Rectangle column = Variablen.columns.get(i);
					speed = 15;
					column.x -= speed;
				}
				
			}
			
			//Fallgeschwindigkeit wird beschleunigt			
			if (Variablen.yMotion < 15) {
				//bei jedem Frame nimmt Fallgeschwindigkeit um 1 zu
				Variablen.yMotion += 1;
			}
			
			//Säule löschen, wenn sie aus dem Bild verschwindet und neue hinzufügen
			for(int i = 0; i < Variablen.columns.size(); i++) {
				Rectangle column = Variablen.columns.get(i);
				
				if (column.x + column.width < 0) {
					Variablen.columns.remove(column);
					
					if (column.y == 0) {
						addColumn(false);
					}
				};
			}
			
			//Position von Spielfigur um yMotion Einheiten verschieben in yRichtung
			Variablen.bird.y += Variablen.yMotion;
			
			//Schleife über jeder Säule
			for (Rectangle column : Variablen.columns) {
				
				//Score erhöhen, wenn Spielfigur unter der oberen Säule ist
				if (!Variablen.gameOver && column.y== 0 && Variablen.birdx + 50 / 2 > column.x + column.width / 2 - 5 && Variablen.birdx + 50/ 2 < column.x + column.width / 2 + 5) {  //Hälfte Speed
					Variablen.score++;
					Musik.music("audio/bing.wav");
				}
				
				//wenn Spielfigure auf eine Säule trifft -> GameOver
				if (column.intersects(Variablen.bird)) {
					Variablen.gameOver = true;
					
					//Spielfigur soll links von de Säule hängenbleiben
					if (Variablen.bird.x <= column.x) {
						Variablen.bird.x = column.x - Variablen.bird.width;
					}
					else {
						//Spielfigur soll au Säule aufliegen und nicht durchfallen, wenn sie die untere Säule trifft
						if (column.y != 0) {
							Variablen.bird.y = column.y - Variablen.bird.height;
						}
						//Spielfigur nicht weiter nach oben als untere Kante der oberen Säule
						else if (Variablen.bird.y < column.height){
							Variablen.bird.y = column.height;
						}
					}
				}
			}
			
			//wenn Spielfigur den Boden oder die Decke trifft -> GameOver
			if (Variablen.bird.y > Variablen.HEIGHT - 150 || Variablen.bird.y < 0) {
				Variablen.gameOver = true;
			}
			
			if (Variablen.bird.y + Variablen.yMotion >= Variablen.HEIGHT - 130) {
				Variablen.bird.y = Variablen.HEIGHT - 120 - Variablen.bird.height;
			}
		}
		//gesamtes Frame wird neu gerendert und repainted
		Variablen.renderer.repaint();
		}
	}
	
	/**
	 * Methode repaint zeichnet Objekte des Frames durch die im Timer definierte Aktualisierungsgeschwindigkeit erneut
	 * @param g übergibt Objekt g der Klasse Graphics 
	 */
	public void repaint(Graphics g) {
			
		//Boden
		g.setColor(Color.orange);
		g.fillRect(0, Variablen.HEIGHT - 120, Variablen.WIDTH, 100);
		
		//Gras
		g.setColor(Color.green);
		g.fillRect(0, Variablen.HEIGHT - 120, Variablen.WIDTH, 20);
		
		//Spieligur
		g.setColor(Color.yellow);
		g.fillOval(Variablen.bird.x, Variablen.bird.y, Variablen.bird.width, Variablen.bird.height);
		
		//Säulen
		for (Rectangle column : Variablen.columns) {
			paintColumn(g, column);
		}
		
		//Menüpunkt
		g.setColor(Color.red);
		g.fillOval(1145, 15, 30, 30);
		
		
		//Definition Schriftart/-größe
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 80));
		
		//wenn Game vorbei ist, Spielstand anzeigen
		if (Variablen.gameOver && Variablen.started) {
			g.drawString("Game Over!", 370, 150);
			g.drawString(String.valueOf(Variablen.score), Variablen.WIDTH / 2 - 30, 80);
		}
		
		
		//Score während des Games anzeigen -> wenn Speederhöhung, Ausgabe Erhöhung
		if (!Variablen.gameOver && Variablen.started) {
			g.drawString(String.valueOf(Variablen.score), Variablen.WIDTH / 2 - 25, 80);
			if (Variablen.speedUp) {
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 1, 50));
				g.drawString("Speed up!", 500,  130);
			}
		}
	}

	/**
	 * Main Methode initialisiert die Objekte der Klassen und ruft die Konstruktoren auf
	 * Startpunkt des Programms
	 * @param args Argumente der Mainmethode
	 */
	public static void main(String[] args) {
		new Variablen();
		Variablen.flappybird = new FlappyBird();
		
	}

	/**
	 * Methode keyReleased steurert bei aktivem Spiel die Bewegung der Spielfigur, wenn die Leertaste durch den Spieler gedrückt wird und spielt dabei Sound ab
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (Variablen.started) {
			jump();
			Musik.music("audio/flap.wav"); 
			}
		}
	}

	
	
	
	
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}


