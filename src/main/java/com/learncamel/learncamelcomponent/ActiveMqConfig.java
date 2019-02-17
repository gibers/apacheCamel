package com.learncamel.learncamelcomponent;


import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMqConfig {

    public ActiveMQComponent activeCompo (ConnectionFactory factory) {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(factory);
        return activeMQComponent;
    }

}
