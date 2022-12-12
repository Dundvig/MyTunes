package DAL.db;

import BE.Song;
import DAL.ISongDatabaseAccess;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDatabaseAccess {

    private DatabaseConnector databaseConnector;

    public SongDAO_DB() { databaseConnector = new DatabaseConnector(); }

    //Get all songs and add it to a list
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
                String genre = rs.getString("Genre");
                Time timer = rs.getTime("Time");
                String url = rs.getString("URL");

                Song song = new Song(id, title, artist, genre, timer, url);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    //Create a new song
    @Override
    public Song createSong(String title, String artist, String genre, Time timer, String URL) throws Exception {
        
            String sql = "INSERT INTO song (Title, Artist, Genre, Time, URL) VALUES (?,?,?,?,?);";

            try (Connection connection = databaseConnector.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


                //Bind parameters.

                statement.setString(1, title);
                statement.setString(2, artist);
                statement.setString(3, genre);
                statement.setTime(4, timer);
                statement.setString(5, URL);

                //Run the specified SQL statement.
                statement.executeUpdate();

                //Get the generated ID from the DB
                ResultSet rs = statement.getGeneratedKeys();
                int id = 0;

                if(rs.next()) {
                    id = rs.getInt(1);
                }

                //Create song object and send up the layers.
                Song song = new Song(id, title, artist, genre, timer, URL);
                return song;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not create song", ex);
            }
        }

        // Updates a song.
        @Override
        public void updateSong(Song updatedsong) throws Exception {
            try (Connection connection = databaseConnector.getConnection()) {

                String sql = "UPDATE Song SET Title = ?, Artist = ?, Genre = ?, Time = ?, URL = ? WHERE Id = ?";

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, updatedsong.getTitle());
                statement.setString(2, updatedsong.getArtist());
                statement.setString(3, updatedsong.getGenre());
                statement.setTime(4, updatedsong.getTimer());
                statement.setString(5, updatedsong.getURL());
                statement.setInt(6, updatedsong.getId());


                statement.executeUpdate();

            }
            catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not update song", ex);
            }
        }

        // Deletes the selected song.
        @Override
        public void deleteSong(Song song) throws Exception {
            try (Connection connection = databaseConnector.getConnection()) {
                String sql = "DELETE FROM Song WHERE id = ?";

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, song.getId());

                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not delete song", ex);
            }
        }
}
