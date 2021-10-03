package br.com.unialfa.univagas.candidato.domain;

import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Candidato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @OneToOne
    private Usuario usuario;
    @NotNull
    @OneToOne
    private Endereco endereco;
    private String cpf;
    private String nome;
    private String dataNascimento;


    public Candidato() {
    }

    public Candidato(long id, Usuario usuario, Endereco endereco, String cpf, String nome, String dataNascimento) {
        this.id = id;
        this.usuario = usuario;
        this.endereco = endereco;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}