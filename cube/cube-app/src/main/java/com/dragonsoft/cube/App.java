package com.dragonsoft.cube;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@ComponentScan
@RestController
public class App {
    @RequestMapping("/")
    public String hello() {
        return "Hello world!";
    }

    public static void main( String[] args ) {
//        SpringApplication.run(CubeAppInitialize.class, args);

    }
}
