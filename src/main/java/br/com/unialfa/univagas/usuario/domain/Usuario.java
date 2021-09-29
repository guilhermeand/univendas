package br.com.unialfa.univagas.usuario.domain;

import br.com.unialfa.univagas.curriculo.domain.Curriculo;
import javax.persistence.*;
import javax.persistence.OneToOne;

import br.com.unialfa.univagas.vagaemprego.domain.VagaEmprego;
import br.com.unialfa.univagas.vagaestagio.domain.VagaEstagio;
import lombok.*;
import java.io.Serializable;
//import java.util.list;

//anotations lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//anotations jpa
@Entity



public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  email;
    private String senha;

    // CONSTRUCTOR public Usuario() {
    //}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @OneToOne
    private Curriculo curriculo;
    @OneToOne
    private VagaEstagio vagaEstagio;
    @OneToOne
    private VagaEmprego vagaEmprego;


}
