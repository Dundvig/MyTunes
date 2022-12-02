package BE;

import java.sql.Time;
import java.time.Duration;

public class Song {
    private int id;
    private String title; // Title of the track.
    private String artist; // Name of the artist.
    private String genre; // Musical genre of the track.
    private Time timer; // Total time of the song in seconds.
    private String url; // Path to the song in the recourse folder.

    // Constructor for our Song.
    public Song(int id, String title, String artist, String genre, Time timer, String url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.timer = timer;
        this.url = url;

    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Time getTimer() {
        return timer;
    }
    public void setTimer(Time timer){this.timer = timer;}

    public String getURL() {
        return url;
    }

    public void setURL(String URL) {
        //check if the file ends with .mp3 or .wav
        if (URL.endsWith(".mp3") || URL.endsWith(".wav")) {
            this.url = URL;
        }
    }
    @Override
    public String toString(){return title + ",  " + artist + ", " + genre + ", " + timer;}
}
