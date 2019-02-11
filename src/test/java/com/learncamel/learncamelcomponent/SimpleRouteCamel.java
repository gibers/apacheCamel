package com.learncamel.learncamelcomponent;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;


@ActiveProfiles("dev")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class SimpleRouteCamel {

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    private Environment env;

    @BeforeClass
    public static void startCleanUp() throws IOException {
        FileUtils.cleanDirectory(new File("data/input"));
        FileUtils.deleteDirectory(new File("data/output"));
    }


    @Test
    public void testMoveFile() throws InterruptedException {

        String message = "type, sku#, itemdescription, price\n" +
                "ADD, 100, Samsung TV, 500\n" +
                "ADD, 101, LG TV, 600";

        String fileName = "filteTest.txt";

        producerTemplate.sendBodyAndHeader(env.getProperty("fromRoute"), message, Exchange.FILE_NAME, fileName);

        Thread.sleep(3000);

        File output = new File("data/output/" + fileName);

        assertThat(output.exists(), Matchers.is(true));


    }

}



