package br.com.unialfa.univagas.candidatura.domain;

import br.com.unialfa.univagas.candidato.domain.Candidato;
import br.com.unialfa.univagas.candidato.domain.CurriculoCandidato;
import br.com.unialfa.univagas.empresa.domain.Vaga;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidatura")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Candidatura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id",nullable = false)
    @Fetch(FetchMode.JOIN)
    @JsonIdentityReference(alwaysAsId=true)
    private Vaga vaga;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculo_id",nullable = false)
    @Fetch(FetchMode.JOIN)
    @JsonIdentityReference(alwaysAsId=true)
    private CurriculoCandidato curriculoCandidato;
    private String vaga_tipo;

    private LocalDateTime aplicado_as;
    private String feedback;
    private Boolean aprovado;
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    public Candidatura() {
    }
    public Candidatura(long id, Vaga vaga, CurriculoCandidato curriculoCandidato, String vaga_tipo, LocalDateTime aplicado_as, String feedback, Boolean aprovado, Candidato candidato) {
        this.id = id;
        this.vaga = vaga;
        this.curriculoCandidato = curriculoCandidato;
        this.vaga_tipo = vaga_tipo;
        this.aplicado_as = aplicado_as;
        this.feedback = feedback;
        this.aprovado = aprovado;
        this.candidato = candidato;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVaga_tipo() {
        return vaga_tipo;
    }

    public void setVaga_tipo(String vaga_tipo) {
        this.vaga_tipo = vaga_tipo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public CurriculoCandidato getCurriculo() {
        return curriculoCandidato;
    }

    public void setCurriculo(CurriculoCandidato curriculoCandidato) {
        this.curriculoCandidato = curriculoCandidato;
    }

    public LocalDateTime getAplicado_as() {
        return aplicado_as;
    }

    public void setAplicado_as(LocalDateTime aplicado_as) {
        this.aplicado_as = aplicado_as;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }
}
