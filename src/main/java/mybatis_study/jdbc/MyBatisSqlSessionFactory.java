package mybatis_study.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try(InputStream is = Resources.getResourceAsStream("mybatis-config.xml")){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
	}
	
	public static SqlSession openSession() {
		return sqlSessionFactory.openSession(true); //setAutoCommit(false)
	}
}
