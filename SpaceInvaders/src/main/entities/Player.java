package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Player implements KeyListener {
	public float x, y;
	public int w = 16, h = 16;
	public float vx, vy;
	public static final float SPEED = 0.3f;
	private boolean fire = false;

	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public boolean rightPressed, leftPressed, spacePressed;
	private boolean collided=false;

	public Player(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void checkCollision(int centerX,int centerY){
		if(centerX>x && centerX<(int)(x+w)){
			if(centerY>y && centerY<(int)(y+h)){
				collided=true;
			}
		}
	}

	
	
	private void init() {
	}

	public void tick() {
		if (leftPressed) {
			vx = -SPEED;

		} else if (rightPressed) {
			vx = SPEED;
		}
		x += vx;
		y += vy;
		vx = vy = 0;
		if (spacePressed) {
			fire();
			spacePressed = false;
		}

		for (Bullet b : bullets) {
			if(b.collided){
				bullets.remove(b);
				break;
			}
//			if(b!=null)    
				b.tick(-1);
		}

	}

	private void fire() {
		bullets.add(new Bullet(x, y));
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, w, h);

		for (Bullet b : bullets) {
			b.render(g,Color.gray);
		}

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
