package DAL.db;

import BE.Playlist;
import BE.Song;
import DAL.IPlaylistDatabaseAccess;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDatabaseAccess {
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
                    Time time = resultSet.getTime("Time");
                    int totalSongs = resultSet.getInt("TotalSongs");

                    Playlist playlist = new Playlist(id, title, time, totalSongs);
                    allPlaylists.add(playlist);
                }
            }
        }
        return allPlaylists;
    }

    //Get the songs connected to the playlist
    public List<Song> getAllPlaylistSongs(Playlist playlist) {
        ArrayList<Song> allPlaylistSongs = new ArrayList<>();
        try (Connection conn = databaseConnector.getConnection()){
            //SQL to get the song connected to the playlist
            String sql = "SELECT s.* FROM PlaylistSongs ps INNER JOIN Song s ON s.Id = ps.SongId WHERE ps.PlaylistId = ? ORDER BY [Order] ASC;";

            //Prepare that shit to avoid injections
            PreparedStatement stmt = conn.prepareStatement(sql);

            //Bind that shit
            stmt.setInt(1, playlist.getId());

            //Get the things and set 'em
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String genre = rs.getString("Genre");
                Time time = rs.getTime("Time");
                String url = rs.getString("URL");

                //Add it to the list yo
                Song song = new Song(id, title, artist, genre, time, url);
                allPlaylistSongs.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not get playlist songs", e);
        }
        return allPlaylistSongs;
    }

    //Delete a song connected to a playlist
    @Override
    public void deletePlaylistSong(Playlist playlist, Song song) {
        try (Connection conn = databaseConnector.getConnection()){
            //SQL to get the right playlist and song
            String sql = "DELETE FROM PlaylistSongs WHERE PlaylistId = ? AND SongId = ?;";

            //Prepare that shit to avoid injections
            PreparedStatement stmt = conn.prepareStatement(sql);

            //bind it to the right thing
            stmt.setInt(1, playlist.getId());
            stmt.setInt(2, song.getId());

            //Do the thing
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not delete playlist song",e);
        }
    }

    public static void main (String[] args) throws Exception {

        PlaylistDAO_DB playlistDAO_db = new PlaylistDAO_DB();

        List<Playlist> allPlaylists = playlistDAO_db.getAllPlaylists();

        System.out.println(allPlaylists);
    }

    // Creates a new playlist.
    @Override
    public Playlist createPlaylist(String title, int id, Time time, int totalSongs) throws Exception {
        String sql = "INSERT INTO Playlist (Title, totalSongs, Time) VALUES (?,?,?);";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            //Bind parameters.
            statement.setString(1, title);
            statement.setInt(2, totalSongs);
            statement.setTime(3, time);

            //Run the specified SQL statement.
            statement.executeUpdate();

            //Get the generated ID from the DB
            ResultSet rs = statement.getGeneratedKeys();
            int Id = 0;

            if(rs.next()) {
                Id = rs.getInt(1);
            }

            //Create movie object and send up the layers.
            Playlist playlist = new Playlist(Id, title, time, totalSongs);
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

            String sql = "UPDATE Playlist SET Title = ?, TotalSongs = ? , Time = ? WHERE Id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, playlist.getTitle());
            statement.setInt(2, playlist.getTotalSongs());
            statement.setTime(3, playlist.getTime());
            statement.setInt(4, playlist.getId());


            statement.executeUpdate();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update playlist", ex);
        }
    }

    // Deletes the selected playlist.
    @Override
    public void deletePlaylist(Playlist playlist) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM PlaylistSongs WHERE PlaylistId = ?; DELETE FROM Playlist WHERE Id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            //Don't know why it says 2 is wrong, but it isn't!
            statement.setInt(1, playlist.getId());
            statement.setInt(2, playlist.getId());

            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete playlist", ex);
        }
    }

    // Add a song til a playlist.
    public void addSongToPlaylist(Playlist playlist, Song song) throws Exception {
        try (Connection conn = databaseConnector.getConnection()){
            //SQL to connect a playlist and song
            String sql = "INSERT INTO PlaylistSongs VALUES(?,?,?)";

            //Prepare that shit to avoid injection
            PreparedStatement stmt = conn.prepareStatement(sql);

            //Bind it to the right thing
            stmt.setInt(1, playlist.getId());
            stmt.setInt(2, song.getId());
            stmt.setInt(3, playlist.getSongs().size());

            //Do the thing
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add Song to playlist", ex);
        }
    }

    @Override
    public void swapSong(Playlist playlist, int songId, int toIndex) {
        try (Connection conn = databaseConnector.getConnection()){
            String sql = "UPDATE PlaylistSongs SET [Order] = ? WHERE PlaylistId = ? AND SongId = ?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, toIndex);
            stmt.setInt(2, playlist.getId());
            stmt.setInt(3, songId);

            int u = stmt.executeUpdate();
            System.out.println("u = " + u);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
