package br.com.unialfa.univagas.empresa.repository;

import br.com.unialfa.univagas.empresa.domain.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository extends CrudRepository<Long, Empresa> {
}
