package business.music;

import com.mpatric.mp3agic.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Track {

	long id;
	String title;
	int length; // Duration in seconds
	String albumTitle;
	String interpret;
	String soundFile;
	
	private String name;
    private String path;
    private String artist;
    private String genre;
    private byte[] image;

    public Track(String path) {
        this.path = "src/music/"+path;
        extractMetadata();
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
    
    public byte[] getImage() {
    	return image;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    private void extractMetadata() {
        try {
            Mp3File mp3File = new Mp3File(path);
            length = (int) mp3File.getLengthInSeconds();
            
            ID3v1 id3v1Tag = mp3File.getId3v1Tag();
            if (id3v1Tag != null) {
                artist = id3v1Tag.getArtist();
                genre = id3v1Tag.getGenreDescription();
                name = id3v1Tag.getTitle();
                
            }

            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                artist = id3v2Tag.getArtist();
                genre = id3v2Tag.getGenreDescription();
                name = id3v2Tag.getTitle();
                image = id3v2Tag.getAlbumImage();
            }

        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            // Handle exceptions or log them as needed
            Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
