package business.music;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Playlist {

	long id;
	String title;
	Date creationDate;
	String coverFile;
    private List<Track> tracks = new ArrayList<>();
	private String name;
	
	public int numberOfTracks() {
		return 0;
		
	}
	
    public Playlist(String name) {
        this.name = name;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public Track getTrack(int trackNumber) {
        return tracks.get(trackNumber);
    }

    public int getTrackCount() {
        return tracks.size();
    }

    public String getName() {
        return name;
    }

}
