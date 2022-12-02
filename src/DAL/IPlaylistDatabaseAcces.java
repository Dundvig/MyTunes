package DAL;

import BE.Playlist;
import BE.Song;

import java.sql.Time;
import java.time.Duration;
import java.util.List;

public interface IPlaylistDatabaseAcces {

    public List<Playlist> getAllPlaylists() throws Exception;

    // Creates a new playlist.
    Playlist createPlaylist(String title, int id, Time time, int totalSongs) throws Exception;

    public void updatePlaylist(Playlist playlist) throws Exception;

    public void deletePlaylist(Playlist playlist) throws Exception;

}
