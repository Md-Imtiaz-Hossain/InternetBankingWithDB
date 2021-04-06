package MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateMySQLConnection {
    public Connection connection;

    public Connection createConnection()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/abcd_bank?verifyServerCertificate=false&useSSL=true",
                    "root",
                    "rootDB600@660");
            System.out.println("Successfully connected with abcd_bank !!!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
