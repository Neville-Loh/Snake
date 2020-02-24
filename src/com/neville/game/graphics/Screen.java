package com.neville.game.graphics;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	public Screen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		start();
	}
	public void tick() {
		System.out.println("Running...");
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
}
