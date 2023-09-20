package br.edu.fema.controller.Dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.fema.model.Curso;

public class CursoDto {

	private Long id;
	private String nome;
	private String categoria;

	public CursoDto(Curso curso) {
		super();
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.categoria = curso.getCategoria();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public static List<CursoDto> converter(List<Curso> cursos) {

		return cursos.stream().map(CursoDto::new).collect(Collectors.toList());
	}

}
