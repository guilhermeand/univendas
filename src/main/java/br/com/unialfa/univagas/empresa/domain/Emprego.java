package br.com.unialfa.univagas.empresa.domain;

import br.com.unialfa.univagas.candidato.domain.Curriculo;
import br.com.unialfa.univagas.empresa.domain.Empresa;
import javax.persistence.OneToOne;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
//import java.util.list;

//anotations lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//anotations jpa
@Entity

public class Emprego  extends Vaga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String descricao;
    private String salario;
    private String cargahoraria;
    private String beneficios;
    private Boolean isFinalizado;

    // CONSTRUCTOR public Usuario() {
    //}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCargaHoraria() {
        return cargahoraria;
    }

    public void setCargaHoraria(String cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public Boolean getIsFinalizado() {
        return isFinalizado;
    }

    public void setIsFinalizado(boolean isFinalizado) {
        this.isFinalizado = true;
    }

    @OneToOne
    private Empresa empresa;

    @OneToOne
    private Curriculo curriculo;
}
