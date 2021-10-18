package mybatis.mapper.ex06;

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
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config/ex06.xml"));

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
    public void testDelete()  {
        SqlSession session = sqlSessionFactory.openSession();
        GuestbookMapper guestbookMapper = session.getMapper(GuestbookMapper.class);


        int count = guestbookMapper.delete(1L);
        session.commit();

        assertEquals(1, count);
    }
}