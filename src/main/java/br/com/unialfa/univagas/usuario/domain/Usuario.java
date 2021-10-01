package br.com.unialfa.univagas.usuario.domain;

import javax.persistence.*;

import lombok.*;
import java.io.Serializable;
//import java.util.list;

//anotations lombok
@Getter
@Setter
@EqualsAndHashCode
//anotations jpa
@Entity



public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  email;
    private String senha;

    public Usuario() {
    }
    public Usuario(long id, String email, String senha){
        this.email = email;
        this.senha = senha;
        this.id = id;
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

}
