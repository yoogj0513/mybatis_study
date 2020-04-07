package mybatis_study.service;

import static org.junit.Assert.fail;

import java.util.Date;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.dto.Course;
import mybatis_study.dto.Tutor;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseUiServiceTest {
	private static CourseUiService service;
	protected static final Log log = LogFactory.getLog(CourseUiServiceTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new CourseUiService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test(expected = RuntimeException.class)
	public void test1JoinNewTutorAndCourseFailTutor() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Tutor tutor = new Tutor();
		tutor.setTutorId(4);
		tutor.setName("kim");
		tutor.setEmail("test@test.co.kr");
		
		Course course = new Course(8, "Python", "Programming", new Date(), new Date(), 4);
		service.joinNewTutorAndCourse(tutor, course);
	}
	
	@Test(expected = RuntimeException.class)
	public void test2JoinNewTutorAndCourseFailCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Tutor tutor = new Tutor();
		tutor.setTutorId(6);
		tutor.setName("kim");
		tutor.setEmail("test@test.co.kr");
		
		Course course = new Course(2, "Python", "Programming", new Date(), new Date(), 4);
		service.joinNewTutorAndCourse(tutor, course);
	}
	
	@Test
	public void test3JoinNewTutorAndCourseSuccess() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Tutor tutor = new Tutor();
		tutor.setTutorId(5);
		tutor.setName("kim");
		tutor.setEmail("test@test.co.kr");
		
		Course course = new Course(4, "Python", "Programming", new Date(), new Date(), 5);
		service.joinNewTutorAndCourse(tutor, course);
	}

	@Test(expected = RuntimeException.class)
	public void test4RemoveTutorAndCourseFailTutor() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		service.removeTutorAndCourse(10, 4);
	}
	
	@Test(expected = RuntimeException.class)
	public void test5RemoveTutorAndCourseFailCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		service.removeTutorAndCourse(5, 8);
	}
	
	@Test
	public void test6RemoveTutorAndCourseSuccess() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		service.removeTutorAndCourse(5, 4);
	}

}
