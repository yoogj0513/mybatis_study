package mybatis_study.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Course;
import mybatis_study.dto.CourseStat;

public class CourseMapperImpl implements CourseMapper {
	private static final CourseMapperImpl instance = new CourseMapperImpl();
	
	private final String namespace = "mybatis_study.mappers.CourseMapper";
	private SqlSession sqlsession;
	
	private CourseMapperImpl() {}

	public static CourseMapperImpl getInstance() {
		return instance;
	}
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	@Override
	public List<Course> selectCoursesByCondition(Map<String, Object> map) {
		return sqlsession.selectList(namespace + ".selectCoursesByCondition", map);
	}

	@Override
	public List<Course> selectCaseCourses(Map<String, Object> map) {
		return sqlsession.selectList(namespace + ".selectCaseCourses", map);
	}

	@Override
	public List<Course> selectWhereCourses(Map<String, Object> map) {
		return sqlsession.selectList(namespace + ".selectWhereCourses", map);
	}

	@Override
	public List<Course> selectTrimCourses(Map<String, Object> map) {
		return sqlsession.selectList(namespace + ".selectTrimCourses", map);
	}

	@Override
	public List<Course> selectCoursesForeachByTutors(Map<String, Object> map) {
		return sqlsession.selectList(namespace + ".selectCoursesForeachByTutors", map);
	}

	@Override
	public int insertCourses(Map<String, Object> map) {
		return sqlsession.insert(namespace + ".insertCourses", map);
	}

	@Override
	public int deleteCourses(Map<String, Object> map) {
		return sqlsession.delete(namespace + ".deleteCourses", map);
	}

	@Override
	public int insertCourse(Course course) {
		int res = sqlsession.insert(namespace + ".insertCourses", course);
		return res;
	}

	@Override
	public int deleteCourse(int id) {
		int res = sqlsession.delete(namespace + ".deleteCourses", id);
		return res;
	}

	@Override
	public Map<String, Object> getCourseCountByTutor(Map<String, Object> param) {
		Map<String, Object> map = new HashMap<String, Object>();
		ResultHandler<CourseStat> resultHandler = new ResultHandler<CourseStat>() {

			@Override
			public void handleResult(ResultContext<? extends CourseStat> resultContext) {
				CourseStat state = resultContext.getResultObject();
				map.put(state.getTutor(), state.getTotal());
			}
		};
		sqlsession.select(namespace + ".getCourseCountByTutor", param, resultHandler);
		return map;
	}

	@Override
	public Map<String, Object> getCourseCountByTutor2(Map<String, Object> param) {
		Map<String, Object> map = new HashMap<String, Object>();
		ResultHandler<CourseStat> resultHandler = new ResultHandler<CourseStat>() {
			
			@Override
			public void handleResult(ResultContext<? extends CourseStat> resultContext) {
				CourseStat state = resultContext.getResultObject();
				map.put(state.getTutor(), state.getTotal());
			}
		};
		sqlsession.select(namespace + ".getCourseCountByTutor2", param, resultHandler);
		return map;
	}

	@Override
	public CourseStat getCourseCountByTotur3(int param) {
		return sqlsession.selectOne(namespace + ".getCourseCountByTotur3", param);
	}

}
