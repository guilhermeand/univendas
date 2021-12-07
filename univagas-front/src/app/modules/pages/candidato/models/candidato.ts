import {User} from "../../user/models/user";
import {Endereco} from "../../../../interfaces/endereco";

export interface Candidato {
  id: number;
  usuario: User;
  endereco: Endereco;
  cpf: string;
  nome: string;
  dataNascimento: string;
}
