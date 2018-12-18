package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Game;

public class Mob {
	public float x, y;
	public int w = 16, h = 16;
	public float vx, vy;
	public static final float SPEED = 0.3f;
	private boolean fire = false;
	private int tickCount=0;
	public  int mobCount;
	
	List<Bullet> bullets = new ArrayList<Bullet>();
	Random random=new Random();
	public boolean collided=false;
	
	public Mob(int x,int y,int count){
		this.x=x;
		this.y=y;
		this.mobCount=count;
		vx=SPEED;
	}
	
	public boolean checkCollision(int centerX,int centerY){
		if(centerX>x && centerX<(int)(x+w)){
			if(centerY>y && centerY<(int)(y+h)){
				return true;
			}
		}
		return false;
	}
	
	public void checkAgainstAll(ArrayList<Bullet> bulletList){
		for(Bullet b:bulletList){
			int xx=(int)(b.x+b.w);
			int yy=(int)(b.y+b.h);
			if(checkCollision(xx, yy)){
				collided=true;
				b.collided=true;
			}
		}
	}
	
	private void fire() {
		bullets.add(new Bullet(x, y));
	}
	
	public void tick(ArrayList<Bullet> bulletList){
		//setting vx,vy
		if(x>Game.WIDTH-w){
			x=Game.WIDTH-w;
			vx=-vx;
		}
		if(x<0){
			x=0;
			vx=-vx;
		}
		
		//collision test with bullet
		checkAgainstAll(bulletList);
		
		
		tickCount++;
		
		if(tickCount%10==0){
			x += vx;
		}
		
		if(random.nextInt(40000)==1){
			fire=true;
		}
		
		
		
		if(fire){
			fire();
			fire=false;
		}
		
		for (Bullet b : bullets) {
			b.tick(1);
		}

		
	}
	
	
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, w, h);

		for (Bullet b : bullets) {
			b.render(g,Color.red);
		}

	}

}
