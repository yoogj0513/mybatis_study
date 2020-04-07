package mybatis_study.mappers;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study.AbstractTest;
import mybatis_study.dto.UserPic;
import mybatis_study.jdbc.MyBatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserPicMapperTest extends AbstractTest{
	private static UserPicMapperImpl dao;
	private static SqlSession sqlSession;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = UserPicMapperImpl.getInstance();
		
		sqlSession = MyBatisSqlSessionFactory.openSession(true);
		dao.setSqlSession(sqlSession);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
		sqlSession.close();
	}

	@Test(expected = RuntimeException.class)
	public void test01InsertUserPic() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		UserPic userPic = new UserPic();
		userPic.setId(1);
		userPic.setName("LeeYouYong");
		userPic.setBio("put some lengthy bio here");
		userPic.setPic(getPicFile());
		int result = dao.insertUserPic(userPic);
		Assert.assertSame(1, result);
	}

	private byte[] getPicFile() {
		byte[] pic = null;
		File file = new File(System.getProperty("user.dir")+"\\images\\lyy.jpg");
		try(InputStream is = new FileInputStream(file)){
			pic = new byte[is.available()];
			is.read(pic);
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return pic;
	}

	@Test
	public void test02GetUserPic() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		UserPic userPic = dao.getUserPic(1);
		if(userPic.getPic() != null) {
			File file = getPicFile(userPic);
			System.out.println("file path " + file.getAbsolutePath());
		}
		Assert.assertNotNull(userPic);
	}
	
	private File getPicFile(UserPic userPic) {
		File pics = new File(System.getProperty("user.dir") + "\\pics\\");
		if(!pics.exists()) {
			pics.mkdir();
		}
		File file = new File(pics, userPic.getName() + ".jpg");
		try (FileOutputStream output = new FileOutputStream(file)){
			output.write(userPic.getPic());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}

}
