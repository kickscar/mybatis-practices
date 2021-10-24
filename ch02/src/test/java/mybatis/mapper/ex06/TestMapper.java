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
public class TestMapper {
    private static SqlSession sqlSession;

    @BeforeAll
    public static void setup() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config/ex06.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("sql/ddl.sql"));

        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    @Order(1)
    public void testInsert()  {
        int count = sqlSession.insert("mybatis.mapper.ex06.Book.insert", "마이바티스 연습");
        assertEquals(1, count);
    }

    @Test
    @Order(2)
    public void testFindByNo()  {
        Book book = sqlSession.selectOne("mybatis.mapper.ex06.Book.findByNo", 1L);
        assertEquals("마이바티스 연습", book.getName());
    }
}