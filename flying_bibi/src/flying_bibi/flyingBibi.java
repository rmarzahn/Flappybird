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


public class flyingBibi implements ActionListener, MouseListener, KeyListener
{
	
	public static flyingBibi flyingBibi;
	public static int Width=1200, Height=800;
	public Renderer renderer;
	public Rectangle bird;
	public Shape bibi;
	public ArrayList<Rectangle> columns;
	public int ticks, yMotion, score;
	public Boolean gameOver=true, started=false;
	public Random rand;
	
	public flyingBibi() 
	{
		
		JFrame jframe=new JFrame();
		Timer timer=new Timer(20, this);
		
		renderer=new Renderer();
		rand=new Random();
		//blablabla

		
		jframe.add(renderer);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(Width, Height);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setTitle("Flying Bibi");
		jframe.setVisible(true);
		
		bird=new Rectangle(Width/2-10, Height/2-10, 20, 20);
		columns=new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		timer.start();
	}	
	
	public void addColumn(boolean start) {
		int space=300;
		int width=100;
		int height=50+rand.nextInt(300);
		
		if(start)
		{
			columns.add(new Rectangle(Width+width+columns.size()*300, Height-height-120, width, height));
			columns.add(new Rectangle(Width + width + (columns.size()-1)*300, 0, width, Height-height-space));
			
		}
		else 
		{
			columns.add(new Rectangle(columns.get(columns.size()-1).x +600, Height-height-120, width, height));
			columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, Height-height-space));
		}
		

		
	}
	
	public void paintColumn(Graphics g, Rectangle column) 
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	
	public void jump() {
		if (gameOver) 
		{
			bird=new Rectangle(Width/2-10, Height/2-10, 20, 20);
			columns.clear();
			yMotion=0;
			score=0;
			
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			gameOver=false;
		}
		if (!started) 
		{
			started=true; 
		}
		else if(!gameOver) 
		{
			if (yMotion>0) {
				yMotion=0;
			}
			yMotion-=10;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		int speed=10;
		
		ticks++;
		
		if (started) 
		{
			for (int i = 0; i < columns.size(); i++) 
			{
				Rectangle column=columns.get(i);
				column.x-=speed;
				
			}
			
			if(ticks%2==0 && yMotion<15) 
			{
				yMotion+=2;
				
			}
			
			for (int i = 0; i < columns.size(); i++) 
			{
				Rectangle column=columns.get(i);
				
				if(column.x+column.width<0) {
					columns.remove(column);
					
					if (column.y==0) 
					{
						addColumn(false);
					}
					
				}
				
			}
			bird.y +=yMotion;
			
			for (Rectangle column: columns) 
			{
				if (column.y==0&&bird.x+bird.width/2>column.x+column.width/2-10&& bird.x+bird.width/2<column.x+column.width/2+10) {
					score++;
				}
				if(column.intersects(bird)) 
				{
					gameOver=true;
					if(bird.x<=column.x) {
						bird.x=column.x-bird.width;
					}
					else
					{
						if (column.y !=0) {
							bird.y=column.y-bird.height;
						}
						else if (bird.y<column.height) {
							bird.y=column.height;
						}
					}
				}
				
			}
			
			if (bird.y>Height-140 || bird.y<0) 
			{
				gameOver=true;
			}
			if (bird.y+yMotion>=Height-120) 
			{
				bird.y=Height-120-bird.height;
			}
		}
		
		renderer.repaint();
	}
	
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
			
			for (Rectangle column:columns) {
				paintColumn(g, column);
			}
			
			g.setColor(Color.white);
			g.setFont(new Font("Aerial",1,100));
			if (!started) {
				 g.drawString("Click to Start", Width/4, Height/2-50);
			}
			if (gameOver&&started) {
				 g.drawString("Game Over!", Width/4, Height/2-50);
				 g.drawString(String.valueOf(score), Width/2-50, Height/2+50);
			}
			if(!gameOver&&started) {
				 g.drawString(String.valueOf(score), Width/2-25, 100);
			}
			
	}
		
	
	 public static void main(String[] args) {
		  flyingBibi=new flyingBibi();
	 }

	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
		
	}

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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	
	
}
