package jeju.ac.kr;

import java.sql.*;

public class UserDao {
    public User get(int id) throws ClassNotFoundException, SQLException {
        //mysql 드라이버 읽고
        //Connection 맺고
        //sql작성하고
        //sql 실행하고
        //결과를 User에 매핑하고
        //자원을 해지하고
        //결과를 리턴한다.
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju","jeju","jejupw");
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
}
