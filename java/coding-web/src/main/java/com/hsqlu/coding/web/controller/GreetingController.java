package com.hsqlu.coding.web.controller;

import com.hsqlu.coding.web.domain.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
@RestController
@RequestMapping("/user")
public class GreetingController {
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Greeting view(@PathVariable("name") String name) {
        Greeting user = new Greeting();
        user.setName("zhang");
        return user;
    }
}
