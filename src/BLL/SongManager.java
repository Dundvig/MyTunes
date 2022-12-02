package BLL;


import BE.Song;
import BLL.util.SongSearcher;
import DAL.ISongDatabaseAccess;
import DAL.db.SongDAO_DB;

import java.sql.Time;
import java.util.List;

public class SongManager {

    private ISongDatabaseAccess songDAO;

    private SongSearcher songSearcher = new SongSearcher();

    public SongManager() {
        songDAO = new SongDAO_DB();
    }

    public List<Song> getAllSongs() throws Exception {
        return songDAO.getAllSongs();
    }

    // Searches all the Songs
    public List<Song> searchSongs() throws Exception {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, "query");
        return searchResult;
    }

    // Creates a new Song
    public Song createSong(String title, String artist, String genre, Time timer, String url) throws Exception {
        return songDAO.createSong(title, artist, genre, timer,  url);
    }

    // Updates the selected Song
    public void updateSong(Song updatedSong) throws Exception{
        songDAO.updateSong(updatedSong);
    }

    // Deletes the selected Song
    public void deleteSong(Song deletedSong) throws Exception {
        songDAO.deleteSong(deletedSong);
    }

}
