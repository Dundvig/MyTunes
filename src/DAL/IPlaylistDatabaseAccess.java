package DAL;

import BE.Playlist;
import BE.Song;

import java.sql.Time;
import java.util.List;

public interface IPlaylistDatabaseAccess {

    List<Playlist> getAllPlaylists() throws Exception;

    void deletePlaylistSong(Playlist playlist, Song song);

    // Creates a new playlist.
    Playlist createPlaylist(String title, int id, Time time, int totalSongs) throws Exception;

    void updatePlaylist(Playlist playlist) throws Exception;

    void deletePlaylist(Playlist playlist) throws Exception;

    void addSongToPlaylist(Playlist playlist, Song song) throws Exception;

    List<Song> getAllPlaylistSongs(Playlist playlist);
    void swapSong(Playlist playlist, int songId, int toIndex);

}
