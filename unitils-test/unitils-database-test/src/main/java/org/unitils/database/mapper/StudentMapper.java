package org.unitils.database.mapper;

import java.util.List;

import org.unitils.database.domain.Student;

public interface StudentMapper {

	List<Student> select(String name);

	void insert(Student student);

}
