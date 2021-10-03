package br.com.unialfa.univagas.candidato.domain;


import br.com.unialfa.univagas.candidatura.domain.Candidatura;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


//anotations lombok
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode

//anotation jpa
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Curriculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="candidato_id",nullable = false)
    private Candidato candidato;
    private String telefone;
    private String sobre;
    private String experiencia;
    private Boolean status;
    private Boolean estudante;


    public Curriculo() {
    }

    public Curriculo(long id, Candidato candidato, String telefone, String sobre, String experiencia, Boolean status, Boolean estudante) {
        this.id = id;
        this.candidato = candidato;
        this.telefone = telefone;
        this.sobre = sobre;
        this.experiencia = experiencia;
        this.status = status;
        this.estudante = estudante;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getEstudante() {
        return estudante;
    }

    public void setEstudante(Boolean estudante) {
        this.estudante = estudante;
    }
}
