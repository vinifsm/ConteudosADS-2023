package br.edu.fema.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.fema.controller.Dto.CursoDto;
import br.edu.fema.model.Curso;
import br.edu.fema.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<CursoDto> lista() {

		List<Curso> cursos = cursoRepository.findAll();
		return CursoDto.converter(cursos);

	}
	
	@GetMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<CursoDto> listaPorId(@PathVariable @NonNull Long id) {

		Optional<Curso> cursos = cursoRepository.findById(id);
		return CursoDto.converter(cursos.stream().toList());

	}

	@PostMapping
	@Transactional
	public ResponseEntity<CursoDto> cadastrar(@RequestBody Curso curso, UriComponentsBuilder uriBuilder) {
		cursoRepository.save(curso);

		URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).body(new CursoDto(curso));
	}

}
