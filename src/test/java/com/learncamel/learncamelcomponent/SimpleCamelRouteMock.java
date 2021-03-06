package com.learncamel.learncamelcomponent;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


@ActiveProfiles("mock")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@Slf4j
public class SimpleCamelRouteMock extends CamelTestSupport {

    @Autowired
    CamelContext context;

    @Autowired
    protected CamelContext createCamelContext() {
        return context;
    }

    @Autowired
    Environment env;

    @Autowired
    ProducerTemplate producerTemplate;

    @Value("${spring.profiles}")
    String profiles;

    @Test
    public void testMF() {
        log.debug("profile => " + profiles);
//        System.out.println("profile => " + profiles);
    }

    @Test
    public void testMoveFileMock() throws InterruptedException {
        String message = "type, sku#, itemdescription, price\n" +
                "ADD, 100, Samsung TV, 500\n" +
                "ADD, 101, LG TV, 600";

        MockEndpoint mockEndpoint = getMockEndpoint(env.getProperty("toRoute1"));
        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived(message);

        producerTemplate.sendBodyAndHeader(env.getProperty("startRoute"), message,
                "env", env.getProperty("spring.profiles") );

        assertMockEndpointsSatisfied();

    }


}
