package mybatis_study.mappers;

import static org.junit.Assert.fail;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.AbstractTest;
import mybatis_study.dto.Course;
import mybatis_study.dto.Tutor;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TutorMapperTest extends AbstractTest{
	private static TutorMapperImpl dao;
	private static SqlSession sqlSession;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = TutorMapperImpl.getInstance();
		sqlSession = MyBatisSqlSessionFactory.openSession();
		dao.setSqlSession(sqlSession);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
		sqlSession.close();
	}

	@Test
	public void test01SelectTutorByTutorId() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Tutor findTutor = new Tutor();
		findTutor.setTutorId(1);
		Tutor tutor = dao.selectTutorByTutorId(findTutor);
		Assert.assertEquals(tutor.getTutorId(), findTutor.getTutorId());
		
		log.trace(tutor.getTutorId() + " : " + tutor.getName());
		List<Course> courses = tutor.getCourses();
		for(Course c : courses) {
			log.trace(c.toString());
		}
	}

}
