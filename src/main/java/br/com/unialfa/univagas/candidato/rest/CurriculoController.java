package br.com.unialfa.univagas.candidato.rest;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.domain.Curriculo;
import br.com.unialfa.univagas.candidato.services.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "curriculo")
public class CurriculoController {

    private final CurriculoService curriculoService;

    @Autowired
    public CurriculoController(CurriculoService curriculoService) {
        this.curriculoService = curriculoService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllCurriculo(){
        Iterable<Curriculo> curriculos = curriculoService.findAll();
        return ResponseEntity.ok(curriculos);
    }
    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Curriculo> candidato = curriculoService.findOneById(id);
        if(candidato.isPresent()) {
            return ResponseEntity.ok(candidato.get());
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }


    @PostMapping(path = "/candidato/{candidato_id}/save")
    public @ResponseBody ResponseEntity<?> save(@PathVariable Long candidato_id, @RequestBody Curriculo curriculo){
        curriculo = curriculoService.save(candidato_id,curriculo);
        if(curriculo != null) {
            return ResponseEntity.ok(curriculo);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }

    }

    @PatchMapping(path = "/{id}/candidato/{candidato_id}/update")
    public @ResponseBody ResponseEntity<?> update(@PathVariable Long id,@PathVariable Long candidato_id,@RequestBody Curriculo curriculo){
        curriculo = curriculoService.update(id,candidato_id,curriculo);
        if(curriculo != null) {
            return ResponseEntity.ok(curriculo);
        }else{  
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return curriculoService.delete(id);
    }

}