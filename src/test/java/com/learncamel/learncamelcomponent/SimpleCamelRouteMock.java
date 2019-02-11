package com.learncamel.learncamelcomponent;

import org.apache.camel.CamelContext;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("mock")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class SimpleCamelRouteMock {

//    @Autowired
//    CamelContext con

    @Test
    public void testMoveFileMock() {

    }

}
