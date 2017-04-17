package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

/**
 * This is tile class, the main responsibility: contains the barrier in the
 * current tile, decide if this tile could be pass or not ,and generated a wild
 * pokemon
 * 
 * @author Renfei Sun
 *
 */
public class Tile implements Serializable {

	private static final long serialVersionUID = 1L;
	private Barrier barrier;
	private char mark;
	private Point point;

	public Tile(char mark, Point point) {
		switch (mark) {
		case 'R':
			this.barrier = Barrier.River;
			break;
		case 'B':
			this.barrier = Barrier.Barrier;
			break;
		case 'G':
			this.barrier = Barrier.Grass;
			break;
		case 'P':
			this.barrier = Barrier.Portal;
			break;
		default:
			this.barrier = Barrier.None;
		}
		this.mark = barrier.name().charAt(0);
		this.point = point;
	}

	/** check if current tile could be passed */
	public boolean isPassable() {
		return barrier.isPassable();
	}

	/** return if this is portal*/
	public boolean isPortal() {
		return mark == 'P';
	}
	
	/** return if this is river*/
	public boolean isRiver() {
		return mark == 'R';
	}

	/** generate a pokemon back to map class */
	public Pokemon generatePokemon() {
		Random generator = new Random();
		if (generator.nextInt(100) >= barrier.canGeneratePokemon())
			return null;
		else {
			FormPokemon wild = new FormPokemon();
			if(isRiver())
				return new Special();
			return wild.getCurrentPokemon();
		}
	}

	public Point getPosition() {
		return point;
	}

	public Barrier getBarrier() {
		return barrier;
	}

	@Override
	public String toString() {
		return mark + "";
	}

	/** these methods bellow are helping testing*/
	public void reach() {
		mark = ' ';
	}

	public boolean beenHere() {
		return mark == ' ';
	}
}
