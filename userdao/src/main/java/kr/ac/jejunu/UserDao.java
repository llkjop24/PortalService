package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(int id) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user;


        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
        } finally {
            if(resultSet != null)
                try{
                resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                if(preparedStatement != null){
                   try{
                       resultSet.close();
                   }catch(SQLException e){
                       e.printStackTrace();
                    }
                }
                if(connection != null){
                try{
                    connection.close();
                    }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return user;
    }

    public Integer insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer id;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "insert into userinfo(name, password) values (?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getInt(1);

        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return id;
    }
}
