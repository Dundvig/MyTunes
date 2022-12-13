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
        //Get all songs from the list of all songs.
        return songDAO.getAllSongs();
    }

    public List<Song> searchSongs(String query) throws Exception {
        // Searches all the Songs
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }

    public Song createSong(String title, String artist, String genre, Time timer, String url) throws Exception {
        // Creates a new Song
        return songDAO.createSong(title, artist, genre, timer,  url);
    }


    public void updateSong(Song updatedSong) throws Exception{
        // Updates the selected Song
        songDAO.updateSong(updatedSong);
    }

    public void deleteSong(Song deletedSong) throws Exception {
        // Deletes the selected Song
        songDAO.deleteSong(deletedSong);
    }

}
