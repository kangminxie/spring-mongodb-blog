package com.kangmin.app.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HealthRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthRestController.class);

    @GetMapping("/api/health")
    public String healthOK(final HttpServletRequest request) {
        LOGGER.info("/api/health visited from " + request.getRemoteUser());
        return "Ok";
    }
}
