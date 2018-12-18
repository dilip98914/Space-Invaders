package main.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	public float x, y;
	public int w = 8, h = 10;

	public float vy = 0;
	private float SPEED=0.3f;
	public boolean collided=false;
	
	public Bullet(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(int dir) {
		vy=dir*SPEED;
		y += vy;
	}

	public void render(Graphics g,Color color) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, w, h);
	}

}
