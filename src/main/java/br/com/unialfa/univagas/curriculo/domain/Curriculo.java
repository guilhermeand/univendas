package br.com.unialfa.univagas.curriculo.domain;


import br.com.unialfa.univagas.endereco.domain.Endereco;
import lombok.*;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import javax.persistence.*;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;


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
    private long idusuario;
    private long idendereco;
    private String nome;
    private String datanascimento;
    private String telefone;
    private String sobre;
    private String experiencia;
    private Boolean status;
    private Boolean estudante;
    private int quantidadevagas;


    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @OneToOne
    private Endereco endereco;

}

// VAMOS PRECISAR DE AJUDA COM AS LIGAÇÕES ENTRE AS CLASSES NO BANCO