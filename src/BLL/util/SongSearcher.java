package BLL.util;

import BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {

    // This checks if the song is eligible to be added to the sing list.
    public List<Song> search(List<Song> searchBase, String query) {
        List<Song> searchResult = new ArrayList<>();

        for (Song song : searchBase) {
            if(compareToSongTitle(query, song) || compareToSongArtist(query, song))
            {
                searchResult.add(song);
            }
        }

        return searchResult;
    }

    // Compares the song artist to the given string.
    private boolean compareToSongArtist(String query, Song song) {
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    // Compares the song title to the given string.
    private boolean compareToSongTitle(String query, Song song) {
        return song.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
