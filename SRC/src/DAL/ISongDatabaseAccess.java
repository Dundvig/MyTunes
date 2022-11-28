package DAL;

import BE.Song;
import java.util.List;

public interface ISongDatabaseAccess {
    public List<Song> getAllSongs() throws Exception;

    public Song createSong(String title, String artist, String album, String genre, int year) throws Exception;

    public void updateSong(Song song) throws Exception;

    public void deleteSong(Song song) throws Exception;

}
