import {User} from "../../user/models/user";
import {Endereco} from "../../../../interfaces/endereco";

export interface Empresa {
   id: number;
   razaosocial: string;
   nomefantasia: string;
   cnpj: string;
   endereco: Endereco;
   usuario: User;
}
