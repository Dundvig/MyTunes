package BLL;


import BE.Song;
import DAL.ISongDatabaseAccess;
import DAL.db.SongDAO_DB;

import java.util.List;

public class SongManager {


    private ISongDatabaseAccess songDAO_DB;

        public SongManager() {
            songDAO_DB = new SongDAO_DB();
        }

        public List<Song> getAllSongs() throws Exception {
            return songDAO_DB.getAllSongs();
        }
    }
