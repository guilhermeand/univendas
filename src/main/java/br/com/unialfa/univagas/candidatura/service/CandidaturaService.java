package br.com.unialfa.univagas.candidatura.service;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.domain.Curriculo;
import br.com.unialfa.univagas.candidato.services.CandidatoService;
import br.com.unialfa.univagas.candidato.services.CurriculoService;
import br.com.unialfa.univagas.candidatura.domain.Candidatura;
import br.com.unialfa.univagas.candidatura.repository.CandidaturaRepository;
import br.com.unialfa.univagas.empresa.domain.Emprego;
import br.com.unialfa.univagas.empresa.domain.Estagio;
import br.com.unialfa.univagas.empresa.domain.Vaga;
import br.com.unialfa.univagas.empresa.service.EmpregoService;
import br.com.unialfa.univagas.empresa.service.EstagioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.*;
@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final CurriculoService curriculoService;
    private final CandidatoService candidatoService;
    private final EstagioService estagioService;
    private final EmpregoService empregoService;

    public CandidaturaService(CandidaturaRepository candidaturaRepository, CurriculoService curriculoService, CandidatoService candidatoService, EstagioService estagioService, EmpregoService empregoService) {
        this.candidaturaRepository = candidaturaRepository;
        this.curriculoService = curriculoService;
        this.candidatoService = candidatoService;
        this.estagioService = estagioService;
        this.empregoService = empregoService;
    }

    public Optional<Candidatura> findOneById(Long id){
        Candidatura candidatura = candidaturaRepository.findById(id).get();
        return Optional.of(candidatura);
    }

    public Iterable<Candidatura> findAll(){
        Iterable<Candidatura> candidatos = candidaturaRepository.findAll();
        return candidatos;
    }

    public Candidatura save(Vaga vaga, String vaga_tipo,Candidato candidato, Curriculo curriculo){
        Candidatura candidatura = new Candidatura();
        candidatura.setCandidato(candidato);
        candidatura.setCurriculo(curriculo);
        candidatura.setAplicado_as(LocalDateTime.now());
        candidatura.setVaga(vaga);
        candidatura.setVaga_tipo(vaga_tipo);
        return candidaturaRepository.save(candidatura);
    }
//    public Candidatura saveEstagio(Long estagio_id, Long curriculo_id){
//        Candidatura candidatura = new Candidatura();
//        Optional<Estagio> estagio = estagioService.findOneById(estagio_id);
//        if(estagio.isEmpty()) return null;
//        Optional<Curriculo> curriculo = curriculoService.findOneById(curriculo_id);
//        if(curriculo.isEmpty()) return null;
//        candidatura.setCurriculo(curriculo.get());
//        candidatura.setVaga(estagio.get());
//        candidatura.setAplicado_as(LocalDateTime.now());
//        return candidaturaRepository.save(candidatura);
//    }
//    public Candidatura saveEmprego(Long emprego_id, Long curriculo_id, Candidatura candidatura){
//
//        Optional<Emprego> emprego = empregoService.findOneById(emprego_id);
//        if(emprego.isEmpty()) return null;
//        Optional<Curriculo> curriculo = curriculoService.findOneById(curriculo_id);
//        if(curriculo.isEmpty()) return null;
//        candidatura.setCurriculo(curriculo.get());
//        candidatura.setVaga(emprego.get());
//        return candidaturaRepository.save(candidatura);
//    }

    public Candidatura update(Long id,Candidatura candidatura,Long curriculo_id){
        Optional<Candidatura> candidaturaCheck = candidaturaRepository.findById(id);
        if(candidaturaCheck.isEmpty()) return null;
        Candidatura candidaturaUpdate = candidaturaCheck.get();
        if(curriculo_id != null) {
            Optional<Curriculo> curriculo = curriculoService.findOneById(curriculo_id);
            if (curriculo.isEmpty()) return null;
            candidaturaUpdate.setCurriculo(curriculo.get());
        };
        if(candidatura.getAprovado() != null) candidaturaUpdate.setAprovado(candidatura.getAprovado());
        if(candidatura.getAplicado_as() != null) candidaturaUpdate.setAplicado_as(candidatura.getAplicado_as());
        if(candidatura.getFeedback() != null) candidaturaUpdate.setFeedback(candidatura.getFeedback());
        return candidaturaRepository.save(candidaturaUpdate);
    }

    public ResponseEntity<?> delete(Long id){
        try {
            candidaturaRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

}
