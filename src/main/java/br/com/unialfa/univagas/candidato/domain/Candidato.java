package br.com.unialfa.univagas.candidato.domain;

import br.com.unialfa.univagas.candidatura.domain.Candidatura;
import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Candidato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @OneToOne
    private Usuario usuario;
    @NotNull
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private String cpf;
    private String nome;
    private String dataNascimento;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "candidato")
    private List<Curriculo> curriculos;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "candidato")
    private List<Candidatura> candidaturas;

    public Candidato() {
    }

    public Candidato(long id, Usuario usuario, Endereco endereco, String cpf, String nome, String dataNascimento, List<Curriculo> curriculos, List<Candidatura> candidaturas) {
        this.id = id;
        this.usuario = usuario;
        this.endereco = endereco;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.curriculos = curriculos;
        this.candidaturas = candidaturas;
    }

    public List<Curriculo> getCurriculos() {
        return curriculos;
    }

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
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