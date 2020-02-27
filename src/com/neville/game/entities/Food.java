package com.neville.game.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Food {
	
	private int x, y, width, height;
	
	public Food(int x, int y, int tileSize) {
		this.x = x;
		this.y = y;
		this.width = tileSize;
		this.height = tileSize;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x * width, y * height, width, height);
		
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
