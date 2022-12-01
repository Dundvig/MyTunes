package BE;

public class Song {
    private final int time; // Total time of the song in seconds.
    private String title; // Title of the track.
    private String artist; // Name of the artist.
    private String genre; // Musical genre of the track.
    private String URL; // Path to the song in the recourse folder.
    private final int id;

    // Constructor for our Song.
    public Song(String title, String artist, String genre, String url, int id, int time) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.URL = url;
        this.id = id;
        this.time = time;
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        //check if the file ends with .mp3 or .wav
        if (URL.endsWith(".mp3") || URL.endsWith(".wav")) {
            this.URL = URL;
        }
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }
}
