package mybatis_study.mappers;

import java.util.List;
import java.util.Map;

import mybatis_study.dto.Student;

public interface StudentMapper {
	// typeHandler 사용
	Student selectStudentByNO(Student student);
	// typeHandler 사용하지 않음
	Student selectStduentByNoWithResultMap(Student student);
	
	//list
	List<Student> selectStudentByAll();
	
	int insertStudent(Student student);
	int deleteStudent(int id);
	int updateStudent(Student student);
	
	//ResultMap
	List<Student> selectStudentByAllForResultMap();
	
	//hasMap
	List<Map<String, Object>> selectStudentByAllForHashMap();
	
	//내포된 결과매핑(ResultMap)을 사용한 일대일 매핑
	Student selectStudentByNoAssociation(Student student);
	
	//enum 타입 다루기
	int insertEnumStudent(Student student);
	
	//여러 개의 입력 파라미터 전달
	Student selectAllStudentByMap(Map<String, String> map);
	
	// ResultSet 처리방식의 재정의
	Map<Integer, Student> selectStudentForMap(int studId);
	
	//set 조건
	int updateSetStudent(Student student);
}
