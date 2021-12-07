package br.com.unialfa.univagas.empresa.rest;

import br.com.unialfa.univagas.empresa.domain.Estagio;
import br.com.unialfa.univagas.empresa.service.EstagioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "estagio")
public class EstagioController {
    private final EstagioService estagioService;

    @Autowired
    public EstagioController(EstagioService estagioService) {
        this.estagioService = estagioService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody
    ResponseEntity<?> list(){
        Iterable<Estagio> estagio = estagioService.findAll();
        return ResponseEntity.ok(estagio);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Estagio> estagio = estagioService.findOneById(id);
        if(estagio != null) {
            return ResponseEntity.ok(estagio);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }

    @PostMapping(path = "/empresa/{estagio_id}/save")
    public @ResponseBody
    ResponseEntity<?> save(@RequestBody Estagio estagio,@PathVariable Long estagio_id){

        estagio = estagioService.save(estagio_id,estagio);
        if(estagio != null) {
            return ResponseEntity.ok(estagio);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }

    }

    @PatchMapping(path = "/{id}/update")
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Estagio estagio, @PathVariable Long id){
        estagio = estagioService.update(id,estagio);
        if(estagio != null) {
            return ResponseEntity.ok(estagio);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return estagioService.delete(id);
    }

}
