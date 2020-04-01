package mybatis_study.mappers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Student;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;

public class StudentMapperImpl implements StudentMapper {
	private static final StudentMapperImpl instance = new StudentMapperImpl();
	
	private final String namespace = "mybatis_study.mappers.StudentMapper";
	private SqlSession sqlSession;
	
	private StudentMapperImpl() {
		this.sqlSession = MyBatisSqlSessionFactory.openSession();
	}
	
	public static StudentMapperImpl getInstance() {
		return instance;
	}
	
	@Override
	public Student selectStudentByNO(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){ // session close			
			return sqlSession.selectOne(namespace + ".selectStudentByNO", student);
		}
	}

	@Override
	public Student selectStduentByNoWithResultMap(Student student) {
		return sqlSession.selectOne(namespace + ".selectStduentByNoWithResultMap", student);
		// 스프링에서는 자동으로 close가 호출됨
	}

	@Override
	public List<Student> selectStudentByAll() {
		return sqlSession.selectList(namespace + ".selectStudentByAll");
	}

	@Override
	public int insertStudent(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){			
			int res = sqlSession.insert(namespace + ".insertStudent", student); 
			return res;
		}
	}

	@Override
	public int deleteStudent(int id) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){			
			int res = sqlSession.insert(namespace + ".deleteStudent", id); 
			return res;
		}
	}

	@Override
	public int updateStudent(Student student) {
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()){			
			int res = sqlSession.insert(namespace + ".updateStudent", student); 
			return res;
		}
	}

}
