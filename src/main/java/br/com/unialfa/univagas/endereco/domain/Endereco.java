package br.com.unialfa.univagas.endereco.domain;

import br.com.unialfa.univagas.curriculo.domain.Curriculo;
import lombok.*;
import javax.persistence.*;
import javax.persistence.OneToOne;
import java.io.Serializable;

//anotations lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//anotations jpa
@Entity



public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

    @OneToOne
    private Curriculo curriculo;
}
