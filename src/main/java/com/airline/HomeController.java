package com.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yash on 5/7/17.
 */

@Controller
@EnableAutoConfiguration
public class HomeController {

    @RequestMapping("/")
    String home() {
        return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HomeController.class, args);
    }
}