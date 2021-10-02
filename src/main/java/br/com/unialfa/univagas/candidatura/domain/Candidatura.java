package br.com.unialfa.univagas.candidatura.domain;

import br.com.unialfa.univagas.candidato.domain.Curriculo;
import br.com.unialfa.univagas.empresa.domain.Vaga;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidatura")
public class Candidatura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Vaga vaga;
    @OneToOne
    private Curriculo curriculo;
    private LocalDateTime aplicado_as;
    private String feedback;
    private Boolean aprovado;

    public Candidatura() {
    }

    public Candidatura(long id, Vaga vaga, Curriculo curriculo, LocalDateTime aplicado_as, String feedback, Boolean aprovado) {
        this.id = id;
        this.vaga = vaga;
        this.curriculo = curriculo;
        this.aplicado_as = aplicado_as;
        this.feedback = feedback;
        this.aprovado = aprovado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
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
