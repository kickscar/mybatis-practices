package mybatis.mapper.ex02;

import mybatis.mapper.ex01.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.BookRepository;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBookRepository {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setup() throws Throwable {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config/ex02.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("sql/ddl.sql"));
    }

    @Test
    public void test02nsertSQL()  {
        SqlSession session = sqlSessionFactory.openSession();
        BookRepository book = session.getMapper(BookRepository.class);

        int count = book.insert("마이바티스 연습");

        assertEquals(1, count);
    }

}