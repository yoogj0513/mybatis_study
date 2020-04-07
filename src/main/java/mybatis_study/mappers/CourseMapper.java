package mybatis_study.mappers;

import java.util.List;
import java.util.Map;

import mybatis_study.dto.Course;
import mybatis_study.dto.CourseStat;

public interface CourseMapper {
	List<Course> selectCoursesByCondition(Map<String, Object> map);
	List<Course> selectCaseCourses(Map<String, Object> map);
	List<Course> selectWhereCourses(Map<String, Object> map);
	List<Course> selectTrimCourses(Map<String, Object> map);
	List<Course> selectCoursesForeachByTutors(Map<String, Object> map);
	
	int insertCourses(Map<String, Object> map);
	int deleteCourses(Map<String, Object> map);
	
	int insertCourse(Course course);
	int deleteCourse(int id);
	
	//procedure
	Map<String, Object> getCourseCountByTutor(Map<String, Object> param);
	Map<String, Object> getCourseCountByTutor2(Map<String, Object> param);
	CourseStat getCourseCountByTotur3(int param);
}
