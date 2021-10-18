package mybatis.mapper.ex05;

import domain.Guestbook;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGuestbookMapper {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setup() throws Throwable {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config/ex05.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("sql/ddl.sql"));
    }

    @Test
    @Order(1)
    public void testInsert()  {
        SqlSession session = sqlSessionFactory.openSession();
        GuestbookMapper guestbookMapper = session.getMapper(GuestbookMapper.class);

        Guestbook guestbook = new Guestbook();
        guestbook.setName("guest");
        guestbook.setPassword("1234");
        guestbook.setMessage("hello world");

        int count = guestbookMapper.insert(guestbook);
        session.commit();

        assertEquals(1, count);
    }

    @Test
    @Order(2)
    public void testUpdate()  {
        SqlSession session = sqlSessionFactory.openSession();
        GuestbookMapper guestbookMapper = session.getMapper(GuestbookMapper.class);

        Guestbook guestbook = new Guestbook();
        guestbook.setNo(1L);
        guestbook.setPassword("5678");
        guestbook.setMessage("HELLO WORLD");

        int count = guestbookMapper.update(guestbook);
        session.commit();

        assertEquals(1, count);
    }
}