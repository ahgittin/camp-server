package io.brooklyn.camp.test.mock.web;

import io.brooklyn.camp.impl.ApplicationComponentTemplate;


public class MockWarComponent {

    public static final ApplicationComponentTemplate TEMPLATE = 
        ApplicationComponentTemplate.builder()
            .name(MockWarComponent.class.getCanonicalName())
            .description("Mock WAR")
            .build();

}