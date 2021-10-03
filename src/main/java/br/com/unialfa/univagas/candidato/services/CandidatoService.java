package br.com.unialfa.univagas.candidato.services;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.repository.CandidatoRepository;
import br.com.unialfa.univagas.candidatura.domain.Candidatura;
import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.endereco.service.EnderecoService;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import br.com.unialfa.univagas.usuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
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
            Optional<Endereco> endereco = enderecoService.findOneById(endereco_id);
            candidatoUpdate.setEndereco(endereco.get());
        };
        if(candidato.getCpf() != null && validaCPF(candidato.getCpf())) candidatoUpdate.setCpf(candidato.getCpf());
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

    public boolean validaCPF(String CPF){
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
    public boolean validarMenorOuIgualTresCandidaturas(Candidato candidato){
        try{
            if (candidato.getCandidaturas().size()  < 3){
                return true;
            }
            return false;
        } catch (Exception erro) {
            return false;
        }
    }
//    public boolean validaNumeroDeCandidaturasESalvaCandidatura(Candidato candidato, Candidatura candidatura){
//        try{
//            if (candidato.getCandidaturas().size()  <= 3){
//                List<Candidatura> candidaturaList = candidato.getCandidaturas();
//                candidaturaList.add(candidatura);
//                candidato.setCandidaturas(candidaturaList);
//                candidatoRepository.save(candidato);
//                return true;
//            }
//            return false;
//        } catch (Exception erro) {
//            return false;
//        }
//    }
}
