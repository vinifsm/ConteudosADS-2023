package br.edu.fema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "<h1>Alô mundo - FEMA</h1>";
	}

}
