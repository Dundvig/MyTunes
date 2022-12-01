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
                String url = rs.getString("URL");
                int time = rs.getInt("Time");

                Song song = new Song(title, artist, genre, url, id, time);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    @Override
    public Song createSong(String title, String artist, String genre, String URL, int Id, int time) throws Exception {
        
            String sql = "INSERT INTO song (Title, Artist, Genre, URL, Time) VALUES (?,?,?,?,?);";

            try (Connection connection = databaseConnector.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


                //Bind parameters.
                statement.setString(1, title);
                statement.setString(2, artist);
                statement.setString(3, genre);
                statement.setString(4, URL);
                statement.setInt(5, time);

                //Run the specified SQL statement.
                statement.executeUpdate();

                //Get the generated ID from the DB
                ResultSet rs = statement.getGeneratedKeys();
                int id = 0;

                if(rs.next()) {
                    id = rs.getInt(1);
                }

                //Create song object and send up the layers.
                Song song = new Song(genre, title, artist, URL, id, time);
                return song;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not create song", ex);
            }
        }

        // Updates a song.
        @Override
        public void updateSong(Song song) throws Exception {
            try (Connection connection = databaseConnector.getConnection()) {

                String sql = "UPDATE song SET Title = ?, Artist = ?, Genre = ?, URL = ?, Time = ? WHERE Id = ?";

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, song.getTitle());
                statement.setString(2, song.getArtist());
                statement.setString(3, song.getGenre());
                statement.setInt(4, song.getTime());
                statement.setString(5, song.getURL());
                statement.setInt(6, song.getId());


                statement.executeUpdate();

            }
            catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }

        // Deletes the selected song.
        @Override
        public void deleteSong(Song song) throws Exception {
            try (Connection connection = databaseConnector.getConnection()) {
                String sql = "DELETE FROM song WHERE id = ?";

                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, song.getId());

                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }
}
