package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import controller.PokemonGUI;
import model.Map;
import model.Trainer;
import model.ImageLib;;
/**
 * 
 * @author Renfei Sun
 *
 */
@SuppressWarnings("serial")
public class MapView extends JPanel implements Observer {
	private Trainer trainer;
	private int width, height;
	private int gameWidth, gameHeight;
	private Point currentPoint;
	private Image map1, map2, item;
	private ImageLib imageLib = PokemonGUI.imageLib;
	private ArrayList<Point> items;
	private double x, y;
	private int step;
	private Timer walkTimer = null, fishTimer = null;
	

	public MapView(Trainer trainer, int width, int height) {
		this.trainer = trainer;
		this.width = width;
		this.height = height;
		this.gameWidth = Map.gameWidth;
		this.gameHeight = Map.gameHeight;
		x = 0.0;
		y = 0.0;
		step = 0;
		walkTimer = new Timer(50, new MoveListener());
		fishTimer = new Timer(600, new MoveListener());
		walkTimer.setActionCommand("walk");
		fishTimer.setActionCommand("fish");
		this.setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		try {
			map1 = ImageIO.read(new File("images"+File.separator+"map.png"));
			map2 = ImageIO.read(new File("images"+File.separator+"map2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			item = ImageIO.read(new File("images"+File.separator+"picks.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		currentPoint = trainer.getCurrentPoint();
		items = trainer.getMap().getItemsPoint();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (trainer.getmapName().equals("map"))
			g2.drawImage(map1, 0, 0, this.width, this.height, this);
		else
			g2.drawImage(map2, 0, 0, this.width, this.height, this);
		for (Point pt : items) {
			g2.drawImage(item, pt.x * this.width / gameWidth, pt.y * this.height / gameHeight, this.width / gameWidth,
					this.height / gameHeight, this);
		}

		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		g2.drawString("Remaining steps: " + trainer.getRemainSteps(), 1100, 70);
		g2.drawString("Pokemons caught: " + trainer.getPokemonList().size(), 1100, 100);
		if(trainer.isFishing() && step == 0){
			g2.setFont(new Font("Times New Roman", Font.BOLD, 50));
			g2.drawString("Presss SPACE KEY to continue", this.width/4, this.height/2);
		}
		AffineTransform t = new AffineTransform();
		if(!trainer.isFishing())
			t.translate((currentPoint.x + x) * 21, (currentPoint.y + y) * 25);
		else
			t.translate((currentPoint.x) * 21, (currentPoint.y) * 25);
		t.scale(1, 1); // scale = 1
		g2.setTransform(t);
		if(!trainer.isFishing())
			g2.drawImage(imageLib.getImage("trainerMove" + trainer.getDirection() + step), 0, 0, this.width / gameWidth, this.height / gameHeight, this);
		else{
			g2.drawImage(imageLib.getImage("trainerFish" + trainer.getDirection() + step), 0, 0, this.width / gameWidth, this.height / gameHeight, this);
		}
	}

	public void move() {
		if (trainer.getDirection().equals("NORTH")) {
			x = 0;
			y = 1;
		} else if (trainer.getDirection().equals("SOUTH")) {
			x = 0;
			y = -1;
		} else if (trainer.getDirection().equals("WEST")) {
			x = 1;
			y = 0;
		} else {
			x = -1;
			y = 0;
		}
		walkTimer.start();
	}
	public void fish() {
		fishTimer.start();
	}

	private class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("walk")) {
				if (trainer.getDirection().equals("NORTH") || trainer.getDirection().equals("SOUTH")) {
					if (Math.abs(y) > 0.000001) {
						y = (y > 0) ? y - 0.2 : y + 0.2;
						step = (step + 1) % 3;
						repaint();
					} else {
						repaint();
						walkTimer.stop();
					}
				} else {
					if (Math.abs(x) > 0.000001) {
						x = (x > 0) ? x - 0.2 : x + 0.2;
						step = (step + 1) % 3;
						repaint();
					} else {
						repaint();
						walkTimer.stop();
					}
				}

			} else {
				if (step < 2) {
					getSelf().setFocusable(false);
					step++;
					repaint();
				} else {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					step = 0;
					repaint();
					fishTimer.stop();
					getSelf().setFocusable(true);
					getSelf().requestFocus();
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public MapView getSelf(){
		return this;
	}
}
