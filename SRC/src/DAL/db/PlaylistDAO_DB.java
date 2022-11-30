package DAL.db;

import BE.Playlist;
import DAL.IPlaylistDatabaseAcces;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDatabaseAcces {
    private DatabaseConnector databaseConnector;

    public PlaylistDAO_DB() { databaseConnector = new DatabaseConnector(); }

    // Gets all the playlists.
    @Override
    public List<Playlist> getAllPlaylists() throws Exception {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try(Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Playlist;";

            Statement statement = connection.createStatement();

            if(statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String title = resultSet.getString("Title");
                    int duration = resultSet.getInt("Duration");
                    int totalSongs = resultSet.getInt("TotalSongs");

                    Playlist playlist = new Playlist(id, title, duration, totalSongs);
                    allPlaylists.add(playlist);
                }
            }
        }
        return allPlaylists;
    }

    public static void main (String[] args) throws Exception {

        PlaylistDAO_DB playlistDAO_db = new PlaylistDAO_DB();

        List<Playlist> allPlaylists = playlistDAO_db.getAllPlaylists();

        System.out.println(allPlaylists);
    }

    // Creates a new playlist.
    @Override
    public Playlist createPlaylist(String title, int totalSongs, int ID, int duration) throws Exception {
        String sql = "INSERT INTO Playlist (Title, totalSongs, duration) VALUES (?,?,?);";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            //Bind parameters.
            statement.setString(1, title);
            statement.setInt(2, totalSongs);
            statement.setInt(3, duration);

            //Run the specified SQL statement.
            statement.executeUpdate();

            //Get the generated ID from the DB
            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next()) {
                id = rs.getInt(1);
            }

            //Create movie object and send up the layers.
            Playlist playlist = new Playlist(id, title, totalSongs, duration);
            return playlist;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create playlist", ex);
        }
    }

    // Updates a playlist.
    @Override
    public void updatePlaylist(Playlist playlist) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {

            String sql = "UPDATE Playlist SET Title = ?, TotalSongs = ? , Duration = ? WHERE Id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, playlist.getTitle());
            statement.setInt(2, playlist.getTotalSongs());
            statement.setInt(3, playlist.getDuration());
            statement.setInt(4, playlist.getId());


            statement.executeUpdate();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    // Deletes the selected playlist.
    @Override
    public void deletePlaylist(Playlist playlist) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Playlist WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, playlist.getId());

            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
}
