package BE;

public class Song {
    private String title; // Title of the track.
    private String artist; // Name of the artist.
    private String album; // Name of the album on which the song is featured.
    private String genre; // Musical genre of the track.
    private int year; // The year the song came out.
    private String URL; // Path to the song in the recourse folder.
    private final int ID;

    // Constructor for our Song.
    public Song(String title, String artist, String album, String genre, int year, String url, int id) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
        this.URL = url;
        this.ID = id;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getID() {
        return ID;
    }
}
