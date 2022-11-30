package GUI.Model;

import BE.Song;
import BLL.SongManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyTunesModel {
        private ObservableList<Song> songsToBeViewed;
        private SongManager songManager;

        public MyTunesModel() throws Exception {
            songManager = new SongManager();
            songsToBeViewed = FXCollections.observableArrayList();
            songsToBeViewed.addAll(songManager.getAllSongs());
        }

        public ObservableList<Song> getObservableSongs() {
            return songsToBeViewed;
        }
}