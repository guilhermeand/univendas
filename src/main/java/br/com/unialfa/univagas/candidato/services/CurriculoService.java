package br.com.unialfa.univagas.candidato.services;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.domain.Curriculo;
import br.com.unialfa.univagas.candidato.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Service
public class CurriculoService {

    private final CurriculoRepository curriculoRepository;
    private final CandidatoService candidatoService;

    @Autowired
    public CurriculoService(CurriculoRepository curriculoRepository, CandidatoService candidatoService) {
        this.curriculoRepository = curriculoRepository;
        this.candidatoService = candidatoService;
    }

    public Iterable<Curriculo> findAll(){
        Iterable<Curriculo> curriculos = curriculoRepository.findAll();
        return curriculos;
    }
    public Optional<Curriculo> findOneById(Long id){
        Curriculo curriculo = curriculoRepository.findById(id).get();
        return Optional.of(curriculo);
    }

    public Curriculo save(Long candidato_id, Curriculo curriculo){
        Optional<Candidato> candidato = candidatoService.findOneById(candidato_id);
        if (candidato.isEmpty()) return null;
        curriculo.setCandidato(candidato.get());
        return curriculoRepository.save(curriculo);
    }

    public Curriculo update(Long id, Long candidato_id,Curriculo curriculo){
        Optional<Candidato> candidatoCheck = candidatoService.findOneById(candidato_id);
        if(candidatoCheck.isEmpty()) return null;
        Optional<Curriculo> curriculoCheck = curriculoRepository.findById(id);
        if(curriculoCheck.isEmpty()) return null;
        Curriculo curriculoUpdate = curriculoCheck.get();
        curriculoUpdate.setCandidato(candidatoCheck.get());
        if(curriculo.getEstudante() != null) curriculoUpdate.setEstudante(curriculo.getEstudante());
        if(curriculo.getExperiencia() != null) curriculoUpdate.setExperiencia(curriculo.getExperiencia());
        if(curriculo.getSobre() != null) curriculoUpdate.setSobre(curriculo.getSobre());
        if(curriculo.getStatus() != null) curriculoUpdate.setStatus(curriculo.getStatus());
        if(curriculo.getTelefone() != null) curriculoUpdate.setTelefone(curriculo.getTelefone());
        return curriculoRepository.save(curriculoUpdate);
    }


    public ResponseEntity<?> delete(Long id){
        try {
            curriculoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

}