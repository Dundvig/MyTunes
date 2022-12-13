package BLL.util;

import BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {

    // This checks if the song is eligible to be added to the sing list.
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

    // Compares the song artist to the given string.
    private boolean compareToSongArtist(String query, Song song) {
        //Check if the user query contains anything from artists, that matches the list of songs in the application.
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    // Compares the song title to the given string.
    private boolean compareToSongTitle(String query, Song song) {
        //Check if the user query contains anything from song titles, that matches the list of songs in the application.
        return song.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
