package br.com.unialfa.univagas.usuario.service;

import br.com.unialfa.univagas.usuario.domain.Usuario;
import br.com.unialfa.univagas.usuario.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Optional<Usuario>> finOneById(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return Optional.of(usuario);
    }

    public Iterable<Usuario> findAll(){
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id,Usuario usuario){
        Usuario usuarioUpdate = usuarioRepository.findById(id).get();
        if(usuarioUpdate == null) return null;
        if(usuario.getEmail() != null) usuarioUpdate.setEmail(usuario.getEmail());
        if(usuario.getSenha() != null) usuarioUpdate.setSenha(usuario.getSenha());
        return usuarioRepository.save(usuarioUpdate);
    }

    public ResponseEntity<?> delete(Long id){
        try {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }
}
