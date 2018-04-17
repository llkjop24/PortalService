package jeju.ac.kr;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDaoTest {

    private UserDao userDao;
    private UserDao hallaUserDao;

    @Before
    public void setup(){
        userDao = new JejuUserDao();
        hallaUserDao = new HallaUserDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        int id =1;
        User user = userDao.get(id);
        assertThat(user.getId(),is(1));
        assertThat(user.getName(),is("jiwon"));
        assertThat(user.getPassword(), is("1234"));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("hulk");
        user.setName("1111");
        Integer id = userDao.insert(user);

        User insertedUser = userDao.get(id);
        assertThat(insertedUser.getId(),is(id));
        assertThat(insertedUser.getName(),is(user.getName()));
        assertThat(insertedUser.getPassword(),is(user.getPassword()));


    }
    @Test
    public void hallaGet() throws SQLException, ClassNotFoundException {
        int id =1;
        User user = userDao.get(id);
        assertThat(user.getId(),is(1));
        assertThat(user.getName(),is("jiwon"));
        assertThat(user.getPassword(), is("1234"));
    }

    @Test
    public void hallaAdd() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("hulk");
        user.setName("1111");
        Integer id = userDao.insert(user);

        User insertedUser = userDao.get(id);
        assertThat(insertedUser.getId(),is(id));
        assertThat(insertedUser.getName(),is(user.getName()));
        assertThat(insertedUser.getPassword(),is(user.getPassword()));
    }
}
