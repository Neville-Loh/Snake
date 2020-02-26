package com.neville.game.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.neville.game.entities.Body;

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
		
		snake = new ArrayList<Body>();
		
		start();
	}
	
	public void tick() {
		//System.out.println("Running...");
		if(snake.size() == 0) {
			b = new Body(x, y, 10);
			snake.add(b);
		}
		if(ticks > 250000) {
			if(right) x++;
			if(left) x--;
			if(up) y--;
			if(down) y++;
			
			b = new Body(x, y, 10);
			snake.add(b);
			
			ticks = 0;
			
			if(snake.size() > size) {
				snake.remove(0);
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
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game Loop");
		thread.start();
				
	}
	
	public void stop() {
		running = false;
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
		
	}
}
