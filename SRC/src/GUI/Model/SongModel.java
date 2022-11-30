package GUI.Model;

import BE.Song;
import BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SongModel {
    private ObservableList<Song> songsToBeViewed;

    private SongManager songManager;

    // Constructor for the model
    public SongModel() throws Exception {
        songManager = new SongManager();
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    // Returns the Song
    public ObservableList<Song> getObservableSongs() {
        return songsToBeViewed;
    }

    // Searches for Songs in the DB
    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.searchSongs();
        songsToBeViewed.clear();
        songsToBeViewed.addAll(searchResults);
    }

    // Creates a new Song
    public void createSong(String title, String artist, String album, String genre, int year, String url, int id, int time) throws Exception {
        Song p = songManager.createSong(title, artist, album, genre, year, url, id, time);
        System.out.println(p.toString());
    }

    // Updates the Song
    public void updateSong(Song updatedSong) throws Exception {
        //Call BLL layer
        //Update the Song in the DB
        songManager.updateSong(updatedSong);

        //Update listview
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    //Deletes the selected Song
    public void deleteSong(Song deletedSong) throws Exception {
        songManager.deleteSong(deletedSong);

        songsToBeViewed.remove(deletedSong);
    }
}
