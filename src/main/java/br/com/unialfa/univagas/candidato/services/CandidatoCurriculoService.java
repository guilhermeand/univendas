package br.com.unialfa.univagas.candidato.services;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.domain.CurriculoCandidato;
import br.com.unialfa.univagas.candidato.repository.CurriculoCandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatoCurriculoService {

    private final CurriculoCandidatoRepository curriculoCandidatoRepository;
    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoCurriculoService(CurriculoCandidatoRepository curriculoCandidatoRepository, CandidatoService candidatoService) {
        this.curriculoCandidatoRepository = curriculoCandidatoRepository;
        this.candidatoService = candidatoService;
    }

    public Iterable<CurriculoCandidato> findAll(){
        Iterable<CurriculoCandidato> curriculos = curriculoCandidatoRepository.findAll();
        return curriculos;
    }
    public Optional<CurriculoCandidato> findOneById(Long id){
        CurriculoCandidato curriculoCandidato = curriculoCandidatoRepository.findById(id).get();
        return Optional.of(curriculoCandidato);
    }

    public CurriculoCandidato save(Long candidato_id, CurriculoCandidato curriculoCandidato){
        Optional<Candidato> candidato = candidatoService.findOneById(candidato_id);
        if (candidato.isEmpty()) return null;
        curriculoCandidato.setCandidato(candidato.get());
        return curriculoCandidatoRepository.save(curriculoCandidato);
    }

    public CurriculoCandidato update(Long id, Long candidato_id, CurriculoCandidato curriculoCandidato){
        Optional<Candidato> candidatoCheck = candidatoService.findOneById(candidato_id);
        if(candidatoCheck.isEmpty()) return null;
        Optional<CurriculoCandidato> curriculoCheck = curriculoCandidatoRepository.findById(id);
        if(curriculoCheck.isEmpty()) return null;
        CurriculoCandidato curriculoCandidatoUpdate = curriculoCheck.get();
        curriculoCandidatoUpdate.setCandidato(candidatoCheck.get());
        if(curriculoCandidato.getEstudante() != null) curriculoCandidatoUpdate.setEstudante(curriculoCandidato.getEstudante());
        if(curriculoCandidato.getExperiencia() != null) curriculoCandidatoUpdate.setExperiencia(curriculoCandidato.getExperiencia());
        if(curriculoCandidato.getSobre() != null) curriculoCandidatoUpdate.setSobre(curriculoCandidato.getSobre());
        if(curriculoCandidato.getStatus() != null) curriculoCandidatoUpdate.setStatus(curriculoCandidato.getStatus());
        if(curriculoCandidato.getTelefone() != null) curriculoCandidatoUpdate.setTelefone(curriculoCandidato.getTelefone());
        return curriculoCandidatoRepository.save(curriculoCandidatoUpdate);
    }


    public ResponseEntity<?> delete(Long id){
        try {
            curriculoCandidatoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

}
