package com.dragonsoft.cube.web.controller;

import com.dragonsoft.cube.module.admin.serivce.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created: 2016/8/30.
 * Author: Qiannan Lu
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpSession session) {
		loginService.login(session, request.getRemoteAddr());
		return "login";
	}
}
