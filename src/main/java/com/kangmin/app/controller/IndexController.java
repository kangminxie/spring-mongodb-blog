package com.kangmin.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("")
    @ResponseBody
    public String index(final HttpServletRequest request) {
        LOGGER.info("index page visited from " + request.getRemoteUser());
        return "Will be the index page!";
    }
}
