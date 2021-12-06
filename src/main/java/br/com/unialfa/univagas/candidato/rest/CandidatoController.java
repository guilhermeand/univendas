package br.com.unialfa.univagas.candidato.rest;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "candidato")
public class CandidatoController {

    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> list(){
        Iterable<Candidato> candidato = candidatoService.findAll();
        return ResponseEntity.ok(candidato);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Candidato> candidato = candidatoService.findOneById(id);
        if(candidato != null) {
            return ResponseEntity.ok(candidato);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<?> save(@RequestBody Candidato candidato){
        candidato = candidatoService.save(candidato);
        if(candidato != null) {
            return ResponseEntity.ok(candidato);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }

    }

    @PatchMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Candidato candidato, @PathVariable Long id){
        candidato = candidatoService.update(id,candidato);
        if(candidato != null) {
            return ResponseEntity.ok(candidato);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return candidatoService.delete(id);
    }


}
