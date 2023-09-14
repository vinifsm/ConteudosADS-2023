package br.edu.fema.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.fema.controller.Dto.TopicoDto;
import br.edu.fema.model.Curso;
import br.edu.fema.model.Topico;

@Controller
public class TopicosController {

	@RequestMapping("/topicos")
	@ResponseBody
	public List<TopicoDto> lista() {

		Topico topico = new Topico("Duvidas sobre Spring Boot", "O que é uma API REST", new Curso());

		return TopicoDto.converter(Arrays.asList(topico, topico, topico, topico));

	}

}
