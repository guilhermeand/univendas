package br.com.unialfa.univagas.endereco.rest;

import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> list(){
        Iterable<Endereco> pacientes = enderecoService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Endereco> endereco = enderecoService.findOneById(id);
        if(endereco != null) {
            return ResponseEntity.ok(endereco);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity<?> savePaciente(@RequestBody Endereco endereco){
        endereco = enderecoService.save(endereco);
        if(endereco != null) {
            return ResponseEntity.ok(endereco);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }

    }

    @PatchMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Endereco endereco, @PathVariable Long id){
        endereco = enderecoService.update(id,endereco);
        if(endereco != null) {
            return ResponseEntity.ok(endereco);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return enderecoService.delete(id);
    }


}
