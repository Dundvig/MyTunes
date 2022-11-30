package BE;

import java.util.List;

public class Playlist {

    //private List<Song>

    private final int duration; // The entire duration of the playlist.

    private String title; // The title of the playlist.

    private int totalSongs; // Total amount of songs in the playlist.

    private int id; // The id of the playlist.

    // Constructor for the playlist.
    public Playlist(int id, String title, int totalSongs, int duration) {
        this.duration = duration;
        this.id = id;
        this.totalSongs = totalSongs;
        this.title = title;
    }

    public int getDuration() {
        return duration;
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
}
