package business.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class SongAnalyzer {

	
	public void analyzeSong() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/music/"+ name + ".m3u"))) {
//            Playlist playlist = new Playlist(name);
//       }     String readLine = bufferedReader.readLine();
//            while (readLine != null) {
//                Track track = new Track(readLine);
//                playlist.addTrack(track);
//                readLine = bufferedReader.readLine();
         
        } catch (IOException e) {
        	e.printStackTrace();
        }	
	}
	
}
