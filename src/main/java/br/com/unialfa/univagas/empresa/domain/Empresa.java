package br.com.unialfa.univagas.empresa.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.OneToOne;
import br.com.unialfa.univagas.endereco.domain.Endereco;
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

public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String razaosocial;
    private String nomefantasia;
    private String cnpj;


    // CONSTRUCTOR public Empresa() {
    //}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaosocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaosocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomefantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomefantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @OneToOne
    private Endereco endereco;
}
