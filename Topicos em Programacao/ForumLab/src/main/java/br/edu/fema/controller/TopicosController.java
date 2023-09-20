package br.edu.fema.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.fema.controller.Dto.TopicoDto;
import br.edu.fema.controller.form.TopicosForm;
import br.edu.fema.model.StatusTopico;
import br.edu.fema.model.Topico;
import br.edu.fema.repository.CursoRepository;
import br.edu.fema.repository.TopicosRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicosRepository topicosRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDto> lista(String nomeCurso, StatusTopico status) {

		if (status != null) {
			List<Topico> topicos = topicosRepository.findByStatus(status);
			return TopicoDto.converter(topicos);
		}

		if (nomeCurso == null) {
			List<Topico> topicos = topicosRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicosRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicosForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicosRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

}
