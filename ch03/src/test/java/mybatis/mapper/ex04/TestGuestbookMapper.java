package mybatis.mapper.ex04;

import domain.Guestbook;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestGuestbookMapper {
    private static GuestbookMapper guestbookMapper;

    @BeforeAll
    public static void setup() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config/ex04.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("sql/ddl.sql"));

        SqlSession session = sqlSessionFactory.openSession();
        guestbookMapper = session.getMapper(GuestbookMapper.class);
    }

    @Test
    public void testAutoGeneratedKey01()  {
        Guestbook guestbook = new Guestbook();
        guestbook.setName("guest");
        guestbook.setPassword("1234");
        guestbook.setMessage("hello world");

        guestbookMapper.insert01(guestbook);

        assertNotNull(guestbook.getNo());
    }

    @Test
    public void testAutoGeneratedKey02()  {
        Guestbook guestbook = new Guestbook();
        guestbook.setName("guest");
        guestbook.setPassword("1234");
        guestbook.setMessage("hello world");

        guestbookMapper.insert02(guestbook);

        assertNotNull(guestbook.getNo());
    }
}