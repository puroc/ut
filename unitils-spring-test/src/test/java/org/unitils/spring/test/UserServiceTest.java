package org.unitils.spring.test;

import org.springframework.context.ApplicationContext;
import org.unitils.spring.annotation.SpringApplicationContext;

public class UserServiceTest extends BaseServiceTest {

	/*
	 * this will create a new ApplicationContext, first loading the
	 * spring-beans.xml followed by the extra-spring-beans.xml configuration
	 * files
	 */
    @SpringApplicationContext("extra-spring-beans.xml")
    private ApplicationContext applicationContext;
}
