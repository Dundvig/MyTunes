package GUI.Model;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import BLL.SongManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyTunesModel {
    private ObservableList<Song> songsToBeViewed;
    private ObservableList<Playlist> playlistsToBeViewed;
    private SongManager songManager;
    private PlaylistManager playlistManager;

    public MyTunesModel() throws Exception {
        songManager = new SongManager();
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
        playlistManager = new PlaylistManager();
        playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    public ObservableList<Song> getObservableSongs() {
        return songsToBeViewed;
    }
}