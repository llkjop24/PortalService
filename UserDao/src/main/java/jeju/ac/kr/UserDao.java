package jeju.ac.kr;

import java.sql.*;

public abstract class UserDao {
    public User get(int id) throws ClassNotFoundException, SQLException {
        //mysql 드라이버 읽고
        //Connection 맺고
        //sql작성하고
        //sql 실행하고
        //결과를 User에 매핑하고
        //자원을 해지하고
        //결과를 리턴한다.
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public Integer insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name,password) values(?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Integer id = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return id;
    }

    abstract Connection getConnection() throws ClassNotFoundException, SQLException ;
//    {
//        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost/jeju","jeju","jejupw");
//    }
}
