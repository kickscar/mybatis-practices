package _02;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Ex03 {
    @Test
    public void shoudConnectionNotNull() throws Throwable {
        InputStream inputStream = Resources.getResourceAsStream("_02/config/ex03.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        assertNotNull(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection());
    }
}