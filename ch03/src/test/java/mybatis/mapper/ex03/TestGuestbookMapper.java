package mybatis.mapper.ex03;

import domain.Guestbook;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGuestbookMapper {
    private static GuestbookMapper guestbookMapper;

    @BeforeAll
    public static void setup() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config/ex03.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("sql/ddl.sql"));

        SqlSession session = sqlSessionFactory.openSession();
        guestbookMapper = session.getMapper(GuestbookMapper.class);
    }

    @Test
    public void testInsert()  {
        Guestbook guestbook = new Guestbook();
        guestbook.setName("guest");
        guestbook.setPassword("1234");
        guestbook.setMessage("Hello World");

        int count = guestbookMapper.insert(guestbook);
        assertEquals(1, count);
    }
}