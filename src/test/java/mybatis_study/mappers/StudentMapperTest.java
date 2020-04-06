package mybatis_study.mappers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.AbstractTest;
import mybatis_study.dto.Gender;
import mybatis_study.dto.PhoneNumber;
import mybatis_study.dto.Student;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest extends AbstractTest {
	private static StudentMapperImpl dao;
	private static SqlSession sqlSession;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = StudentMapperImpl.getInstance();
		
		//sqlSesstion 한번 호출
		sqlSession = MyBatisSqlSessionFactory.openSession();
		dao.setSqlSession(sqlSession);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
		sqlSession.close();
	}

	@Test
	public void test01SelectStudentByNO() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		Student selectStudent = dao.selectStudentByNO(student);
		log.debug(selectStudent.toString());
		Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
	}
	
	@Test
	public void test02SelectStduentByNoWithResultMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		Student selectStudent = dao.selectStduentByNoWithResultMap(student);
		log.debug(selectStudent.toString());
		Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
	}
	
	@Test
	public void test03SelectStudentByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Student> lists = dao.selectStudentByAll();
		Assert.assertNotNull(lists);
		for(Student std : lists) {
			log.debug(std.toString());
		}
	}
	
	@Test
	public void test04InsertStudent() {
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990, 2, 28);
		
		Student student = new Student();
		student.setStudId(3);
		student.setName("홍길동");
		student.setEmail("test@test.co.kr");
		student.setPhone(new PhoneNumber("010-1234-1234"));
		student.setDob(newDate.getTime());
		
		int res = dao.insertStudent(student);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test05DeleteStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int deleteStudent = dao.deleteStudent(3);
		Assert.assertSame(1, deleteStudent);
	}
	
	@Test
	public void test06UpdateStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		student.setName("Timothy");
		student.setEmail("test@test.com");
		student.setPhone(new PhoneNumber("010-0000-1234"));
		student.setDob(new Date());
		
		int res = dao.updateStudent(student);
		Assert.assertSame(1, res);
		
		student.setEmail("timothy@gmail.com");
		student.setPhone(new PhoneNumber("123-1234-1234"));
		student.setDob(new GregorianCalendar(1998, 04, 25).getTime());
		res = dao.updateStudent(student);
		Assert.assertSame(1, res);
	}
	
	@Test
	public void test07SelectStudentByAllForResultMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Student> lists = dao.selectStudentByAllForResultMap();
		Assert.assertNotNull(lists);
		for(Student std : lists) {
			log.debug(std.toString());
		}
	}
	
	@Test
	public void test08SelectStudentByAllForHashMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Map<String, Object>> lists = dao.selectStudentByAllForHashMap();
		Assert.assertNotNull(lists);
		
		for(Map<String, Object> map : lists) {
			for(Entry<String, Object> e : map.entrySet()) {
				log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
			}
		}
	}
	
	@Test
	public void test09SelectStudentByNoAssociation() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Student student = new Student();
		student.setStudId(1);
		Student selectStd = dao.selectStudentByNoAssociation(student);
		Assert.assertNotNull(selectStd);
		log.debug(selectStd.toString());
	}
	
	@Test
	public void test10InsertEnumStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990, 2, 28);
		Student student = new Student();
		student.setStudId(3);
		student.setName("test");
		student.setEmail("test@test.co.kr");
		student.setDob(newDate.getTime());
		student.setPhone(new PhoneNumber("010-1234-1234"));
		student.setGender(Gender.FEMALE);
		int res = dao.insertEnumStudent(student);
		Assert.assertEquals(1, res);
		
		student.setStudId(4);
		student.setName("test2");
		student.setEmail("test2@test2.co.kr");
		student.setDob(newDate.getTime());
		student.setPhone(new PhoneNumber("010-1111-1111"));
		student.setGender(Gender.MALE);
		int res1 = dao.insertEnumStudent(student);
		Assert.assertEquals(1, res1);
	}
	
	@Test
	public void test11SelectAllStudentByMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("name", "Timothy");
		maps.put("email", "timothy@gmail.com");
		Student student = dao.selectAllStudentByMap(maps);
		Assert.assertNotNull(student);
		log.debug(student.toString());
		
		maps.remove("email");
		student = dao.selectAllStudentByMap(maps);
		log.debug(student.toString());
		
		maps.clear();
		maps.put("email", "timothy@gmail.com");
		student = dao.selectAllStudentByMap(maps);
		log.debug(student.toString());
	}
	
	@Test
	public void test12SelectStudentForMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Map<Integer, Student> map = dao.selectStudentForMap(1);
		Assert.assertNotNull(map);
		
		for(Entry<Integer, Student> entry : map.entrySet()) {
			System.out.printf("key(%s) - values(%s)%n", entry.getKey(), entry.getValue());
		}
	}

}
