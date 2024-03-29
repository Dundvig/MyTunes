package DAL.db;

import BE.Song;
import DAL.ISongDatabaseAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDatabaseAccess {

    private DatabaseConnector databaseConnector;

    public SongDAO_DB() { databaseConnector = new DatabaseConnector(); }

    @Override
    public List<Song> getAllSongs() throws Exception {
        //Get all songs and add it to a list
        ArrayList<Song> allSongs = new ArrayList<>();
        //Database connection and sql query.
        try (Connection conn = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Song";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //
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
        //Creating a song in the database by using an sql query.
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

        @Override
        public void updateSong(Song updatedsong) throws Exception {
            // Updates a song.
            try (Connection connection = databaseConnector.getConnection()) {
                //Update a song in the database by using an SQL query.
                String sql = "UPDATE Song SET Title = ?, Artist = ?, Genre = ?, Time = ?, URL = ? WHERE Id = ?";

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, updatedsong.getTitle());
                statement.setString(2, updatedsong.getArtist());
                statement.setString(3, updatedsong.getGenre());
                statement.setTime(4, updatedsong.getTimer());
                statement.setString(5, updatedsong.getURL());
                statement.setInt(6, updatedsong.getId());

                //Run the specified SQL statement.
                statement.executeUpdate();

            }
            catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not update song", ex);
            }
        }

        @Override
        public void deleteSong(Song song) throws Exception {
            // Deletes the selected song based on a specific id.
            try (Connection connection = databaseConnector.getConnection()) {
                String sql = "DELETE FROM PlaylistSongs WHERE SongId = ?; DELETE FROM Song WHERE id = ?";

                PreparedStatement statement = connection.prepareStatement(sql);

                //Don't know why it says 2 is wrong, but it isn't!
                statement.setInt(1, song.getId());
                statement.setInt(2, song.getId());

                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not delete song", ex);
            }
        }
}
