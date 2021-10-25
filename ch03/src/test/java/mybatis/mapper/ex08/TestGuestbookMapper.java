package mybatis.mapper.ex08;

import domain.Guestbook;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestGuestbookMapper {
    private static GuestbookMapper guestbookMapper;

    @BeforeAll
    public static void setup() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config/ex08.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("sql/ddl.sql"));

        SqlSession sqlSession = sqlSessionFactory.openSession();
        guestbookMapper = sqlSession.getMapper(GuestbookMapper.class);

        Guestbook guestbook01 = new Guestbook();
        guestbook01.setName("guest01");
        guestbook01.setPassword("1234");
        guestbook01.setMessage("message01");
        guestbookMapper.insert(guestbook01);

        Guestbook guestbook02 = new Guestbook();
        guestbook02.setName("guest02");
        guestbook02.setPassword("1234");
        guestbook02.setMessage("message02");
        guestbookMapper.insert(guestbook02);
    }

    @Test
    public void testFindByNo01()  {
        Guestbook guestbook = guestbookMapper.findByNo01(1L);
        assertNotNull(guestbook.getRegDate());
    }

    @Test
    public void testFindByNo02()  {
        Guestbook guestbook = guestbookMapper.findByNo02(1L);
        assertNotNull(guestbook.getRegDate());
    }
}