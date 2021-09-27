package br.com.unialfa.univagas.curriculo.repository;

import br.com.unialfa.univagas.curriculo.domain.Curriculo;
import org.springframework.data.repository.CrudRepository;

public interface CurriculoRepository extends CrudRepository<Long, Curriculo> {
}
