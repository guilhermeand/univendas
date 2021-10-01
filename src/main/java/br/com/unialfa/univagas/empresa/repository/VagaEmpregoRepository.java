package br.com.unialfa.univagas.empresa.repository;

import br.com.unialfa.univagas.empresa.domain.Emprego;
import org.springframework.data.repository.CrudRepository;

public interface VagaEmpregoRepository extends CrudRepository<Emprego, Long> {
}
