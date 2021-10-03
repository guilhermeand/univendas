package br.com.unialfa.univagas.endereco.service;

import br.com.unialfa.univagas.candidato.domain.Curriculo;
import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.endereco.repository.EnderecoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Optional<Endereco> findOneById(Long id){
        Endereco endereco = enderecoRepository.findById(id).get();
        return Optional.of(endereco);
    }

    public Iterable<Endereco> findAll(){
        Iterable<Endereco> usuarios = enderecoRepository.findAll();
        return usuarios;
    }

    public Endereco save(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public Endereco update(Long id,Endereco endereco){
        Optional<Endereco> enderecoExists = enderecoRepository.findById(id);
        if(enderecoExists.isEmpty()) return null;
        Endereco enderecoUpdate = enderecoExists.get();
        if(endereco.getBairro() != null) enderecoUpdate.setBairro(endereco.getBairro());
        if(endereco.getCep() != null) enderecoUpdate.setCep(endereco.getCep());
        if(endereco.getCidade() != null) enderecoUpdate.setCidade(endereco.getCidade());
        if(endereco.getComplemento() != null) enderecoUpdate.setComplemento(endereco.getComplemento());
        if(endereco.getEstado() != null) enderecoUpdate.setEstado(endereco.getEstado());
        if(endereco.getLogradouro() != null) enderecoUpdate.setLogradouro(endereco.getLogradouro());
        if(endereco.getPais() != null) enderecoUpdate.setPais(endereco.getPais());

        return enderecoRepository.save(enderecoUpdate);
    }

    public ResponseEntity<?> delete(Long id){
        try {
            enderecoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.status(500);
        }
    }

}
