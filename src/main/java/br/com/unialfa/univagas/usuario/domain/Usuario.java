package br.com.unialfa.univagas.usuario.domain;

import br.com.unialfa.univagas.curriculo.domain.Curriculo;
import javax.persistence.*;
import javax.persistence.OneToOne;
import lombok.*;
import java.io.Serializable;
//import java.util.list;

//anotations lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//anotations jpa
@Entity



public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  email;
    private String senha;

    @OneToOne
    private Curriculo curriculo;


}
