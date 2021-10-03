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
}