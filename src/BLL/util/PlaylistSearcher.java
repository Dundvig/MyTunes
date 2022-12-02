package BLL.util;

import BE.Playlist;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PlaylistSearcher {
    public List<Playlist> search(List<Playlist> searchBase, String query) {
        List<Playlist> searchResult = new ArrayList<>();

        for (Playlist playlist : searchBase) {
            if(compareToPlaylistTitle(query, playlist) || compareToPlaylistTime(query, playlist))
            {
                searchResult.add(playlist);
            }
        }

        return searchResult;
    }

    private boolean compareToPlaylistTime(String query, Playlist playlist) {
        return playlist.getTime().toString().contains(query);
    }

    private boolean compareToPlaylistTitle(String query, Playlist playlist) {
        return playlist.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
