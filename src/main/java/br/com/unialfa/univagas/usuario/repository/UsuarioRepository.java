package br.com.unialfa.univagas.usuario.repository;

import br.com.unialfa.univagas.usuario.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findById(String id);
}
