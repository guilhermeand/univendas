package br.com.unialfa.univagas.empresa.domain;

import br.com.unialfa.univagas.candidatura.domain.Candidatura;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
//import java.util.list;

//anotations lombok
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode

//anotations jpa
@Entity
@Table(name = "emprego")
public class Emprego  extends Vaga implements Serializable {


    private String numero_documento;
    private Boolean clt;
    private Boolean pj;


    public Emprego() {
    }

    public Emprego(long id, Empresa empresa, String descricao, String salario, String carga_horaria, String beneficios, Date termina_em, Boolean isFinalizada, Boolean isSupervisionada, Boolean isAprovada, Boolean isVisivel, List<Candidatura> candidaturas, String numero_documento, Boolean clt, Boolean pj) {
        super(id, empresa, descricao, salario, carga_horaria, beneficios, termina_em, isFinalizada, isSupervisionada, isAprovada, isVisivel, candidaturas);
        this.numero_documento = numero_documento;
        this.clt = clt;
        this.pj = pj;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public Boolean getClt() {
        return clt;
    }

    public void setClt(Boolean clt) {
        this.clt = clt;
    }

    public Boolean getPj() {
        return pj;
    }

    public void setPj(Boolean pj) {
        this.pj = pj;
    }
}
