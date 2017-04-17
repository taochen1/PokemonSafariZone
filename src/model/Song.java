package model;
/**
 * This simple class just holds a song's fileName.
 * 
 * @author Wang Tian
 *
 */

public class Song {
	private String fileName;
	public Song(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(){
		return fileName;
	}

}
