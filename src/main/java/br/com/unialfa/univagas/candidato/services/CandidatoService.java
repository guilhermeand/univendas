package br.com.unialfa.univagas.candidato.services;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.repository.CandidatoRepository;
import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.endereco.service.EnderecoService;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import br.com.unialfa.univagas.usuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final EnderecoService enderecoService;
    private final UsuarioService usuarioService;

    public CandidatoService(CandidatoRepository candidatoRepository, EnderecoService enderecoService, UsuarioService usuarioService) {
        this.candidatoRepository = candidatoRepository;
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
    }

    public Optional<Candidato> findOneById(Long id){
        Candidato candidato = candidatoRepository.findById(id).get();
        return Optional.of(candidato);
    }

    public Iterable<Candidato> findAll(){
        Iterable<Candidato> candidatos = candidatoRepository.findAll();
        return candidatos;
    }

    public Candidato save(Long endereco_id, Long usuario_id, Candidato candidato){
        Optional<Endereco> endereco = enderecoService.findOneById(endereco_id);
        if(endereco.isEmpty()) return null;
        Optional<Usuario> usuario = usuarioService.findOneById(usuario_id);
        if(usuario.isEmpty()) return null;
        candidato.setUsuario(usuario.get());
        candidato.setEndereco(endereco.get());
        return candidatoRepository.save(candidato);
    }

    public Candidato update(Long id,Candidato candidato,Long endereco_id){
        Optional<Candidato> candidatoCheck = candidatoRepository.findById(id);
        if(candidatoCheck.isEmpty()) return null;
        Candidato candidatoUpdate = candidatoCheck.get();
        if(endereco_id != null) {
            candidatoUpdate.setEndereco(enderecoService.findOneById(endereco_id).get());
        };
        if(candidato.getCpf() != null) candidatoUpdate.setCpf(candidato.getCpf());
        if(candidato.getNome() != null) candidatoUpdate.setNome(candidato.getNome());
        if(candidato.getDataNascimento() != null) candidatoUpdate.setDataNascimento(candidato.getDataNascimento());
        return candidatoRepository.save(candidatoUpdate);
    }

    public ResponseEntity<?> delete(Long id){
        try {
            candidatoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

}
