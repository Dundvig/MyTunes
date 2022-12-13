package BLL.util;

import BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {
    public List<Song> search(List<Song> searchBase, String query) {
        //Make a list of songs to be viewed based on the user query.
        List<Song> searchResult = new ArrayList<>();

        for (Song song : searchBase) {
            if(compareToSongTitle(query, song) || compareToSongArtist(query, song))
            {
                searchResult.add(song);
            }
        }

        return searchResult;
    }

    private boolean compareToSongArtist(String query, Song song) {
        //Check if the user query contains anything from artists, that matches the list of songs in the application.
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToSongTitle(String query, Song song) {
        //Check if the user query contains anything from song titles, that matches the list of songs in the application.
        return song.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
