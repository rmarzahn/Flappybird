package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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

public class FlappyBird implements ActionListener, KeyListener{
	
	
	public FlappyBird() {
		
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);		
		
		Variablen.renderer = new Renderer();
		Variablen.rand = new Random();
		Variablen.background = new Background();
		Variablen.menu = new Menu();
		
		
		jframe.add(Variablen.renderer);	
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(Variablen.WIDTH, Variablen.HEIGHT);
		jframe.addKeyListener(this);
		jframe.addMouseListener(new MouseInput());
		jframe.setResizable(false);
		jframe.setVisible(true);
		
		//this.addMouseListener(new MouseInput());
		
		Variablen.bird = new Rectangle(Variablen.WIDTH / 2 - 10, Variablen.HEIGHT / 2 - 10, 20, 20);
		Variablen.columns = new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
				
		timer.start();
	}
	
	
	public static void addColumn(boolean start) {
		
		int space = 300;
		int width = 100;
		int height = 50 + Variablen.rand.nextInt(300);
		
		if (start) {
			
			Variablen.columns.add(new Rectangle(Variablen.WIDTH + width + Variablen.columns.size() * 300, Variablen.HEIGHT - height - 120, width, height));
			Variablen.columns.add(new Rectangle(Variablen.WIDTH + width + (Variablen.columns.size() -1) * 300, 0, width,  Variablen.HEIGHT - height - space));
		}
		else {
			Variablen.columns.add(new Rectangle(Variablen.columns.get(Variablen.columns.size() - 1).x + 600, Variablen.HEIGHT - height - 120, width, height));
			Variablen.columns.add(new Rectangle(Variablen.columns.get(Variablen.columns.size() - 1).x, 0, width, Variablen.HEIGHT - height - space));
		}
	}
	
	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
		
	}
	
	public static void jump() {
		
		if (Variablen.gameOver) {
			
			Variablen.bird = new Rectangle(Variablen.WIDTH / 2 - 10, Variablen.HEIGHT / 2 - 10, 20, 20);
			Variablen.columns.clear();
			Variablen.yMotion = 0;
			Variablen.score = 0;
			
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
			if (Variablen.yMotion > 0) {
				Variablen.yMotion = 0;
			}
			Variablen.yMotion -= 10;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (Variablen.start == true) {
		
			if (Variablen.score <= 20) {
				Variablen.speed = 8;
			}
			else if (Variablen.score > 20 && Variablen.score <= 50) {
				Variablen.speed = 10;
			}
			else {
				Variablen.speed = 15;
			}
			
			Variablen.ticks++;
			
			if (Variablen.started) {
			
			int speed = 10;
			
			for(int i = 0; i < Variablen.columns.size(); i++) {
				Rectangle column = Variablen.columns.get(i);
				column.x -= speed;
			}
			
			if (Variablen.ticks % 2 == 0 && Variablen.yMotion < 15) {
				Variablen.yMotion += 2;
			}
			
			for(int i = 0; i < Variablen.columns.size(); i++) {
				Rectangle column = Variablen.columns.get(i);
				
				if (column.x + column.width < 0) {
					Variablen.columns.remove(column);
					
					if (column.y == 0) {
						addColumn(false);
					}
				};
			}
			
			Variablen.bird.y += Variablen.yMotion;
			
			for (Rectangle column : Variablen.columns) {
				
				if (column.y== 0 && Variablen.bird.x + Variablen.bird.width / 2 > column.x + column.width / 2 - 5 && Variablen.bird.x + Variablen.bird.width / 2 < column.x + column.width / 2 + 5) {  //Hälfte Speed
					Variablen.score++;
					Musik.music("audio/bing.wav");
				}
				
				if (column.intersects(Variablen.bird)) {
					Variablen.gameOver = true;
					
					if (Variablen.bird.x <= column.x) {
						Variablen.bird.x = column.x - Variablen.bird.width;
					}
					else {
						if (column.y != 0) {
							Variablen.bird.y = column.y - Variablen.bird.height;
						}
						else if (Variablen.bird.y < column.height){
							Variablen.bird.y = column.height;
						}
					}
				}
			}
			
			if (Variablen.bird.y > Variablen.HEIGHT - 120 || Variablen.bird.y < 0) {
				Variablen.gameOver = true;
			}
			
			if (Variablen.bird.y + Variablen.yMotion >= Variablen.HEIGHT - 120) {
				Variablen.bird.y = Variablen.HEIGHT - 120 - Variablen.bird.height;
				Variablen.gameOver = true;
			}
		}
			
			Variablen.renderer.repaint();
		}
	}
	
	
	public void repaint(Graphics g) {
			
		g.setColor(Color.orange);
		g.fillRect(0, Variablen.HEIGHT - 120, Variablen.WIDTH, 120);
		
		g.setColor(Color.green);
		g.fillRect(0, Variablen.HEIGHT - 120, Variablen.WIDTH, 20);
		
		g.setColor(Color.red);
		g.fillRect(Variablen.bird.x, Variablen.bird.y, Variablen.bird.width, Variablen.bird.height);
		
		for (Rectangle column : Variablen.columns) {
			paintColumn(g, column);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
		
		
		//if (!Variablen.started) {
		//	g.drawString("Welcome", 25, Variablen.HEIGHT / 3 - 50);
		//	g.drawString("to Flappy Bird", 10, Variablen.HEIGHT / 3 - 150);
		//}
		
		
		if (Variablen.gameOver) {
			g.drawString("Game Over!", 75, Variablen.HEIGHT / 2 - 50);
			g.drawString(String.valueOf(Variablen.score), Variablen.WIDTH / 2 - 25, 100);
		}
		
		if (!Variablen.gameOver && Variablen.started) {
			g.drawString(String.valueOf(Variablen.score), Variablen.WIDTH / 2 - 25, 100);
		}
	}

	public static void main(String[] args) {
		
		new Variablen();
		Variablen.flappybird = new FlappyBird();
		
	}

	
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


