package com.learncamel.learncamelcomponent.route;

import com.learncamel.learncamelcomponent.domain.Item;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SimpleCamelRoute extends RouteBuilder {


    @Autowired
    private Environment env;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure() throws Exception {

        DataFormat bindy = new BindyCsvDataFormat(Item.class);

        from("{{startRoute}}")
                .log("Timer Invoked and the body " + env.getProperty("message") )
                .choice()
                    .when((header("env").isNotEqualTo("mock")))
                        .pollEnrich("{{fromRoute}}")
                    .otherwise()
                        .log("mock nv flow and the body is ${body}")
                .end()
                .to("{{toRoute}}")
//                .unmarshal(bindy)
//                .log("the unmarshal object is ${body}")
//                .split(body())
//                    .log("Record is ${body}")
//                .end();

        ;
        log.info("ending the Camel Route");

    }

}

