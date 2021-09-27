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
    private long idendereco;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;

    @OneToOne
    private Endereco endereco;
}
