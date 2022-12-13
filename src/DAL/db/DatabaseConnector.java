package DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DatabaseConnector {
    private SQLServerDataSource dataSource;

    public DatabaseConnector()
    {
        //Setting the needed information for connection to the database.
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("My_Tunes_4");
        dataSource.setUser("CSe22A_4");
        dataSource.setPassword("CSe22A_4");
        dataSource.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        //Connecting to the database.
        return dataSource.getConnection();
    }
}
