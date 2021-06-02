package _03;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Ex05 {
    @Test
    public void shoudConnectionNotNull() throws Throwable {
        InputStream inputStream = Resources.getResourceAsStream("_03/config/ex05.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        assertNotNull(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection());
    }
}