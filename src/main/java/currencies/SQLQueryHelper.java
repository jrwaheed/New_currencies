package currencies;

import java.sql.*;

public class SQLQueryHelper {

    public void updateTables() throws SQLException {

        String sql = "CALL newCreateOrUpdateTargetTables();";

        String myURL = "jdbc:mysql://localhost:3306/Exchange";
        String user = "root";
        String password = "TheHulk1*";

        try (
        Connection conn = DriverManager.getConnection(myURL, user, password);
        CallableStatement stmt = conn.prepareCall(sql);)
        {
            stmt.execute();
            stmt.close();
            System.out.println("Successfully executed mysql call.");
        } catch
            (SQLException ex)
        {
            System.out.println("Failure to call newCreateOrUpdateTargetTables()");
        }
    }



}
