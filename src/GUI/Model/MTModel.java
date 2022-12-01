package GUI.Model;

public class MTModel {

    private SongModel songModel;

    public MTModel() throws Exception {
        songModel = new SongModel();
    }

    public SongModel getSongModel(){
        return songModel;
    }
}
