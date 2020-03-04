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

//Klasse erbt von Action- und KeyListener
public class FlappyBird implements ActionListener, KeyListener{
	
	
	public FlappyBird() {
		
		//Ausgabefenster des Spiels, Objekt der Klasse JFrame, Anzeige nur bei visuable = true
		JFrame jframe = new JFrame();
		//Objekt wird alle 20 Millisekunden aufgerufen -> S�ulen verschieben sich alle 20ms um 10 Einheiten nach links
		//Spielgfigur bewegt sich um yMotion in yRichtung
		//andere Objekte werden angepasst und neu eingestellt -> neues Ausf�hren des Frames
		Timer timer = new Timer(20, this);		
		
		//Konstruktor der definierten Klasse wird aufgerufen
		Variablen.renderer = new Renderer();
		Variablen.rand = new Random();
		Variablen.menu = new Menu();
		
		//renderer Components werden angezeigt im Frame
		jframe.add(Variablen.renderer);	
		jframe.setTitle("Flappy Bird");
		//Game beendet, wenn Fenster geschlossen wird
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(Variablen.WIDTH, Variablen.HEIGHT);
		jframe.addKeyListener(this);
		jframe.addMouseListener(new MouseInput());
		//Gr��enver�nderung des Fensters nicht m�glich
		jframe.setResizable(false);
		//Sichtbarkeit des Fensters
		jframe.setVisible(true);
		
		
		//Spielfigur wird initialisiert -> damit wird Spielfigur vor dem esten Jump angezeigt
		Variablen.bird = new Rectangle(Variablen.birdx, Variablen.birdy, 40, 35);
		//ArrayList dynamisch -> S�ulen k�nnen gel�scht und hinzugef�gt werden
		Variablen.columns = new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
				
		timer.start();
	}
	
	//Funktion zum Hinzuf�gen von S�ulen
	public static void addColumn(boolean start) {
			
			//Abstand S�ulen, Breite S�ulen, H�he S�ulen zuf�llig zwischen 50 und 350 generiert
			int space = 300;
			int width = 100;
			int height = 50 + Variablen.rand.nextInt(300);
			
			//Anzeige der S�ulen bei Game Start: Abstand der S�ulen festlegen, pro Spalte zwei S�ulen
			if (start) {
				//ColumnSize -1, da obere S�ule auf H�he der unteren S�ule sein soll
				Variablen.columns.add(new Rectangle(Variablen.WIDTH + width + Variablen.columns.size() * 300, Variablen.HEIGHT - height - 120, width, height));
				Variablen.columns.add(new Rectangle(Variablen.WIDTH + width + (Variablen.columns.size() -1) * 300, 0, width,  Variablen.HEIGHT - height - space));
			}
			else {
				//xPosition + 600 als neue Position S�ule, ColumnSize -1, da Indedx bei 0 anf�ngt zu z�hlen und size bei 1
				Variablen.columns.add(new Rectangle(Variablen.columns.get(Variablen.columns.size() - 1).x + 600, Variablen.HEIGHT - height - 120, width, height));
				Variablen.columns.add(new Rectangle(Variablen.columns.get(Variablen.columns.size() - 1).x, 0, width, Variablen.HEIGHT - height - space));
			}
	}
	
	//Component der S�ule erstellen, um diese im Fenster anzeigen zu k�nnen
	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
		
	}
	
	//Aufruf bei BlankTab
	public static void jump() {
		
		if (Variablen.gameOver) {
			
			//Spielfigur wird intitialisiert, S�ulen zur�ckgesetzt, Spielfigur bewegt sich nicht, Punktestand auf 0
			Variablen.bird = new Rectangle(Variablen.birdx, Variablen.birdy, 40, 35); 
			Variablen.columns.clear();
			Variablen.yMotion = 0;
			Variablen.score = 0;
			
			
			//Start mit vier S�ulen
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			
			//Spielbeginn -> GameOver ist false
			Variablen.gameOver = false;
		}
		
		
		//wenn Spiel beginnt -> started auf true
		if (!Variablen.started) {
			Variablen.started = true;
		}
		else if (!Variablen.gameOver) {
			
			//Spielfigur beibt f�r einen Frame in der Luft stehen, wewnn sie vor dem Sprung im Fallen war
			if (Variablen.yMotion > 0) {
				Variablen.yMotion = 0;
			}
			//Spielfigur geht um 10 Einheiten nach oben pro Frame (und um speed Einheiten nach rechts)
			Variablen.yMotion -= 10;
		}
	}
	
	
	//bei jedem Frame aufgerufen von Timer
	public void actionPerformed(ActionEvent e) {
		
		if (Variablen.start == true) {
			
			if (Variablen.started) {
			
			//Geschwindigkeit der S�ulen nach links (Einheiten pro Frame)
			int speed = 8;
			
			//Geschwindigkeitserh�hung bei Erreichen eines bestimmten Scores -> Ausgabe Erh�hung true
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
			
			//S�ule l�schen, wenn sie aus dem Bild verschwindet und neue hinzuf�gen
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
			
			//Schleife �ber jeder S�ule
			for (Rectangle column : Variablen.columns) {
				
				//Score erh�hen, wenn Spielfigur unter der oberen S�ule ist
				if (!Variablen.gameOver && column.y== 0 && Variablen.birdx + 50 / 2 > column.x + column.width / 2 - 5 && Variablen.birdx + 50/ 2 < column.x + column.width / 2 + 5) {  //H�lfte Speed
					Variablen.score++;
					Musik.music("audio/bing.wav");
				}
				
				//wenn Spielfigure auf eine S�ule trifft -> GameOver
				if (column.intersects(Variablen.bird)) {
					Variablen.gameOver = true;
					
					//Spielfigur soll links von de S�ule h�ngenbleiben
					if (Variablen.bird.x <= column.x) {
						Variablen.bird.x = column.x - Variablen.bird.width;
					}
					else {
						//Spielfigur soll au S�ule aufliegen und nicht durchfallen, wenn sie die untere S�ule trifft
						if (column.y != 0) {
							Variablen.bird.y = column.y - Variablen.bird.height;
						}
						//Spielfigur nicht weiter nach oben als untere Kante der oberen S�ule
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
		//gesamtes Frame wird neu erstellt
		Variablen.renderer.repaint();
		}
	}
	
	//wird bei jedem Frame von Timer aufgerufen
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
		
		//S�ulen
		for (Rectangle column : Variablen.columns) {
			paintColumn(g, column);
		}
		
		g.setColor(Color.red);
		g.fillOval(1145, 15, 30, 30);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 80));
		
		//wenn Game vorbei ist, Spielstand anzeigen -> wenn Highscore �bertroffen diesen anzeigen
		if (Variablen.gameOver && Variablen.started) {
			g.drawString("Game Over!", 370, 150);
			g.drawString(String.valueOf(Variablen.score), Variablen.WIDTH / 2 - 25, 80);
		}
		
		
				
		//Score w�hrend des Games anzeigen -> wenn Speederh�hung, Ausgabe Erh�hung
		if (!Variablen.gameOver && Variablen.started) {
			g.drawString(String.valueOf(Variablen.score), Variablen.WIDTH / 2 - 25, 80);
			if (Variablen.speedUp) {
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 1, 50));
				g.drawString("Speed up!", 500,  130);
			}
		}
	}

	public static void main(String[] args) {
		//Objekte der Klassen werden instanziert, Kontruktor wird aufgerufen
		new Variablen();
		Variablen.flappybird = new FlappyBird();
		
	}

	//wenn BlancTab bei started = true -> jump und flap Audio
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


