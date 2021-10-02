package br.com.unialfa.univagas.candidatura.repository;

import br.com.unialfa.univagas.candidatura.domain.Candidatura;
import br.com.unialfa.univagas.empresa.domain.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface CandidaturaRepository extends CrudRepository<Candidatura, Long> {
}
