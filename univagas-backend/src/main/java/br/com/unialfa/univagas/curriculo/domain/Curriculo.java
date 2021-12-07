package br.com.unialfa.univagas.curriculo.domain;


import br.com.unialfa.univagas.endereco.domain.Endereco;
import lombok.*;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import javax.persistence.*;
import javax.persistence.OneToOne;
import java.io.Serializable;



//anotations lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//anotation jpa
@Entity

public class Curriculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String cpf;
    private String datanascimento;
    private String telefone;
    private String sobre;
    private String experiencia;
    private Boolean status;
    private Boolean estudante;
    private int quantidadevagas;


    // CONSTRUCTOR public Curriculo() {
    //}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return datanascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.datanascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSobre() { return sobre; }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = true;
    }

    public Boolean getEstudante() {
        return estudante;
    }

    public void setEstudante(Boolean estudante) {
        this.estudante = true;
    }

    public int getQuantidadeVagas() {
        return quantidadevagas;
    }

    public void setQuantidadeVagas(int quantidadevagas) {
        this.quantidadevagas = quantidadevagas;
    }

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Endereco endereco;

}
