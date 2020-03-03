package flying_bibi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

//Klasse erbt von Action-, Mouse& Keylistener
public class flyingBibi implements ActionListener, MouseListener, KeyListener
{
	//Attribute der Klasse
	//Ein static Objekt der Klasse, um damit in der Main den Konstruktor aufrufen zu können
	public static flyingBibi flyingBibi;
	//Höhe und Breite des jFrames in Variablen festlegen, damit die Werte im Falle einer Änderung nur an
	//einer Stelle angepasst werden müssen
	public static int Width=1200, Height=800;
	//Objekt der Klasse renderer, die genutzt wird, um die Components im Frame anzuzeigen
	public Renderer renderer;
	//Spielfigur
	public Rectangle bird;
	
//	public Shape bibi;
	
	//Arraylist aller Säulen
	public ArrayList<Rectangle> columns;
	//yMotion legt die Bewegung der Spielfigur in y Richtung pro Frame fest
	//!Vorzeichen ist immer umgekehrt
	public int yMotion, score;
	//hierin soll Highscore gespeichert werden
	//TODO: Variable in einer speraten Datei speichern und abrufen
	public static int highScore;
	//Hilfsvaribale, um den Highscore anzuzeigen
	public static boolean displayHighScore=false;
	//Hilfsvariablen, um zu prüfen, ob das Spiel angefangen, bzw. beendet wurde
	public Boolean gameOver=true, started=false;
	//Objekt der importierten Klasse Random, um Zufallszahlen zu generieren für die Höhe der Säulen
	public Random rand;
	
	//2. Schritt: Konstruktor wird aufgerufen
	public flyingBibi() 
	{
		//Ausgabefenster, in dem das Spiel gezeigt wird
		//Objekt der Klasse JFrame wird erzuegt
		//Anzeige erfolgt erst, wenn Visible=true
		JFrame jframe=new JFrame();
		
		//Objekt wird alle 20 Millisekunden aufgerufen
		//d.h. alle 20 ms verschieben sich Säulen um 10 Einheiten nach links 
		//und Bibi bewegt sich um yMotion in y Richtung
		//und Hintergrund undsoweiter wird immer wieder neu erstellt
		//d.h. im Endeffekt werden alle Funktionen bei jeden Frame neu ausgeführt
		Timer timer=new Timer(20, this);
		
		//Kostruktor der definierten Klasse wird aufgerufen
		renderer=new Renderer();
		//Instanzierung Objekt
		rand=new Random();

		//renderer Components werden angezeigt in JFrame
		//Säulen, Bibi, Background...
		jframe.add(renderer);
		//Programm wird abgebrochen, sobald Fenser geschlossen wird
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Höhe und Breite des Fensters festelgen
		jframe.setSize(Width, Height);
		//Fenster reagiert auf Mausklicks
		jframe.addMouseListener(this);
		//Fenster reagiert auf Tastenklicks
		jframe.addKeyListener(this);
		//Fenstergröße kann nicht geändert werden
		jframe.setResizable(false);
		//Titel
		jframe.setTitle("Flying Bibi");
		//Sichtbarkeit des Fensters
		jframe.setVisible(true);
		
		//Spielfigur wird initialisiert
		//brauch man nur, damit bibi vor dem ersten Jump angezeigt wird
		bird=new Rectangle(Width/2-10, Height/2-10, 20, 20);
		//Arraylist dynamisch: es können Säulen gelöscht und hinzugefügt werden
		columns=new ArrayList<Rectangle>();
		
		//gestartet wird mit 4 Säulen



		
		//Timer beginnt
		timer.start();
	}	
	
