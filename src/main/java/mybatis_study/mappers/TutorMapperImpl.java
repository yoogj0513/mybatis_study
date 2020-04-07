package mybatis_study.mappers;

import org.apache.ibatis.session.SqlSession;

import mybatis_study.dto.Tutor;

public class TutorMapperImpl implements TutorMapper {
	private static final TutorMapperImpl instance = new TutorMapperImpl();
	
	private final String namespace = "mybatis_study.mappers.TutorMapper";
	private SqlSession sqlSession;
	
	private TutorMapperImpl() {}
	
	public static TutorMapperImpl getInstance() {
		return instance;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Tutor selectTutorByTutorId(Tutor tutor) {
		return sqlSession.selectOne(namespace + ".selectTutorByTutorId", tutor);
	}

	@Override
	public int insertTutor(Tutor tutor) {
		int res = sqlSession.insert(namespace + ".insertTutor", tutor);
		return res;
	}

	@Override
	public int deleteTutor(int id) {
		int res = sqlSession.delete(namespace + ".deleteTutor", id); 
		return res;
	}
	
	
}
