package br.com.unialfa.univagas.usuario.repository;

import br.com.unialfa.univagas.usuario.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Long, Usuario> {

}
