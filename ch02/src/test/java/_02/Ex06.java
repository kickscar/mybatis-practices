package _02;

import _02.domain.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex06 {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setup() throws Throwable {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("_02/config/ex06.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("_02/sql/ddl.sql"));
    }

    @Test
    public void testInsertSQL()  {
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.insert("book.insert", "마이바티스 연습");

        assertEquals(1, count);
    }

    @Test
    public void testSelectSQL()  {
        SqlSession session = sqlSessionFactory.openSession();
        Book book = session.selectOne("book.findByNo", 1L);

        assertEquals("마이바티스 연습", book.getName());
    }

}
