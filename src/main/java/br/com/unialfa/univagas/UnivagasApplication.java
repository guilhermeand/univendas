package br.com.unialfa.univagas;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnivagasApplication {

	//@Autowired
	//private UsuarioRepository usuarioRepository;

	//@Autowired
	//private CurriculoRepository curriculoRepository;

	//@Autowired
	//private EnderecoRepository enderecoRepository;


	public static void main(String[] args) {
		SpringApplication.run(UnivagasApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {


		/**
		 Usuario usuario = new Usuario();
		 usuario.setEmail("gabriel@gmail.com");
		 usuario.setSenha("Murilo");


		 Curriculo curriculo = new Curriculo();
		 curriculo.setNome("Gabriel");
		 curriculo.setCpf("123.456.789-11");
		 curriculo.setDataNascimento("12/30/1992");
		 curriculo.setTelefone("623456543");
		 curriculo.setSobre("Oi, eu sou o Goku!");
		 curriculo.setExperiencia("Vasp");
		 //curriculo.setStatus(true);
		 curriculo.setEstudante(false);
		 curriculo.setQuantidadeVagas(2);


		 Endereco endereco = new Endereco();
		 endereco.setLogradouro("Rua 1 Qd 1 Lt 1");
		 endereco.setBairro("Bairro 2");
		 endereco.setComplemento("Casa 2");
		 endereco.setCidade("Paris");
		 endereco.setEstado("Goiás");
		 endereco.setPais("Brasil");
		 endereco.setCep("11111-1111");


		 curriculoRepository.save(curriculo);
		 usuario.setCurriculo(curriculo);
		 usuarioRepository.save(usuario);
		 enderecoRepository.save(endereco);

		 */

		return null;
	}
}