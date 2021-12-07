package br.com.unialfa.univagas.empresa.rest;

import br.com.unialfa.univagas.empresa.domain.Empresa;
import br.com.unialfa.univagas.empresa.domain.Empresa;
import br.com.unialfa.univagas.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody
    ResponseEntity<?> list(){
        Iterable<Empresa> empresa = empresaService.findAll();
        return ResponseEntity.ok(empresa);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Empresa> empresa = empresaService.findOneById(id);
        if(empresa != null) {
            return ResponseEntity.ok(empresa);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<?> save(@RequestBody Empresa empresa){

        empresa = empresaService.save(empresa);
        if(empresa != null) {
            return ResponseEntity.ok(empresa);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }

    }

    @PatchMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Empresa empresa, @PathVariable Long id){
        empresa = empresaService.update(id,empresa);
        if(empresa != null) {
            return ResponseEntity.ok(empresa);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(400);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return empresaService.delete(id);
    }


}
