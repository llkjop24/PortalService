package kr.ac.jejunu;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;


public class UserDaoTest {

    private UserDao userDao;
    private DaoFactory daoFactory;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext applicationContext = new GenericXmlApplicationContext("classpath:daoFactory.xml");
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        int id= 1;
        User user = userDao.get(id);
        MatcherAssert.assertThat(user.getId(), CoreMatchers.is(1));
        MatcherAssert.assertThat(user.getName(), CoreMatchers.is("jiwon"));
        MatcherAssert.assertThat(user.getPassword(), CoreMatchers.is("1234"));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1111");
        Integer id = userDao.insert(user);

        User insertedUser = userDao.get(id);
        MatcherAssert.assertThat(insertedUser.getId(), CoreMatchers.is(id));
        MatcherAssert.assertThat(insertedUser.getName(), CoreMatchers.is(user.getName()));
        MatcherAssert.assertThat(insertedUser.getPassword(), CoreMatchers.is(user.getPassword()));
    }

}
