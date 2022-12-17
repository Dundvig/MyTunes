package BLL;

import BE.Playlist;
import BE.Song;
import BLL.util.PlaylistSearcher;
import DAL.IPlaylistDatabaseAccess;
import DAL.db.PlaylistDAO_DB;

import java.sql.Time;
import java.util.List;

public class PlaylistManager {

    private PlaylistSearcher playlistSearcher = new PlaylistSearcher();

    private IPlaylistDatabaseAccess playlistDAO;

    public PlaylistManager() { playlistDAO = new PlaylistDAO_DB(); }

    public List<Playlist> getAllPlaylists() throws Exception {
        return playlistDAO.getAllPlaylists();
    }

    // Searches all the playlists
    public List<Playlist> searchPlaylists(String query) throws Exception {
        List<Playlist> allPlaylists = getAllPlaylists();
        List<Playlist> searchResult = playlistSearcher.search(allPlaylists, query);
        return searchResult;
    }

    // Creates a new playlist
    public Playlist createNewPlaylist(String title, int totalSongs, int id, Time time) throws Exception {
        return playlistDAO.createPlaylist(title, totalSongs, time, id);
    }

    // Updates the selected playlist
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception{
        playlistDAO.updatePlaylist(updatedPlaylist);
    }

    // Deletes the selected playlist
    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        playlistDAO.deletePlaylist(deletedPlaylist);
    }

    //Adds a song to a playlist
    public void addSongToPlaylist(Playlist playlist, Song song) throws Exception{
        playlistDAO.addSongToPlaylist(playlist, song);
        playlist.addSong(song);
    }

    //Gets all songs of a playlist
    public void getAllPlaylistSongs(Playlist playlist) {
        List<Song> allPlaylistSongs = playlistDAO.getAllPlaylistSongs(playlist);
        playlist.setSongs(allPlaylistSongs);
    }

    //Deletes a song from a playlist
    public void deletePlaylistSong(Playlist playlist, Song song) {
        playlistDAO.deletePlaylistSong(playlist, song);
    }

    public void swapSong(Playlist playlist, Song s1, Song s2) {
        System.out.println("playlist.getSongs() = " + playlist.getSongs());
        int i1 = playlist.getSongs().indexOf(s1);
        int i2 = playlist.getSongs().indexOf(s2);
        System.out.println("playlist = " + playlist);
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        playlistDAO.swapSong(playlist, s1.getId(), i2);
        playlistDAO.swapSong(playlist, s2.getId(), i1);
    }
}
