/**
 * 
 */
package org.unitils.database.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.domain.Student;
import org.unitils.database.exception.DbException;
import org.unitils.database.service.StudentService;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

/**
 * @author pud
 * 
 */
@SpringApplicationContext("spring-beans.xml")
@DataSet
public class StudentServiceTest extends UnitilsJUnit4 {

	@SpringBeanByName
	private StudentService studentService;

	/*
	 * 方法上的@DataSet会覆盖类上@DataSet，所以只会向数据库中插入StudentServiceTest.testSelect.xml的数据
	 * */
	@Test
	@DataSet(value = "StudentServiceTest.testSelect.xml")
	public void testSelect() {
		Assert.assertTrue(!studentService.select("lucy").isEmpty());
	}

	/*
	 * 由于unitils.properties配置了DatabaseModule.Transactional.value.default=commit,
	 * 所以测试方法执行完成之后才会提交事务
	 * 。所以测试方法中执行的insert操作并没有插入到数据库中，此时进行expectedData的比较，数据库中必然没有期待的数据
	 * 。所以要添加@Transactional(TransactionMode.DISABLED)，代表这个测试方法不采用事务的方式执行，即自动提交
	 */
	@Test
	@ExpectedDataSet(value = "StudentServiceTest.testInsert-result.xml")
	@Transactional(TransactionMode.DISABLED)
	public void testInsert() {
		try {
			studentService.insert("jack", 20);
		} catch (DbException e) {
			Assert.fail(e.getMessage());
		}
	}

}
