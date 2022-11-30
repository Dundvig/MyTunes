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
    //Get a list of all songs
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
    public Song createSong(String title, String artist, String album, String genre, int year, String URL, int ID, int time) throws Exception{
        String sql = "INSERT INTO Song(Title, Artist, Album, Genre, Year, URL, Time) VALUES(?,?,?,?,?,?,?)";
        //Create a new song based on a list of parameters.
        // This song is going to be added to the database with a SQL Query.
        try(Connection connection = databaseConnector.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1,title);
            statement.setString(2,artist);
            statement.setString(3,album);
            statement.setString(4,genre);
            statement.setInt(5, year);
            statement.setString(6,URL);
            statement.setInt(7,time);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            ID = 0;

            if(resultSet.next()){
                ID = resultSet.getInt("Id");
            }

            Song song = new Song(title, artist, album, genre, year, URL, ID, time);
            return song;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }
    }

    @Override
    public void updateSong(Song song) throws Exception {
        String sql = "UPDATE Song SET Title = ?, Artist = ?, Album = ?, Genre = ?, Year = ?, URL = ?, Time = ? WHERE Id = ?";
        //Update a song in the database by changing some parameters.
        try(Connection connection = databaseConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,song.getTitle());
            preparedStatement.setString(2,song.getAlbum());
            preparedStatement.setString(3,song.getAlbum());
            preparedStatement.setString(4,song.getGenre());
            preparedStatement.setInt(5, song.getYear());
            preparedStatement.setString(6,song.getURL());
            preparedStatement.setInt(7,song.getTime());
            preparedStatement.setInt(8, song.getID());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }
    }

    @Override
    public Song deleteSong(Song song) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "DELETE FROM Song WHERE Id = ?";
            //Delete a song from the database.
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, song.getID());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows !=1){
                throw new Exception();
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
        return song;
    }
}