	//Funktion zum Hinzufügen von Säulen
	//Parameter start: Soll zu Beginn des Spiels Säule hinzugefügt werden?
	//Am Anfang wird mit 3 Säulen gestartet
	public void addColumn(boolean start) {
		//Abstand zwischen oberer und unterer Säule
		//wird noch mal 120 abgezogen, aslo tatsächlicher Abstand 180!
		int space=180;
		//Breite der Säule
		int width=100;
		//Höhe der Säule
		//Mininum 50
		//maximum 350
		//Werte dazwischen werden zufällig generiert
		int height=50+rand.nextInt(300);
		
		//Falls die Säulen zu beginn des Spiels angezeigt werden sollen
		if(start)
		{
			//Säulen zu Beginn außerhalb des Bildschirms
			//Alle Säulenpaare mit Abstand 2*300(=600)
			//untere Säule wird zuerst erstellt
			//columns.size() gibt die Anzahl der Säulen wieder
			//Pro "Spalte" immer 2 Säulen
			//Rectangles werden immer von oben links nach unten rechts gezeichnet
			columns.add(new Rectangle(Width+width+columns.size()*300, Height-height-120, width, height));
			//obere Säule
			//columns.size()-1, da die obere Säule auf Höhe der unteren sein soll
			columns.add(new Rectangle(Width + width + (columns.size()-1)*300, 0, width, Height-height-space-120));
			
		}
		else 
		{
			//Zur x-Position der letzten Säule wird Abstand 600 addiert
			//columns.size()-1), da Index bei null anfängt zu zählen und size bei 1
			//in unserem Fall würde auch columns.get(3).x reichen, da immer 2 Säulenpaare bei Aufruf der Funktion
			//mit start=false exisitieren
			columns.add(new Rectangle(columns.get(columns.size()-1).x +600, Height-height-120, width, height));
			//abstand gleich 0, da obere immer auf gleicher x-position mit unterer Säule
			columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, Height-height-space-120));
		}
		

		
	}
	
	//Component der Säule erstellen, um diese im Fenster anzeigen zu können
	public void paintColumn(Graphics g, Rectangle column) 
	{
		//Farbe der Säule
		g.setColor(Color.green.darker());
		//Säule wird mit Farbe ausgefüllt, damit man sie sehen kann
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	
	//Methode wird bei Maus-oder Leertastenklcik aufgerufen
	public void jump() {
		//beginn des Spiels
		//wenn das erste mal eine Taste gedrückt wird
		//zu Beginn ist gameOver=true
		if (gameOver) 
		{
			//bibi wird initialisiert
			bird=new Rectangle(Width/2-10, Height/2-10, 20, 20);
			//Säulen zurücksetzen
			columns.clear();
			//im ersten Frame bewegt sich bibi nicht
			yMotion=0;
			//Punktestand beginnt bei 0
			score=0;
			
			//Start mit 3 Säulen
			//3, weil das die maximale Anzahl an Säulen ist, die gleichzeigit angezeigt werden können
			//Mehr als drei sind kein Problem, bringen aber auch nichts
			addColumn(true);
			addColumn(true);
			addColumn(true);
			
			//Spiel hat begonnen, gameOver nicht mehr true!
			gameOver=false;
		}
		//Am Anfang ist started=false
		//wird auf true gesetzt, wenn spiel beginnt
		if (!started) 
		{
			started=true; 
		}
		//Wenn das Spiel läuft
		else if(!gameOver) 
		{
			//Bibi bleibt für einen Frame in der Luft stehen, wenn sie vor dem Sprung im Fallen war
			//egal wie hoch die Fallgeschwindigkeit war
			if (yMotion>0) {
				yMotion=0;
			}
			//Bibi geht 10 Einheiten nach oben, pro Frame (streng genommen nur beim ersten frame um 9)
			//und pro Frame um speed(=10) Einheiten nach rechts
			//-->Steigung =1
			//Steigung nimmt ab: Bie jedem Frame wird 1 Einheit von der Steigung subtrahiert
			//gesamte Bewegung in y-Richtung: 9+8+7+6+5+4+3+2+1+0=45
			yMotion-=10;
		}
	}
	
	//Wie wird Funktion aufgerufen?
	//Wird bei jedem Frame aufgerufen
	//also von Timer aufgerufen
	public void actionPerformed(ActionEvent e) {
		//Wie schnell die Säulen sich nach links bwewegen
		//also wie viele Einheiten pro Frame
		int speed=10;
		
		
		if (started) 
		{
			//Bei jedem Frame alle Säulen um speed (=10) Einheiten nach links verschieben
			for (int i = 0; i < columns.size(); i++) 
			{
				Rectangle column=columns.get(i);
				column.x-=speed;
				
			}
			//Fallgeschwindigkeit nimmt bei jedem Frame um 1 Einheit zu
			//-->Fallgeschwindigkeit beschleunigt!!!
			//maximale Fallgeschwindigkeit bei 14 Einheiten/Frame (-1,4 Steigung)
			//approx. stetige Funktionen
			//Steigung im diskreten stärker negativ
			//Steigung=-0,1x
			//Integral=-0,05x^2
			//fällt in 15 Frames um 105 Einheiten, danach um 14 pro Frame
			//maximal 700 Einheiten pro Sekunden
			if(yMotion<15) 
			{
				//jedes Frame nimmt Fallgeschwindigkeit um eine Einheit zu
				yMotion+=1;
				
			}
			
			//Säule löschen, wenn sie aus dem Bild verschwindet und neue hinzufügen
			for (int i = 0; i < columns.size(); i++) 
			{
				Rectangle column=columns.get(i);
				
				//Wenn Säule außerhalb des Bildschirms
				if(column.x+column.width<0) {
					//Säule löschen
					columns.remove(column);
					
					//nur bei jeder zweiten Säule wird ein neues Säulenpaar hinzugefügt
					//genau genommen bei jeder oberern Säule
					if (column.y==0) 
					{
						addColumn(false);
					}
					
				}
				
			}
			//Position von Bibi um yMotion Einheiten verschieben auf der y-Achse
			bird.y +=yMotion;
			
			//Schleife über jede Säule
			//Punktzahl wird erhöht, falls Bird genau unter oberer Säule
			//Falls Bibi auf Säule trifft, ist Spiel vorbei
			for (Rectangle column: columns) 
			{
				//Score um eine Einheit erhöhen, wenn Bibi genau unter oberer Säule ist
				//außer spiel ist bereits vorbei
				if (!gameOver&&column.y==0&&bird.x+bird.width/2>column.x+column.width/2-10&& bird.x+bird.width/2<column.x+column.width/2+10) {
					score++;
				}
				//wenn Bibi auf Säule trifft, dann ist Spiel vorbei
				else if(column.intersects(bird)) 
				{
					gameOver=true;
					//Bibi soll links von der Säule "hängenbleiben"
					
					if(bird.x<=column.x) {
						bird.x=column.x-bird.width;
					}
					else
					{
						//wenn Bibi auf untere Säule trifft
						if (column.y !=0) {
							//dann soll bibi auf Säule aufliegen, und nicht "dadurch fallen"
							bird.y=column.y-bird.height;
						}
						//Bibi soll nicht weiter nach oben gehen können als die untere Kante der oberen Säule
						else if (bird.y<column.height) {
							bird.y=column.height;
						}
					}
				}
				
			}
			//wenn Vogel auf Boden oder Decke trifft, dann ist Spiel vorbei
			//126=120+20-14
			//126=Höhe Bode+Größe bibi-yMotion
			if (bird.y>Height-126 || bird.y<0) 
			{
				gameOver=true;
			}
			//
			if (bird.y+yMotion>=Height-120) 
			{
				bird.y=Height-120-bird.height;
			}
		}
		//gesamtes frame wird neu erstellt mit repaint methode
		renderer.repaint();
	}
	
	//wird bei jedem Frame von Timer aufgerufen	
	public void repaint(Graphics g) 
	{
		//Hintergrund/Himmel
			g.setColor(Color.cyan);
			g.fillRect(0,0,Width, Height);
			
			//Boden
			g.setColor(Color.orange);
			g.fillRect(0,Height-120, Width,120);

			
			//Gras
			g.setColor(Color.green);
			g.fillRect(0,Height-120, Width,15);
			
			//Bibi
			g.setColor(Color.red);
			g.fillRect(bird.x, bird.y, bird.width, bird.height);
			
			//Säulen
			for (Rectangle column:columns) {
				paintColumn(g, column);
			}
			
			
			//Schriftart und Farbe
			g.setColor(Color.white);
			g.setFont(new Font("Aerial",1,100));
			//wenn spiel noch nicht begonnen hat
			//also beim Aufruf der Main Methode
			if (!started) {
				 g.drawString("Click to Start", Width/4, Height/2-50);
			}
			//Wenn das Spiel vorbei ist, wird Spielstand angezeigt
			//Wenn HighScore übertroffen wurde, wird dieser angezeigt
			//TODO: Highscore in sperater Datei speichern
			if (gameOver&&started) {
				 if (score>highScore||displayHighScore) {
					 System.out.println("score" + score);
					 System.out.println("highscore" + highScore);
					 g.drawString("New Highscore: "+ String.valueOf(score), Width/8, Height/2-50);
					 highScore=score;
					 displayHighScore=true;
				 }
				 else{
					 System.out.println(score);
					 g.drawString("Game Over!", Width/4, Height/2-50);
					 g.drawString(String.valueOf(score), Width/2-50, Height/2+50);
//					 g.drawString("Highscore: "+ String.valueOf(highScore), Width/4  , Height/2+150);
				 }
			}
			//Während des Spiel soll Score angezeigt werden
			if(!gameOver&&started) {
				 g.drawString(String.valueOf(score), Width/2-25, 100);
			}
			
	}
		
	
	//Startpunkt des Programms
	 public static void main(String[] args) {
		 //Objekt der Klasse wird instanziert, Konstruktor wird aufgerufen
		  flyingBibi=new flyingBibi();
	 }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			jump();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
		
	}

	
		
	//Rest wird nicht benötigt
	@Override
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	
	
}
