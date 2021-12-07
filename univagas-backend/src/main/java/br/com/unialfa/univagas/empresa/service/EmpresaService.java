package br.com.unialfa.univagas.empresa.service;

import br.com.unialfa.univagas.empresa.domain.Empresa;
import br.com.unialfa.univagas.empresa.repository.EmpresaRepository;
import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.endereco.service.EnderecoService;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import br.com.unialfa.univagas.usuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EnderecoService enderecoService;
    private final UsuarioService usuarioService;

    public EmpresaService(EmpresaRepository empresaRepository, EnderecoService enderecoService, UsuarioService usuarioService) {
        this.empresaRepository = empresaRepository;
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
    }

    public Optional<Empresa> findOneById(Long id){
        Empresa empresa = empresaRepository.findById(id).get();
        return Optional.of(empresa);
    }

    public Iterable<Empresa> findAll(){
        Iterable<Empresa> empresas = empresaRepository.findAll();
        return empresas;
    }

    public Empresa save(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public Empresa update(Long id,Empresa empresa){
        Optional<Empresa> candidatoCheck = empresaRepository.findById(id);
        if(candidatoCheck.isEmpty()) return null;
        Empresa candidatoUpdate = candidatoCheck.get();
        if(empresa.getCnpj() != null) candidatoUpdate.setCnpj(empresa.getCnpj());
        if(empresa.getEndereco() != null) candidatoUpdate.setEndereco(empresa.getEndereco());
        if(empresa.getNomefantasia() != null) candidatoUpdate.setNomefantasia(empresa.getNomefantasia());
        if(empresa.getRazaosocial() != null) candidatoUpdate.setRazaosocial(empresa.getRazaosocial());
        if(empresa.getUsuario() != null) candidatoUpdate.setUsuario(empresa.getUsuario());
        return empresaRepository.save(candidatoUpdate);
    }

    public ResponseEntity<?> delete(Long id){
        try {
            empresaRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

}
