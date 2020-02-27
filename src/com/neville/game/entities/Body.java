package com.neville.game.entities;

import java.awt.Color;
import java.awt.Graphics;


public class Body {
	
	private int x, y, width, height;
	
	public Body(int x, int y, int tileSize) {
		this.x = x;
		this.y = y;
		width = tileSize;
		height = tileSize;
		
	}
	
	// updating snake
	public void tick() {
		
	}
	
	// drawing on screen
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x * width, y * height, width, height);
		g.setColor(Color.GRAY);
		g.fillRect(x * width + 2, y * width + 2, width - 4, height - 4);
	}
	
	//getters
	public int getx() {
		return x;
	}
	public void setx(int x) {
		this.x = x;
	}
	public int gety() {
		return y;
	}
	public void sety(int y) {
		this.y = y;
	}
	
}
