package songPlayer;

import model.Song;

/**
 * This class can play the sound effects. It has methods such as playThrowRock,
 * playThrowBall, playThrowBait, or playRunAway that can be called for the corresponding
 * conditions.
 * 
 * @author Wang Tian
 *
 */
public class SoundEffect {
	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	private Song throwRock;
	private Song throwBait;
	private Song throwSafariBall;
	private Song enterBattle;
	private Song runAway;
	private Song capturePokemon;
	private Song victory;
	private Song escape;
	private Song map;
	private ObjectWaitingForSongToEnd waiter;
	private SongPlayer player;

	//initiates all the songs.
	public SoundEffect() {
		throwRock = new Song("itemUsed.wav");
		throwBait = new Song("itemUsed.wav");
		throwSafariBall = new Song("spacemusic.au");
		enterBattle = new Song("battle2.mp3");
		runAway = new Song("tada.wav");
		capturePokemon = new Song("caughtAPokemon-.mp3");
		victory = new Song("victory.mp3");
		escape = new Song("tada.wav");
		map = new Song("mapSound.mp3");
		player = new SongPlayer();
	}

	//play throwing rock sound
	public void playThrowRock() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + throwRock.getFileName());
	}
	//play throwing bait sound
	public void playThrowBait() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + throwBait.getFileName());
	}
    //play trainer-runaway sound
	public void playRunAway() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + runAway.getFileName());
	}
    //play pokemon-caught sound
	public void playCapturePokemon() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + capturePokemon.getFileName());
	}
	//play throwing safari ball sound
	public void playSafariBall() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + throwSafariBall.getFileName());
	}
    //play battle-entering sound
	public void playEnterBattle() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + enterBattle.getFileName());
	}

	public void playMapSound() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + map.getFileName());
	}

	public void playVictory() {
		waiter = new ObjectWaitingForSongToEnd();
		player.playFile(waiter, baseDir + victory.getFileName());
	}

	private class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			if (eosEvent.fileName().contains(throwRock.getFileName())
					|| eosEvent.fileName().contains(throwBait.getFileName())
					|| eosEvent.fileName().contains(throwSafariBall.getFileName()))
				playEnterBattle();
			else if (eosEvent.fileName().contains(runAway.getFileName())
					|| eosEvent.fileName().contains(capturePokemon.getFileName())
					|| eosEvent.fileName().contains(escape.getFileName()))
				playMapSound();
		}
	}

}
