package DAL;

import BE.Song;

import java.net.URI;
import java.util.List;

public interface ISongDatabaseAccess {
    public List<Song> getAllSongs() throws Exception;

    public Song createSong(String title, String artist, String genre, String URL, int ID, int time) throws Exception;

    public void updateSong(Song song) throws Exception;

    public void deleteSong(Song song) throws Exception;

}
