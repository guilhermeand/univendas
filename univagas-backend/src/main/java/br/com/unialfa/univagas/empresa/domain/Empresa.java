package br.com.unialfa.univagas.empresa.domain;

import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.usuario.domain.Usuario;

import javax.persistence.*;
import java.io.Serializable;

//import java.util.list;

//anotations lombok
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode

//anotations jpa
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String razaosocial;
    private String nomefantasia;
    private String cnpj;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    @OneToOne
    private Usuario usuario;

    public Empresa() {
    }

    public Empresa(long id, String razaosocial, String nomefantasia, String cnpj, Endereco endereco, Usuario usuario) {
        this.id = id;
        this.razaosocial = razaosocial;
        this.nomefantasia = nomefantasia;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
