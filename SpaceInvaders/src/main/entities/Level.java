package main.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import main.Game;

public class Level {
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	public Player player;
	public static final float SPEED = 0.3f;

	public int width, height;
	private final int renderingConst = 30;

	private boolean getLeft = false, getRight = false;

	public Level(int mobCount) {
		this.width = Game.WIDTH;
		this.height = Game.HEIGHT;
		init(mobCount);

	}

	private void init(int mobCount) {

		player = new Player(width / 2, height - 100);
		int divider = 10;
		for (int i = 0; i < mobCount; i++) {
			mobs.add(new Mob((i % divider) * renderingConst, (i / divider)
					* renderingConst, i));
		}

	}

	public void tick() {
//		checkCorners();
		for (Mob m : mobs) {
			if (m.collided) {
				mobs.remove(m);
				break;
			}
			if (m != null) {
				m.tick(player.bullets);
			}

		}

		player.tick();

	}

/*	private void checkCorners() {
		int w = 16;
		int h = 16;
		for (Mob m : mobs) {
			if (m.mobCount / 10 == 0) {
				System.out.println(m.mobCount+"hi");
				if (m.x > Game.WIDTH - w) {
//					getLeft = true;
//					break;
					for(Mob m1:mobs){
						m1.vx=-SPEED;
					}
					
				}
			}
		}
		if(getLeft){
			for(Mob m:mobs){
				m.vx=-SPEED;
			}
			
		}
	}
*/
	public void render(Graphics g) {
		for (Mob m : mobs) {
			m.render(g);
		}
		player.render(g);
	}

}
