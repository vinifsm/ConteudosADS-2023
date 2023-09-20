package br.edu.fema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.model.StatusTopico;
import br.edu.fema.model.Topico;

public interface TopicosRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);

	List<Topico> findByStatus(StatusTopico status);

}
