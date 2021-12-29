package com.kangmin.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/oauth2/callback/**")
    public String oauth2Redirect() {
        LOGGER.info("ForwardingController `/oauth2/redirect` path visited and got redirected to /");
        return "forward:/";
    }

    @RequestMapping(value = "/**/{path:[^.]*}")
    public String errorRedirect(@PathVariable final String path) {
        LOGGER.error("ForwardingController *** /error path visited with path=" + path);
        return "forward:/";
    }
}
