package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import main.entities.Level;
import main.entities.Mob;
import main.entities.Player;

public class Game extends Canvas implements Runnable {
	public static int WIDTH = 500;
	public static int HEIGHT = 500;
	private boolean running = false;

	private Level level;

	public Game() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		// setFocusable(true);
		init();

	}

	private void init() {
		level = new Level(50);
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void run() {
		while (running) {
			tick();
			render();
		}
	}

	private void tick() {
		level.tick();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		level.render(g);	
		g.dispose();
		bs.show();

	}

	public static void main(String args[]) {
		Game game = new Game();
		JFrame frame = new JFrame("SPace Invaders");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.addKeyListener(game.level.player);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

}
