package tests;

import org.junit.Test;
import model.ImageLib;

public class imageTest {
	@Test
	public void testImage() {
		ImageLib imageLib = new ImageLib();
		imageLib.getImage("common1");
		imageLib.getImage("common1");
		imageLib.getImage("common1");
		imageLib.getImage("trainerMove");
	}
}
