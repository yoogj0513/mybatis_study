package mybatis_study.mappers;

import mybatis_study.dto.UserPic;

public interface UserPicMapper {
	int insertUserPic(UserPic userPic);
	UserPic getUserPic(int id);
}
