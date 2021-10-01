package br.com.unialfa.univagas.candidato.services;

import br.com.unialfa.univagas.candidato.business.CurriculoService;
import br.com.unialfa.univagas.candidato.domain.Curriculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping(path = "curriculos")
public class CurriculoController {

    private final CurriculoService service;

    @Autowired
    public CurriculoController(CurriculoService curriculoService) {
        this.service = curriculoService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/search/listCurriculos")
    public @ResponseBody ResponseEntity<?> findAllCurriculo(){
        Iterable<Curriculo> curriculos = service.findAll();
        return ResponseEntity.ok(curriculos);
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<?> saveCurriculo(@RequestBody Curriculo curriculo){
        curriculo = service.save(curriculo);
        if(curriculo != null) {
            return ResponseEntity.ok(curriculo);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }

    }

    @PutMapping(path = "/edit")
    public @ResponseBody ResponseEntity<?> update(@RequestBody Curriculo curriculo){
        curriculo = service.save(curriculo);
        if(curriculo != null) {
            return ResponseEntity.ok(curriculo);
        }else{  
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return service.delete(id);
    }

}