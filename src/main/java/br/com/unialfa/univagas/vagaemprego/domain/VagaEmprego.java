package br.com.unialfa.univagas.vagaemprego.domain;

import br.com.unialfa.univagas.curriculo.domain.Curriculo;
import br.com.unialfa.univagas.empresa.domain.Empresa;
import javax.persistence.OneToOne;
import java.io.Serializable;
import javax.persistence.*;
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

public class VagaEmprego implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idempresa;
    private  long idcurriculo;
    private String descricao;
    private String salario;
    private String cargaHoraria;
    private String beneficios;
    private Boolean isFinalizado;

    @OneToOne
    private Empresa empresa;

    @OneToOne
    private Curriculo curriculo;
}
