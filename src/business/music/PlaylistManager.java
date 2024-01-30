package business.music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistManager {
	
	public Playlist getPlaylist(String name) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/assets/playlists/"+ name + ".m3u"))) {
        	
            Playlist playlist = new Playlist(name);
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                Track track = new Track(readLine);
                playlist.addTrack(track);
                readLine = bufferedReader.readLine();
            }
            return playlist;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public Playlist getAllTracks() {
		return null;
		
	}
	
	public void deletePlaylist(Playlist actPlaylist) {
		
	}
	
	public void savePlaylist(Playlist actPlaylist) {
		
	}
	
	/* 
	 * Not needed methods:
	 * 
	 * findPlaylist(name : String) : List<Playlist>
	 * savePlaylist(actPlaylist : Playlist) : void
	 * deletePlaylist(actPlaylist: Playlist) : void
	 * updatePlaylist(actPlaylist : Playlist) : void
	 */
}
