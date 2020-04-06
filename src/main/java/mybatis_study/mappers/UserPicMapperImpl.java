package mybatis_study.mappers;

import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.UserPic;

public class UserPicMapperImpl implements UserPicMapper {
	private static final UserPicMapperImpl instance = new UserPicMapperImpl();
	
	private final String namespace = "mybatis_study.mappers.UserPicMapper";
	private SqlSession sqlSession;
	
	private UserPicMapperImpl() {}
	
	public static UserPicMapperImpl getInstance(){
		return instance;
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertUserPic(UserPic userPic) {
		return sqlSession.insert(namespace + ".insertUserPic", userPic);
	}

	@Override
	public UserPic getUserPic(int id) {
		return sqlSession.selectOne(namespace + ".getUserPic", id);
	}

}
