package BE;

import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    //private List<Song>

    private final Time time; // The entire duration of the playlist.

    private String title; // The title of the playlist.

    private int totalSongs; // Total amount of songs in the playlist.

    private int id; // The id of the playlist.

    public List<Song> songs = new ArrayList<>();

    // Constructor for the playlist.
    public Playlist(int id, String title, Time time, int totalSongs) {
        this.id = id;
        this.time = time;
        this.totalSongs = totalSongs;
        this.title = title;
    }

    public Time getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalSongs() {
        return totalSongs;
    }

    public void setTotalSongs(int totalSongs) {
        this.totalSongs = totalSongs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString(){ return title +", " + totalSongs + ", " + time; }
}
