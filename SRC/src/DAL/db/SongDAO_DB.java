package DAL.db;

import BE.Song;
import DAL.ISongDatabaseAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDatabaseAccess {

    private DatabaseConnector databaseConnector;

    public SongDAO_DB() { databaseConnector = new DatabaseConnector(); }

    @Override
    public List<Song> getAllSongs() throws Exception {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Song";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String album = rs.getString("Album");
                String genre = rs.getString("Genre");
                String url = rs.getString("URL");
                int year = rs.getInt("Year");
                int time = rs.getInt("Time");

                Song song = new Song(title, artist, album, genre, year, url, id, time);
                allSongs.add(song);
            }
            return allSongs;
        }
    }

    @Override
    public Song createSong(String title, String artist, String album, String genre, int year, String URL, int ID, int time) throws Exception {
        return null;
    }

    @Override
    public void updateSong(Song song) throws Exception {

    }

    @Override
    public void deleteSong(Song song) throws Exception {

    }
}
