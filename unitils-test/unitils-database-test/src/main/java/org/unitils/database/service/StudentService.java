package org.unitils.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unitils.database.domain.Student;
import org.unitils.database.exception.DbException;
import org.unitils.database.mapper.StudentMapper;

@Service
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;

//	@Transactional(rollbackFor = DbException.class)
	public void insert(String name, int age) throws DbException {
		Student student = new Student();
		student.setAge(age);
		student.setName(name);
		studentMapper.insert(student);
		// throw new DbException("insert failed.");
	}

	@Transactional(rollbackFor = DbException.class)
	public List<Student> select(String name) {
		return studentMapper.select(name);
	}

}
