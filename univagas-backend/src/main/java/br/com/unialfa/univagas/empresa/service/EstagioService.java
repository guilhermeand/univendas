package br.com.unialfa.univagas.empresa.service;

import br.com.unialfa.univagas.empresa.domain.Emprego;
import br.com.unialfa.univagas.empresa.domain.Empresa;
import br.com.unialfa.univagas.empresa.domain.Estagio;
import br.com.unialfa.univagas.empresa.repository.EstagioRepository;
import br.com.unialfa.univagas.empresa.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstagioService {

    private final EstagioRepository estagioRepository;
    private final EmpresaService empresaService;
    
    public EstagioService(EstagioRepository estagioRepository, EmpresaService empresaService) {
        this.estagioRepository = estagioRepository;
        this.empresaService = empresaService;
    }


    public Optional<Estagio> findOneById(Long id){
        Estagio emprego = estagioRepository.findById(id).get();
        return Optional.of(emprego);
    }

    public Iterable<Estagio> findAll(){
        Iterable<Estagio> estagios = estagioRepository.findAll();
        return estagios;
    }

    public Estagio save(Long empresa_id, Estagio emprego){
        Optional<Empresa> estagio = empresaService.findOneById(empresa_id);
        if(estagio.isEmpty()) return null;
        emprego.setEmpresa(estagio.get());
        return estagioRepository.save(emprego);
    }

    public Estagio update(Long id, Estagio emprego){
        Optional<Estagio> empregoCheck = estagioRepository.findById(id);
        if(empregoCheck.isEmpty()) return null;
        Estagio estagioUpdate = empregoCheck.get();
        if(emprego.getIes() != null) estagioUpdate.setIes(emprego.getIes());
        if(emprego.getData_final() != null) estagioUpdate.setData_final(emprego.getData_final());
        if(emprego.getData_inicio() != null) estagioUpdate.setData_final(emprego.getData_inicio());
        if(emprego.getBeneficios() != null) estagioUpdate.setBeneficios(emprego.getBeneficios());
        if(emprego.getDescricao() != null) estagioUpdate.setDescricao(emprego.getDescricao());
        if(emprego.getCarga_horaria() != null) estagioUpdate.setCarga_horaria(emprego.getCarga_horaria());
        if(emprego.getFinalizada() != null) estagioUpdate.setFinalizada(emprego.getFinalizada());
        if(emprego.getSalario() != null) estagioUpdate.setSalario(emprego.getSalario());
        if(emprego.getTermina_em() != null) estagioUpdate.setTermina_em(emprego.getTermina_em());
        if(emprego.getSupervisionada() != null) estagioUpdate.setSupervisionada(emprego.getSupervisionada());
        if(emprego.getAprovada() != null) estagioUpdate.setAprovada(emprego.getAprovada());
        if(emprego.getVisivel() != null) estagioUpdate.setVisivel(emprego.getVisivel());
        return estagioRepository.save(estagioUpdate);
    }

    public ResponseEntity<?> delete(Long id){
        try {
            estagioRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }
    public Estagio save(Estagio estagio){
        return estagioRepository.save(estagio);
    }
}