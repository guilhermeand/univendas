package br.com.unialfa.univagas.usuario.rest;

import br.com.unialfa.univagas.usuario.domain.Usuario;
import br.com.unialfa.univagas.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RepositoryRestController
@RequestMapping(path = "usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllPaciente(){
        Iterable<Usuario> pacientes = usuarioService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Optional<Usuario>> usuario = usuarioService.finOneById(id);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity<?> savePaciente(@RequestBody Usuario usuario){
        usuario = usuarioService.save(usuario);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }

    }

    @PatchMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> update(@RequestBody Usuario usuario,@PathVariable Long id){
        usuario = usuarioService.update(id,usuario);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return usuarioService.delete(id);
    }

}
