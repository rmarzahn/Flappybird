package flappybird;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Variablen {
	
	public static  FlappyBird flappybird;
	public final static int WIDTH = 700;
	public final static int HEIGHT = 700;
	public static int backgroundX1 = 0, backgroundX2 = -700, backgroundspeed = 9;
	public static int birdx = 350, birdy = 350;
	public static Renderer renderer;
	public static Background background;
	
	public static Rectangle bird;
	
	public static int ticks;
	public static int yMotion;
	public static int score;
	public static int speed;
	
	public static boolean gameOver;
	public static boolean started;
	public static boolean start;
	
	public static ArrayList<Rectangle> columns;
	
	public static Random rand;
	public static Menu menu;
	
}
