package jeju.ac.kr;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;


public class UserDaoTest {

    private UserDao userDao;
    private DaoFactory daoFactory;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        int id= 1;
        User user = userDao.get(id);
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("jiwon"));
        assertThat(user.getPassword(), is("1234"));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1111");
        Integer id = userDao.insert(user);

        User insertedUser = userDao.get(id);
        assertThat(insertedUser.getId(), is(id));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

}
