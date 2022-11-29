package BLL;

import BE.Playlist;
import BLL.util.PlaylistSearcher;
import DAL.IPlaylistDatabaseAcces;
import DAL.db.PlaylistDAO_DB;

import java.util.List;

public class PlaylistManager {

    private PlaylistSearcher playlistSearcher = new PlaylistSearcher();

    private IPlaylistDatabaseAcces playlistDAO;

    public PlaylistManager() { playlistDAO = new PlaylistDAO_DB(); }

    public List<Playlist> getAllPlaylists() throws Exception {
        return playlistDAO.getAllPlaylists();
    }

    // Searches all the playlists
    public List<Playlist> searchPlaylists() throws Exception {
        List<Playlist> allPlaylists = getAllPlaylists();
        List<Playlist> searchResult = playlistSearcher.search(allPlaylists, "query");
        return searchResult;
    }

    // Creates a new playlist
    public Playlist createNewPlaylist(String title, int totalSongs, int id, int time) throws Exception {
        return playlistDAO.createPlaylist(title, totalSongs, id, time);
    }

    // Updates the selected playlist
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception{
        playlistDAO.updatePlaylist(updatedPlaylist);
    }

    // Deletes the selected playlist
    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        playlistDAO.deletePlaylist(deletedPlaylist);
    }


}
