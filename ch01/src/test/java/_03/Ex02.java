package _03;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class Ex02 {
    @Test
    public void shoudEnvironmentIdDevel() throws Throwable {
        InputStream inputStream = Resources.getResourceAsStream("_03/config/ex02.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "devel");

        assertEquals("devel", sqlSessionFactory.getConfiguration().getEnvironment().getId());
    }

    @Test
    public void shoudDefaultEnvironmentIdDevel() throws Throwable {
        InputStream inputStream = Resources.getResourceAsStream("_03/config/ex02.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        assertEquals("devel", sqlSessionFactory.getConfiguration().getEnvironment().getId());
    }
}