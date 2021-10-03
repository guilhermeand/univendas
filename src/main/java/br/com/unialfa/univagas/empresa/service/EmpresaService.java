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

    public Empresa save(Long endereco_id, Long usuario_id, Empresa empresa){
        Optional<Endereco> endereco = enderecoService.findOneById(endereco_id);
        if(endereco.isEmpty()) return null;
        Optional<Usuario> usuario = usuarioService.findOneById(usuario_id);
        if(usuario.isEmpty()) return null;
        empresa.setUsuario(usuario.get());
        empresa.setEndereco(endereco.get());
        return empresaRepository.save(empresa);
    }

    public Empresa update(Long id,Empresa empresa,Long endereco_id){
        Optional<Empresa> candidatoCheck = empresaRepository.findById(id);
        if(candidatoCheck.isEmpty()) return null;
        Empresa candidatoUpdate = candidatoCheck.get();
        if(endereco_id != null) {
            candidatoUpdate.setEndereco(enderecoService.findOneById(endereco_id).get());
        };
        if(empresa.getCnpj() != null) candidatoUpdate.setCnpj(empresa.getCnpj());
        if(empresa.getEndereco() != null) candidatoUpdate.setEndereco(empresa.getEndereco());
        if(empresa.getNomefantasia() != null) candidatoUpdate.setNomefantasia(empresa.getNomefantasia());
        if(empresa.getRazaosocial() != null) candidatoUpdate.setRazaosocial(empresa.getRazaosocial());
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