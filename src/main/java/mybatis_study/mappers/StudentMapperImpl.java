package mybatis_study.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Student;

public class StudentMapperImpl implements StudentMapper {
	private static final StudentMapperImpl instance = new StudentMapperImpl();
	
	private final String namespace = "mybatis_study.mappers.StudentMapper";
	private SqlSession sqlSession;
	
	private StudentMapperImpl() {}
	
	public static StudentMapperImpl getInstance() {
		return instance;
	}
	
	// sqlSession은 꼭 close를 해줘야하는데 test에서 open하고 close함
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Student selectStudentByNO(Student student) {
		return sqlSession.selectOne(namespace + ".selectStudentByNO", student);
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
		int res = sqlSession.insert(namespace + ".insertStudent", student); 
		return res;
	}

	@Override
	public int deleteStudent(int id) {
		int res = sqlSession.delete(namespace + ".deleteStudent", id); 
		return res;
	}

	@Override
	public int updateStudent(Student student) {
		int res = sqlSession.update(namespace + ".updateStudent", student); 
		return res;
	}

	@Override
	public List<Student> selectStudentByAllForResultMap() {
		return sqlSession.selectList(namespace + ".selectStudentByAllForResultMap");
	}

	@Override
	public List<Map<String, Object>> selectStudentByAllForHashMap() {
		return sqlSession.selectList(namespace + ".selectStudentByAllForHashMap");
	}

	@Override
	public Student selectStudentByNoAssociation(Student student) {
		return sqlSession.selectOne(namespace + ".selectStudentByNoAssociation", student);
	}

	@Override
	public int insertEnumStudent(Student student) {
		return sqlSession.insert(namespace + ".insertEnumStudent", student);
	}

	@Override
	public Student selectAllStudentByMap(Map<String, String> map) {
		return sqlSession.selectOne(namespace+".selectAllStudentByMap", map);
	}

	@Override
	public Map<Integer, Student> selectStudentForMap(int studId) {
		Map<Integer, Student> map = new HashMap<>();
		ResultHandler<Student> resultHandler = new ResultHandler<Student>() {
			@Override
			public void handleResult(ResultContext<? extends Student> resultContext) {
				Student student = resultContext.getResultObject();
				map.put(student.getStudId(), student);
			}
		};
		sqlSession.select(namespace + ".selectStudentForMap", studId, resultHandler);
		return map;
	}

	@Override
	public int updateSetStudent(Student student) {
		return sqlSession.update(namespace + ".updateSetStudent", student);
	}

	

}
