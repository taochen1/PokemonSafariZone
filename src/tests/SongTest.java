package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Song;
/**
 * A JUnit test for Song class
 * @author Wang Tian
 *
 */
public class SongTest {

	@Test
	public void test() {
		Song aSong = new Song("test");
		assertEquals("test",aSong.getFileName());
	}

}
