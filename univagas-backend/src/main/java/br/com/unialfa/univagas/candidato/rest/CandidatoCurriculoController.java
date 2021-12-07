package br.com.unialfa.univagas.candidato.rest;

import br.com.unialfa.univagas.candidato.domain.CurriculoCandidato;
import br.com.unialfa.univagas.candidato.services.CandidatoCurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "curriculo")
public class CandidatoCurriculoController {

    private final CandidatoCurriculoService candidatoCurriculoService;

    @Autowired
    public CandidatoCurriculoController(CandidatoCurriculoService candidatoCurriculoService) {
        this.candidatoCurriculoService = candidatoCurriculoService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllCurriculo(){
        Iterable<CurriculoCandidato> curriculos = candidatoCurriculoService.findAll();
        return ResponseEntity.ok(curriculos);
    }
    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<CurriculoCandidato> candidato = candidatoCurriculoService.findOneById(id);
        if(candidato.isPresent()) {
            return ResponseEntity.ok(candidato.get());
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }


    @PostMapping(path = "/candidato/{candidato_id}/save")
    public @ResponseBody ResponseEntity<?> save(@PathVariable Long candidato_id, @RequestBody CurriculoCandidato curriculoCandidato){
        curriculoCandidato = candidatoCurriculoService.save(candidato_id, curriculoCandidato);
        if(curriculoCandidato != null) {
            return ResponseEntity.ok(curriculoCandidato);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }

    }

    @PatchMapping(path = "/{id}/candidato/{candidato_id}/update")
    public @ResponseBody ResponseEntity<?> update(@PathVariable Long id,@PathVariable Long candidato_id,@RequestBody CurriculoCandidato curriculoCandidato){
        curriculoCandidato = candidatoCurriculoService.update(id,candidato_id, curriculoCandidato);
        if(curriculoCandidato != null) {
            return ResponseEntity.ok(curriculoCandidato);
        }else{  
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return candidatoCurriculoService.delete(id);
    }

}
