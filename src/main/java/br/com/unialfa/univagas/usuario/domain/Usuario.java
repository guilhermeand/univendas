package br.com.unialfa.univagas.usuario.domain;

import br.com.unialfa.univagas.candidato.domain.Candidato;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
//import java.util.list;

//anotations lombok
//@Getter
//@Setter
//@EqualsAndHashCode
//anotations jpa
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JoinColumn(unique = true, nullable = false)
    private String email;
    @JoinColumn(nullable = false)
    private String senha;
    @JoinColumn(nullable = false)
    private String username;
    @OneToOne(mappedBy = "usuario", cascade = {CascadeType.REMOVE})
    private Candidato candidato;

    public Usuario() {
    }

    public Usuario(long id, String email, String senha, String username) {
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.username = username;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
