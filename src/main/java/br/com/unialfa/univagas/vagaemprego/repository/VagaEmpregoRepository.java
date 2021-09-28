package br.com.unialfa.univagas.vagaemprego.repository;

import br.com.unialfa.univagas.vagaemprego.domain.VagaEmprego;
import org.springframework.data.repository.CrudRepository;

public interface VagaEmpregoRepository extends CrudRepository<VagaEmprego, Long> {
}
