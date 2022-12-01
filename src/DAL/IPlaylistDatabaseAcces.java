package DAL;

import BE.Playlist;
import BE.Song;

import java.util.List;

public interface IPlaylistDatabaseAcces {

    public List<Playlist> getAllPlaylists() throws Exception;

    public Playlist createPlaylist(String title, int totalSongs, int ID, int time) throws Exception;

    public void updatePlaylist(Playlist playlist) throws Exception;

    public void deletePlaylist(Playlist playlist) throws Exception;

}
