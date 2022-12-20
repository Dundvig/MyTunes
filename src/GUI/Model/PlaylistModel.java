package GUI.Model;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.util.List;

public class PlaylistModel {
    private ObservableList<Playlist> playlistsToBeViewed;
    private ObservableList<Song> playlistSongsToBeViewed;
    private PlaylistManager playlistManager;
    private Playlist selectedPlaylist;

    // Constructor for the model
    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
        playlistSongsToBeViewed = FXCollections.observableArrayList();
    }

    // Returns the playlist
    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistsToBeViewed;
    }

    // Returns songs of a playlist
    public ObservableList<Song> getObservablePlaylistSongs() { return playlistSongsToBeViewed; }

    // Searches for playlists in the DB
    public void searchPlaylist(String query) throws Exception {
        List<Playlist> searchResults = playlistManager.searchPlaylists(query);
        playlistsToBeViewed.clear();
        playlistsToBeViewed.addAll(searchResults);
    }

    // Creates a new playlist
    public void createNewPlaylist(String title, Time time, int totalSongs, int id) throws Exception {
        Playlist p = playlistManager.createNewPlaylist(title, totalSongs, id, time);
        System.out.println(p.toString());

        //Update listview
        playlistsToBeViewed.clear();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    // Updates the playlist
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        //Call BLL layer
        //Update the playlist in the DB
        playlistManager.updatePlaylist(updatedPlaylist);

        //Update listview
        playlistsToBeViewed.clear();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    //Deletes the selected playlist
    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        playlistManager.deletePlaylist(deletedPlaylist);

        playlistsToBeViewed.remove(deletedPlaylist);
    }

    //Get the selected playlist
    public Playlist getSelectedPlaylist() {
        return selectedPlaylist;
    }

    //Set the selected playlist
    public void setSelectedPlaylist(Playlist selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    //Add song to the selected playlist
    public void addSongToPlaylist(Playlist selectedPlaylist, Song selectedSong) throws Exception {
        playlistManager.addSongToPlaylist(selectedPlaylist,selectedSong);
    }

    //Get all songs of a selected playlist
    public void getAllPlaylistSongs(Playlist playlist) {
        playlistManager.getAllPlaylistSongs(playlist);
    }

    //Delete song from a playlist
    public void deletePlaylistSong(Playlist playlist, Song song) {
        playlistManager.deletePlaylistSong(playlist, song);
        playlistSongsToBeViewed.remove(song);
    }

    //Swap song position in playlist
    public void swapSong(Playlist playlist, Song s1, Song s2) {
        playlistManager.swapSong(playlist, s1, s2);
    }
}
