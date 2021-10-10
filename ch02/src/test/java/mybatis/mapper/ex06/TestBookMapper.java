package mybatis.mapper.ex06;

import domain.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBookMapper {
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
    public void testInsertSQL()  {
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.insert("mybatis.mapper.ex06.Book.insert", "마이바티스 연습");
        session.commit();

        assertEquals(1, count);
    }

    @Test
    @Order(2)
    public void testSelectSQL()  {
        SqlSession session = sqlSessionFactory.openSession();
        Book book = session.selectOne("mybatis.mapper.ex06.Book.findByNo", 1L);

        assertEquals("마이바티스 연습", book.getName());
    }

}
