package br.com.unialfa.univagas.vagaestagio.domain;


import javax.persistence.*;
import br.com.unialfa.univagas.curriculo.domain.Curriculo;
import br.com.unialfa.univagas.empresa.domain.Empresa;
import lombok.*;
import java.io.Serializable;
import javax.persistence.OneToOne;
//import java.util.list;

//anotations lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//anotations jpa
@Entity



public class VagaEstagio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idempresa;
    private  long idcurriculo;
    private String descricao;
    private String salario;
    private String cargaHoraria;
    private String beneficios;
    private String dataInicio;
    private String dataFim;
    private Boolean isFinalizado;

    @OneToOne
    private Empresa empresa;

    @OneToOne
    private Curriculo curriculo;

}
