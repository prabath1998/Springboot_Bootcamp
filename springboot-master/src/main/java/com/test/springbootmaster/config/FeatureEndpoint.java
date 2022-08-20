package com.test.springbootmaster.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
}
