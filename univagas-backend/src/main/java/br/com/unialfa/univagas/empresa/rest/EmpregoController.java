package br.com.unialfa.univagas.empresa.rest;


import br.com.unialfa.univagas.empresa.domain.Emprego;
import br.com.unialfa.univagas.empresa.service.EmpregoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "emprego")
public class EmpregoController {
    private final EmpregoService empregoService;

    @Autowired
    public EmpregoController(EmpregoService empregoService) {
        this.empregoService = empregoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody
    ResponseEntity<?> list(){
        Iterable<Emprego> emprego = empregoService.findAll();
        return ResponseEntity.ok(emprego);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Emprego> emprego = empregoService.findOneById(id);
        if(emprego != null) {
            return ResponseEntity.ok(emprego);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }

    @PostMapping(path = "/empresa/{empresa_id}/save")
    public @ResponseBody
    ResponseEntity<?> save(@RequestBody Emprego emprego,@PathVariable Long empresa_id){

        emprego = empregoService.save(empresa_id,emprego);
        if(emprego != null) {
            return ResponseEntity.ok(emprego);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }

    }

    @PatchMapping(path = "/{id}/update")
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Emprego emprego, @PathVariable Long id){
        emprego = empregoService.update(id,emprego);
        if(emprego != null) {
            return ResponseEntity.ok(emprego);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return empregoService.delete(id);
    }

}
