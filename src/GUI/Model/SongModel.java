package GUI.Model;

import BE.Song;
import BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.util.List;

public class SongModel {
    private ObservableList<Song> songsToBeViewed;

    private SongManager songManager;

    private Song selectedSong;

    public SongModel() throws Exception {
        //Constructor for the model
        songManager = new SongManager();
        //Add all songs to the view.
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getObservableSongs() {
        //Returns the Song
        return songsToBeViewed;
    }

    public void searchSong(String query) throws Exception {
        //Searches for Songs in the DB
        List<Song> searchResults = songManager.searchSongs(query);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(searchResults);
    }

    public void createSong(String title, String artist, String genre, Time timer, String url) throws Exception {
        //Creates a new Song
        Song s = songManager.createSong(title, artist, genre, timer, url);
        songsToBeViewed.add(s);
        songsToBeViewed.clear();
        songsToBeViewed.addAll(songManager.getAllSongs());
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

    //Get the selected song
    public Song getSelectedSong(){return selectedSong;}

    //Set the selected song
    public void setSelectedSong(Song selectedSong){
        this.selectedSong = selectedSong;
    }

}
