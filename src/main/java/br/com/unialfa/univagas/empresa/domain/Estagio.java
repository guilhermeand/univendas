package br.com.unialfa.univagas.empresa.domain;


import br.com.unialfa.univagas.candidatura.domain.Candidatura;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
//import java.util.list;

//anotations lombok
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode

//anotations jpa
@Entity
@Table(name = "estagio")
public class Estagio extends Vaga implements Serializable {

     private String data_inicio;
     private String data_final;
     private String ies;

     public Estagio() {
     }

     public Estagio(long id, Empresa empresa, String descricao, String salario, String carga_horaria, String beneficios, Date termina_em, Boolean isFinalizada, Boolean isSupervisionada, Boolean isAprovada, Boolean isVisivel, List<Candidatura> candidaturas, String data_inicio, String data_final, String ies) {
          super(id, empresa, descricao, salario, carga_horaria, beneficios, termina_em, isFinalizada, isSupervisionada, isAprovada, isVisivel, candidaturas);
          this.data_inicio = data_inicio;
          this.data_final = data_final;
          this.ies = ies;
     }

     public String getData_inicio() {
          return data_inicio;
     }

     public void setData_inicio(String data_inicio) {
          this.data_inicio = data_inicio;
     }

     public String getData_final() {
          return data_final;
     }

     public void setData_final(String data_final) {
          this.data_final = data_final;
     }

     public String getIes() {
          return ies;
     }

     public void setIes(String ies) {
          this.ies = ies;
     }
}
