package br.com.unialfa.univagas;

import br.com.unialfa.univagas.endereco.domain.Endereco;
import br.com.unialfa.univagas.endereco.repository.EnderecoRepository;
import br.com.unialfa.univagas.curriculo.domain.Curriculo;
import br.com.unialfa.univagas.curriculo.repository.CurriculoRepository;
import br.com.unialfa.univagas.usuario.domain.Usuario;
import br.com.unialfa.univagas.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class UnivagasApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CurriculoRepository curriculoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;


	public static void main(String[] args) {
		SpringApplication.run(UnivagasApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		Usuario usuario = new Usuario();
		usuario.setEmail("gabriel@gmail.com");
		usuario.setSenha("Murilo");


		Curriculo curriculo = new Curriculo();
		curriculo.setNome("Gabriel");
		curriculo.setCpf("123.456.789-11");
		curriculo.setDatanascimento("12/30/1992");
		curriculo.setTelefone("623456543");
		curriculo.setSobre("Oi, eu sou o Goku!");
		curriculo.setExperiencia("Vasp");
		curriculo.setStatus(true);
		curriculo.setEstudante(false);
		curriculo.setQuantidadevagas(2);


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
		return null;
	}
}