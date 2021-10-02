package br.com.unialfa.univagas.empresa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vaga")
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
    private Boolean isFinalizada;
    private Date termina_em;

    public Vaga() {
    }

    public Vaga(long id, Empresa empresa, String descricao, String salario, String carga_horaria, String beneficios, Boolean isFinalizada, Date termina_em) {
        this.id = id;
        this.empresa = empresa;
        this.descricao = descricao;
        this.salario = salario;
        this.carga_horaria = carga_horaria;
        this.beneficios = beneficios;
        this.isFinalizada = isFinalizada;
        this.termina_em = termina_em;
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
