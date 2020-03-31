package mybatis_study.mappers;

import java.util.List;

import mybatis_study.dto.Student;

public interface StudentMapper {
	// typeHandler 사용
	Student selectStudentByNO(Student student);
	// typeHandler 사용하지 않음
	Student selectStduentByNoWithResultMap(Student student);
	
	//list
	List<Student> selectStudentByAll();
}
