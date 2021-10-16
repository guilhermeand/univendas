package br.com.unialfa.univagas.empresa.domain;

import br.com.unialfa.univagas.candidatura.domain.Candidatura;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vaga")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Vaga  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Empresa empresa;
    private String descricao;
    private String salario;
    private String carga_horaria;
    private String beneficios;
    private Date termina_em;
    @ColumnDefault("false")
    private Boolean isFinalizada;
    @ColumnDefault("false")
    private Boolean isSupervisionada;
    @ColumnDefault("false")
    private Boolean isAprovada;
    @ColumnDefault("false")
    private Boolean isVisivel;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "vaga")
    private List<Candidatura> candidaturas;

    public Vaga() {
    }

    public Vaga(long id, Empresa empresa, String descricao, String salario, String carga_horaria, String beneficios, Date termina_em, Boolean isFinalizada, Boolean isSupervisionada, Boolean isAprovada, Boolean isVisivel, List<Candidatura> candidaturas) {
        this.id = id;
        this.empresa = empresa;
        this.descricao = descricao;
        this.salario = salario;
        this.carga_horaria = carga_horaria;
        this.beneficios = beneficios;
        this.termina_em = termina_em;
        this.isFinalizada = isFinalizada;
        this.isSupervisionada = isSupervisionada;
        this.isAprovada = isAprovada;
        this.isVisivel = isVisivel;
        this.candidaturas = candidaturas;
    }

    public Boolean getSupervisionada() {
        return isSupervisionada;
    }

    public void setSupervisionada(Boolean supervisionada) {
        isSupervisionada = supervisionada;
    }

    public Boolean getAprovada() {
        return isAprovada;
    }

    public void setAprovada(Boolean aprovada) {
        isAprovada = aprovada;
    }

    public Boolean getVisivel() {
        return isVisivel;
    }

    public void setVisivel(Boolean visivel) {
        isVisivel = visivel;
    }

    public List<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(String carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public Boolean getFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        isFinalizada = finalizada;
    }

    public Date getTermina_em() {
        return termina_em;
    }

    public void setTermina_em(Date termina_em) {
        this.termina_em = termina_em;
    }
}
