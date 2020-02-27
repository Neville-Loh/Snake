package com.neville.game.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import com.neville.game.entities.Body;
import com.neville.game.entities.Food;

public class Screen extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	public static final int WIDTH = 800, HEIGHT = 800;
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean running = false;
	
	
	private Body b;
	private ArrayList<Body> snake;
	private Food food;
	private ArrayList<Food> foodList;
	
	private Random r;
	
	private int x = 10, y = 10;
	private int size = 5;
	private int ticks = 0;
	
	private boolean right = true, left = false, up = false, down = false;
	private Key key;
	
	public Screen() {
		//key control
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		
		
		//construct
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		r = new Random();
		
		snake = new ArrayList<Body>();
		foodList = new ArrayList<Food>();
		
		start();
	}
	
	public void tick() {
		//System.out.println("Running...");
		if(snake.size() == 0) {
			b = new Body(x, y, 10);
			snake.add(b);
		}

		for(int i = 0; i < snake.size(); i++) {
			if(x == snake.get(i).getx() && y == snake.get(i).gety()) {
				if (i != snake.size() - 1) {
					stop();
				}
			}
		}
		
		
		
		if(ticks > 750000) {
			
			// Turning when boarder is hit
			if (x < 0) {
				up = false;
				down = true;
				left = false;
				right = false;
				x++;
				y++;
			}
			else if(x > 79){
				up = true;
				down = false;
				left = false;
				right = false;
				x--;
				y--;
			}
			
			else if (y < 0) {
				up = false;
				down = false;
				left = true;
				right = false;
				x--;
				y++;
			}
			else if (y > 79) {
				up = false;
				down = false;
				left = false;
				right = true;
				x++;
				y--;
			}
			
			// control
			else {
				if(right) x++;
				if(left) x--;
				if(up) y--;
				if(down) y++;
			}
			
			b = new Body(x, y, 10);
			snake.add(b);
			if(snake.size() > size) {
				snake.remove(0);
			
			ticks = 0;
			}
			
			if(foodList.size() == 0) {
				int x = r.nextInt(79);
				int y = r.nextInt(79);
				
				food = new Food(x, y, 10);
				foodList.add(food);
			}
			
			// Eating food
			for (int i = 0; i < foodList.size(); i++) {
				if (x == foodList.get(i).getx() && y == foodList.get(i).gety()) {
					size++;
					foodList.remove(i);
					i--;
				}
			}
		}
		
		ticks++;
	}
	
	public void paint(Graphics g) {
		// clear current
		g.clearRect(0, 0, WIDTH, HEIGHT);
		// drawing grid
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < WIDTH / 10; i++) {
			g.drawLine( i * 10, 0, i * 10, HEIGHT);
		}
		
		for(int i = 0; i < HEIGHT/ 10; i++) {
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
		
		// drawing snake
		for(int i = 0; i< snake.size(); i++) {
			snake.get(i).draw(g);
		}
		// drawing food
		for (int i = 0; i < foodList.size(); i++) {
			foodList.get(i).draw(g);
		}
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game Loop");
		thread.start();
				
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}
	
	// control
	private class Key implements KeyListener {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_RIGHT && !left) {
				up = false;
				down = false;
				right = true;				
			}
			if(key == KeyEvent.VK_LEFT && !right) {
				up = false;
				down = false;
				left = true;
			}
			if(key == KeyEvent.VK_UP && !down) {
				left = false;
				right = false;
				up = true;
			}
			if(key == KeyEvent.VK_DOWN && !up) {
				left = false;
				right = false;
				down = true;
			}
			
			
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
}
