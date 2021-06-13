package _02;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Ex01 {
   @Test
    public void shoudThrowIOException() {
        assertThrows(IOException.class, () -> {
            InputStream inputStream = Resources.getResourceAsStream("_02/config/none.xml");
        });
    }

    @Test
    public void shoudSqlSessionFactoryNotNull() throws Throwable {
        InputStream inputStream = Resources.getResourceAsStream("_02/config/ex01.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        assertNotNull(sqlSessionFactory);
    }
}