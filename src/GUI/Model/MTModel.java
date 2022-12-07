package GUI.Model;

public class MTModel {

    private SongModel songModel;
    private PlaylistModel playlistModel;

    public MTModel() throws Exception {
        songModel = new SongModel();
        playlistModel = new PlaylistModel();
    }

    public SongModel getSongModel(){
        return songModel;
    }

    public PlaylistModel getPlaylistModel(){
        return playlistModel;
    }
}
