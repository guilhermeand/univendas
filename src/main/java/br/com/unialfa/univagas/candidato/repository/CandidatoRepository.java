package br.com.unialfa.univagas.candidato.repository;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import org.springframework.data.repository.CrudRepository;

public interface CandidatoRepository extends CrudRepository<Candidato, Long> {
}
