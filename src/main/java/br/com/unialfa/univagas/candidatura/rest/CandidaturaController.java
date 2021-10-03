package br.com.unialfa.univagas.candidatura.rest;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.domain.Curriculo;
import br.com.unialfa.univagas.candidato.services.CandidatoService;
import br.com.unialfa.univagas.candidato.services.CurriculoService;
import br.com.unialfa.univagas.candidatura.domain.Candidatura;
import br.com.unialfa.univagas.candidatura.service.CandidaturaService;
import br.com.unialfa.univagas.empresa.domain.Emprego;
import br.com.unialfa.univagas.empresa.domain.Estagio;
import br.com.unialfa.univagas.empresa.service.EmpregoService;
import br.com.unialfa.univagas.empresa.service.EstagioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RepositoryRestController
@RequestMapping(path = "candidatura")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;
    private final CandidatoService candidatoService;
    private final CurriculoService curriculoService;
    private final EstagioService estagioService;
    private final EmpregoService empregoService;

    @Autowired
    public CandidaturaController(CandidaturaService candidaturaService, CandidatoService candidatoService, CurriculoService curriculoService, EstagioService estagioService, EmpregoService empregoService) {
        this.candidaturaService = candidaturaService;
        this.candidatoService = candidatoService;
        this.curriculoService = curriculoService;
        this.estagioService = estagioService;
        this.empregoService = empregoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> list(){
        Iterable<Candidatura> candidaturas = candidaturaService.findAll();
        return ResponseEntity.ok(candidaturas);
    }
    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findOne(@PathVariable(name = "id") Long id){
        Optional<Candidatura> candidato = candidaturaService.findOneById(id);
        if(candidato.isPresent()) {
            return ResponseEntity.ok(candidato.get());
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(404);
        }
    }


    @PostMapping(path = "/vaga/{vaga_id}/tipo/{vaga_tipo}/candidato/{candidato_id}/curriculo/{curriculo_id}/save")
    public @ResponseBody ResponseEntity<?> save(@PathVariable Long vaga_id,@PathVariable String vaga_tipo,@PathVariable Long candidato_id,@PathVariable Long curriculo_id){
        Optional<Candidato> candidato = candidatoService.findOneById(candidato_id);
        if (candidato.isEmpty()) return (ResponseEntity<?>) ResponseEntity.status(400).body("candidato nao encontrado");
        if (!candidatoService.validarMenorOuIgualTresCandidaturas(candidato.get())) return (ResponseEntity<?>) ResponseEntity.status(400).body("numero de vagas maximo atingido para o candidato");
        Optional<Curriculo> curriculo = curriculoService.findOneById(curriculo_id);
        if(curriculo.isEmpty()) return (ResponseEntity<?>) ResponseEntity.status(400).body("curriculo nao encontrado");
        Candidatura candidatura;
        switch (vaga_tipo){
            case "estagio":
                Optional<Estagio> estagio = estagioService.findOneById(vaga_id);
                if(estagio.isEmpty()) return null;
                candidatura = candidaturaService.save(estagio.get(),vaga_tipo.toLowerCase(),candidato.get(),curriculo.get());
                break;
            case "emprego":
                Optional<Emprego> emprego = empregoService.findOneById(vaga_id);
                if(emprego.isEmpty()) return null;
                candidatura = candidaturaService.save(emprego.get(),vaga_tipo.toLowerCase(),candidato.get(),curriculo.get());
                break;
            default:
                return (ResponseEntity<?>) ResponseEntity.status(404).body("tipo de vaga nao aceito");
        }
        if(candidatura != null) {
            return ResponseEntity.ok(candidatura);
        }else{
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }

    }
//    @PostMapping(path = "/emprego/{emprego_id}/curriculo/{curriculo_id}/save")
//    public @ResponseBody ResponseEntity<?> save(@PathVariable Long candidato_id, @RequestBody Candidatura curriculo){
//        Emprego emprego = candidaturaService.saveEmprego(candidato_id,curriculo);
//        if(curriculo != null) {
//            return ResponseEntity.ok(curriculo);
//        }else{
//            return (ResponseEntity<?>) ResponseEntity.status(500);
//        }
//
//    }

    @PatchMapping(path = "/{id}/curriculo/{curriculo_id}/update")
    public @ResponseBody ResponseEntity<?> update(@PathVariable Long id,@PathVariable Long curriculo_id,@RequestBody Candidatura candidatura){
        candidatura = candidaturaService.update(id,candidatura,curriculo_id);
        if(candidatura != null) {
            return ResponseEntity.ok(candidatura);
        }else{  
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return candidaturaService.delete(id);
    }

}