package _03;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.sql.DataSource;

public class Ex04 {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setup() throws Throwable {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("_03/config/ex04.xml"));

        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(Resources.getResourceAsReader("_03/sql/ddl.sql"));
    }

    @Test
    public void testInsertSQL()  {
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.insert("book.insert", "마이바티스 연습");

        assertEquals(1, count);
    }
}