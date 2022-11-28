package DAL.db;

import BE.Song;
import DAL.ISongDatabaseAccess;

import java.util.List;

public class SongDAO_DB implements ISongDatabaseAccess {

    private DatabaseConnector databaseConnector;

    public SongDAO_DB() { databaseConnector = new DatabaseConnector(); }

    @Override
    public List<Song> getAllSongs() throws Exception {
        return null;
    }

    @Override
    public Song createSong(String title, String artist, String album, String genre, int year, String URL, int ID, int time) throws Exception {
        return null;
    }

    @Override
    public void updateSong(Song song) throws Exception {

    }

    @Override
    public void deleteSong(Song song) throws Exception {

    }
}
