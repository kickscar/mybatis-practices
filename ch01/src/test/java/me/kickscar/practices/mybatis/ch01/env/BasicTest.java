package me.kickscar.practices.mybatis.ch01.env;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasicTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setup() throws Throwable {
        Properties props = new Properties();
        props.load(Resources.getResourceAsStream("config/jdbc.properties"));

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(props.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(props.getProperty("jdbc.url"));
        dataSource.setUsername(props.getProperty("jdbc.username"));

        new ScriptRunner(dataSource.getConnection()).runScript(Resources.getResourceAsReader("sql/ddl.sql"));
    }

    @Test
    @Order(1)
    public void testConfiguration() throws Throwable {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("config/configuration.xml"));
        assertNotNull(sqlSessionFactory);
    }

    // @Disabled
    @Test
    @Order(2)
    public void testQuery() throws Throwable {
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.insert("book.insert", "과학 혁명의 구조");

        assertEquals(1, count);
    }

}
