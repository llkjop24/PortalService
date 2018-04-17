package jeju.ac.kr;

import com.sun.tools.internal.xjc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuConnecionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju","jeju","jejupw");
    }
}
