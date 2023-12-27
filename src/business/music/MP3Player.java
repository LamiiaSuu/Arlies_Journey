package business.music;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class MP3Player {
    private static final String STANDARD_PLAYLIST = "playlist1";
    private static final float STANDARD_VOLUME = 0.25f;

    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private Playlist playlist;
    private Timeline endOfTrackChecker;
    private List<Integer> shuffledTracks;
    private Object[] shuffledTracksArray;
    private int trackNumber = 0;
    
    private SimpleBooleanProperty paused;
    private SimpleBooleanProperty shuffle;
    public SimpleFloatProperty volume;
    private SimpleObjectProperty<Track> currentlyPlayingTrack;

    

    public void start() {
    	paused = new SimpleBooleanProperty(true);
    	shuffle = new SimpleBooleanProperty(false);
    	currentlyPlayingTrack = new SimpleObjectProperty<Track>();
    	
    	
        playlist = new PlaylistManager().getPlaylist(STANDARD_PLAYLIST);
        minim = new SimpleMinim(true);
        audioPlayer = minim.loadMP3File(playlist.getTrack(trackNumber).getPath());
        volume = new SimpleFloatProperty();
        
        volume(STANDARD_VOLUME);
        endOfTrackChecker = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> checkEndOfTrack())
        );
        endOfTrackChecker.setCycleCount(Animation.INDEFINITE);
        endOfTrackChecker.play();
    }
    
    private void checkEndOfTrack() {
        // Check if the audio has reached the end
        if (audioPlayer != null) {
            if(!audioPlayer.isPlaying() && !paused.get()) {
            	next();
            }
        }
    }   
    
    public void toggleShuffle() {
        if (shuffle.get()) {
            // Shuffling is currently enabled
            // Find the original order index of the currently playing song
            int originalOrderIndex = findOriginalOrderIndex();
            
            // Update trackNumber to the original order index
            trackNumber = (originalOrderIndex != -1) ? originalOrderIndex : 0;
            
            shuffle.set(false);
        } else {
            // Shuffling is currently disabled
            shuffleTracks();
            trackNumber = 0;
            shuffle.set(true);
        }
    }
    
    private int findOriginalOrderIndex() {
        Track currentTrack = getCurrentTrack();
        if (currentTrack != null) {
            for (int i = 0; i < playlist.getTrackCount(); i++) {
                if (currentTrack.getPath().equals(playlist.getTrack(i).getPath())) {
                    return i;
                }
            }
        }
        return -1; // Track not found in the original order
    }
    
    private void shuffleTracks() {
        shuffledTracks = new ArrayList<>();
        shuffledTracks.add(trackNumber);

        Random random = new Random();
        int randomTrack;
        while (shuffledTracks.size() < playlist.getTrackCount()) {
            do {
                randomTrack = random.nextInt(playlist.getTrackCount());
            } while (shuffledTracks.contains(randomTrack));

            shuffledTracks.add(randomTrack);
        }

        shuffledTracksArray = shuffledTracks.toArray();
    }
    
//    private void checkEndOfTrack() {
//        // Check if the audio has reached the end
//        if (audioPlayer != null) {
//            double currentPosition = audioPlayer.position();
//            double trackLength = playlist.getTrack(trackNumber).getLength() * 1000;
//            System.out.println(audioPlayer.position() + " " + playlist.getTrack(trackNumber).getLength() + audioPlayer.isPlaying());
//
//            // Allow for a small threshold to account for variations in playback time
//            double threshold = 0; // Adjust as needed
//            if (currentPosition >= trackLength - threshold) {
//                next();
//            }
//        }
//    }   
    
    public boolean isShuffleActive() {
    	
    	return shuffle.get();
    }

    public void next() {
    	if(!shuffle.get()) {
    		if (trackNumber < playlist.getTrackCount() - 1) {
                trackNumber++;
                playCurrentTrack();
            }
            else {
            	trackNumber = 0;
            	playCurrentTrack();
            }
    	}
    	else {
    		if (trackNumber < playlist.getTrackCount() - 1) {
                trackNumber++;
                playShuffledTrack();
            }
            else {
            	trackNumber = 0;
            	playShuffledTrack();
            }
    	}
    }

    public void prev() {
        if (audioPlayer.position() >= 3000) {
            playCurrentTrack();
        } else if (trackNumber == 0) {
            trackNumber = (playlist.getTrackCount()-1);
            playCurrentTrack();
        }
        	
        	else {
        
            trackNumber--;
            playCurrentTrack();
        }
    }

    public void play(String playlistName) {
        playlist = new PlaylistManager().getPlaylist(playlistName);
        trackNumber = 0;
        playCurrentTrack();
    }

    private void playCurrentTrack() {
        audioPlayer.pause();
        audioPlayer = minim.loadMP3File(playlist.getTrack(trackNumber).getPath());
        currentlyPlayingTrack.set(playlist.getTrack(trackNumber));
        
        audioPlayer.play();
        volume(volume.get());
    }
    
    private void playShuffledTrack() {
        audioPlayer.pause();
        audioPlayer = minim.loadMP3File(playlist.getTrack((int) shuffledTracksArray[trackNumber]).getPath());
        currentlyPlayingTrack.set(playlist.getTrack((int) shuffledTracksArray[trackNumber]));
        
        audioPlayer.play();
        volume(volume.get());
    }

    public void stop() {
        if (audioPlayer != null) {
            audioPlayer = null;
        }
        if (minim != null) {
            minim.stop();
        }
    }

    public void pause() {
        if (audioPlayer.isPlaying()) {
        	paused.set(true);
            audioPlayer.pause();
        } else {
        	paused.set(false);
            audioPlayer.play();
        }
    }

    public void volume(float newVolume) {
        // Convert the volume to decibels
        float decibels = (float) (Math.log10(newVolume) * 20);
        volume.set(newVolume);
        // Set the gain in decibels
        audioPlayer.setGain(decibels);
    }
    
    public double getCurrentPosition() {
        if (audioPlayer != null) {
            return audioPlayer.position();
        }
        return 0.0; // Return 0 if no audio player or not playing
    }

    public Track getCurrentTrack() {
        if (shuffle.get()) {
            return playlist.getTrack((int) shuffledTracksArray[trackNumber]);
        } else {
            return playlist.getTrack(trackNumber);
        }
    }
    
    public void seek(double position) {
        if (audioPlayer != null) {
            boolean wasPlaying = audioPlayer.isPlaying();

            // Pause the audio player
            audioPlayer.pause();

            // Calculate the new position in milliseconds
            int newPositionMillis = (int) (position * 1000);

            // Seek to the new position
            audioPlayer.cue(newPositionMillis);

            // Resume playback if it was playing before seeking
            if (wasPlaying) {
                audioPlayer.play();
            }
        }
    }
    
    public SimpleBooleanProperty pausedProperty() {
    	return paused;
    }
    public SimpleBooleanProperty shuffleProperty() {
    	return shuffle;
    }
    public SimpleFloatProperty volumeProperty(){
    	return volume;
    }

    public SimpleObjectProperty<Track> currentlyPlayingTrackProperty(){
    	return currentlyPlayingTrack;
    }
    
    
}