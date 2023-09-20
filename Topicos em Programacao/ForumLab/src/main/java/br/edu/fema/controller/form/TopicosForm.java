package br.edu.fema.controller.form;

import java.util.List;

import br.edu.fema.model.Curso;
import br.edu.fema.model.Topico;
import br.edu.fema.repository.CursoRepository;

public class TopicosForm {

	private String titulo;
	private String mensagem;
	private String nomeCurso;

	public Topico converter(CursoRepository cursoRepository) {

		List<Curso> curso = cursoRepository.findByNome(this.nomeCurso);
		return new Topico(this.titulo, this.mensagem, curso.get(0));
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

}
