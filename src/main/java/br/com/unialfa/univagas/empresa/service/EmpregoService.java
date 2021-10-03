package br.com.unialfa.univagas.empresa.service;

import br.com.unialfa.univagas.empresa.domain.Emprego;
import br.com.unialfa.univagas.empresa.domain.Empresa;
import br.com.unialfa.univagas.empresa.repository.EmpregoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpregoService {

    private final EmpregoRepository empregoRepository;
    private final EmpresaService empresaService;

    public EmpregoService(EmpregoRepository empregoRepository, EmpresaService empresaService) {
        this.empregoRepository = empregoRepository;
        this.empresaService = empresaService;
    }


    public Optional<Emprego> findOneById(Long id){
        Emprego emprego = empregoRepository.findById(id).get();
        return Optional.of(emprego);
    }

    public Iterable<Emprego> findAll(){
        Iterable<Emprego> empresas = empregoRepository.findAll();
        return empresas;
    }

    public Emprego save(Long empresa_id, Emprego emprego){
        Optional<Empresa> empresa = empresaService.findOneById(empresa_id);
        if(empresa.isEmpty()) return null;
        emprego.setEmpresa(empresa.get());
        return empregoRepository.save(emprego);
    }

    public Emprego update(Long id,Emprego emprego){
        Optional<Emprego> empregoCheck = empregoRepository.findById(id);
        if(empregoCheck.isEmpty()) return null;
        Emprego empregoUpdate = empregoCheck.get();
        if(emprego.getClt() != null) empregoUpdate.setClt(emprego.getClt());
        if(emprego.getPj() != null) empregoUpdate.setPj(emprego.getPj());
        if(emprego.getNumero_documento() != null) empregoUpdate.setNumero_documento(emprego.getNumero_documento());
        if(emprego.getBeneficios() != null) empregoUpdate.setBeneficios(emprego.getBeneficios());
        if(emprego.getDescricao() != null) empregoUpdate.setDescricao(emprego.getDescricao());
        if(emprego.getCarga_horaria() != null) empregoUpdate.setCarga_horaria(emprego.getCarga_horaria());
        if(emprego.getFinalizada() != null) empregoUpdate.setFinalizada(emprego.getFinalizada());
        if(emprego.getSalario() != null) empregoUpdate.setSalario(emprego.getSalario());
        if(emprego.getTermina_em() != null) empregoUpdate.setTermina_em(emprego.getTermina_em());
        return empregoRepository.save(empregoUpdate);
    }

    public ResponseEntity<?> delete(Long id){
        try {
            empregoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }
}
