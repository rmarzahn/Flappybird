package flappybird;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Variablen {
	
	
	//static Objekte, damit in der Main den Kontruktor aufrufen zu können
	public static  FlappyBird flappybird;
	//Höhe und Breite des jframes in Variablen festlegen, damit die Werte im Falle von Änderungen nur an einer Stelle angepasst werden müssen
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 700;
	public static int backgroundX1 = 0, backgroundX2 = -700, backgroundspeed = 9;
	public static int birdx = 350, birdy = 350;
	//Objekt der Klasse renderer die genutzt wird, um die Components im Frame anzuzeigen
	public static Renderer renderer;
	public static Background background;
	public static boolean speedUp = false;
	
	//Spielfigur
	public static Rectangle bird;
	
	public static int ticks;
	//yMotion legt die Bewegung der Spielfigur in yRichtung pro Frame fest
	public static int yMotion;
	public static int score = 18;
	//speichert Highscore
	public static int highscore;
	public static int speed;
	public static int welt = 0;
	
	//Hilfsvariable um Highscore anzuzeigen
	public static boolean displayhighscore = false;
	//Hilfsvariablen um zu prüfen, ob das Game started oder over ist
	public static boolean gameOver;
	public static boolean started;
	public static boolean start;
	
	//ArrayList aller Säulen
	public static ArrayList<Rectangle> columns;
	
	public static Random rand;
	public static Menu menu;
	
}
