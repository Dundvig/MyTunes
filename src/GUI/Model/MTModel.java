package GUI.Model;

public class MTModel {

    private SongModel songModel;
    private PlaylistModel playlistModel;

    public MTModel() throws Exception {
        //Create new common models for the controllers.
        songModel = new SongModel();
        playlistModel = new PlaylistModel();
    }
//Getters for the models.
    public SongModel getSongModel(){
        return songModel;
    }

    public PlaylistModel getPlaylistModel(){
        return playlistModel;
    }
}
