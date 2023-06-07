package br.com.fiap.global.core.config;

import br.com.fiap.global.core.models.Arrecadacao;
import br.com.fiap.global.core.models.Instituicao;
import br.com.fiap.global.core.models.Usuario;
import br.com.fiap.global.core.repository.ArrecadacaoRepository;
import br.com.fiap.global.core.repository.InstituicaoRepository;
import br.com.fiap.global.core.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    InstituicaoRepository instituicaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ArrecadacaoRepository arrecadacaoRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Instituicao> i = generateInstituicoes();
        instituicaoRepository.saveAll(i);

        List<Usuario> u = generateUsuarios();
        usuarioRepository.saveAll(u);

        List<Arrecadacao> a = generateArrecadacoes(u, i);
        arrecadacaoRepository.saveAll(a);
    }

    private static List<Arrecadacao> generateArrecadacoes(List<Usuario> u, List<Instituicao> i){
        List<Arrecadacao> arrecadacoes = new ArrayList<>();

        arrecadacoes.addAll(List.of(
                Arrecadacao.builder()
                        .valor(15.0f)
                        .dtArrecadacao(LocalDate.of(2023, 4, 1))
                        .tipoPagamento("Boleto")
                        .usuario(u.get(0))
                        .instituicao(i.get(0))
                        .build(),
                Arrecadacao.builder()
                        .valor(7.85f)
                        .dtArrecadacao(LocalDate.of(2022, 12, 17))
                        .tipoPagamento("Cartão de Crédito")
                        .usuario(u.get(1))
                        .instituicao(i.get(1))
                        .build(),
                Arrecadacao.builder()
                        .valor(1000f)
                        .dtArrecadacao(LocalDate.of(2023, 2, 12))
                        .tipoPagamento("Boleto")
                        .usuario(u.get(2))
                        .instituicao(i.get(2))
                        .build()
        ));
        return arrecadacoes;
    }

    private static List<Usuario> generateUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.addAll(List.of(
                Usuario.builder()
                        .nome("Adilson")
                        .email("exemplo@exemplo.com")
                        .senha("12345678")
                        .telefone("12344321")
                        .build(),
                Usuario.builder()
                        .nome("Fernando")
                        .email("softmax@softmax.com")
                        .senha("87654321")
                        .telefone("87655678")
                        .build(),
                Usuario.builder()
                        .nome("Cléber")
                        .email("webcam@webcam.com")
                        .senha("webcam123")
                        .telefone("11112222")
                        .build()
        ));
        return usuarios;
    }
    private static List<Instituicao> generateInstituicoes(){
        List<Instituicao> instituicoes = new ArrayList<>();

        instituicoes.addAll(List.of(
                Instituicao.builder()
                        .nome("Amparaí")
                        .descricao("Amparaí: Combate a fome e vulnerabilidade, suprindo necessidades básicas em Porto Alegre durante a pandemia, promovendo segurança alimentar e acesso a itens essenciais.")
                        .endereco("Porto Alegre - RS")
                        .contato("nao sei")
                        .build(),
                Instituicao.builder()
                        .nome("Amigos do Bem")
                        .descricao("Amigos do Bem: Promovendo inclusão social no Nordeste com projetos educacionais, trabalho, água, saúde e moradia. Atuando desde 1993 na redução da pobreza e melhoria de vida.")
                        .endereco("Caruaru - PE")
                        .contato("nao sei")
                        .build(),
                Instituicao.builder()
                        .nome("ONG Banco de Alimentos")
                        .descricao("A ONG Banco de Alimentos trabalha desde 1998 para ajudar pessoas em situação de insegurança alimentar, combatendo o desperdício de alimentos. Sua doação é fundamental para apoiar nosso trabalho nessa causa tão importante.")
                        .endereco("São Paulo - SP")
                        .contato("nao sei")
                        .build(),
                Instituicao.builder()
                        .nome("Brasil Sem Fome")
                        .descricao("A Ação da Cidadania é o principal movimento social do Brasil, nascido no auge do Movimento pela Ética na Política. Sua atuação é focada em combater a fome e a miséria, por meio de uma extensa rede de mobilização com comitês locais da sociedade civil.")
                        .endereco("Rio de Janeiro - RJ")
                        .contato("nao sei")
                        .build(),
                Instituicao.builder()
                        .nome("Cáritas")
                        .descricao("Desde a sua fundação, a Cáritas tem a prática de ouvir respeitosamente o sofrimento dos empobrecidos e dos que estão em situação de vulnerabilidade e favorecer ferramentas para transformar suas vidas.")
                        .endereco("Manaus - AM")
                        .contato("nao sei")
                        .build(),
                Instituicao.builder()
                        .nome("Iprede")
                        .descricao("Instituto da Primeira Infância foi fundado em 1986 em Fortaleza (CE) por um grupo de profissionais sensibilizados pela condição das crianças em desnutrição grave e vivendo em situação de vulnerabilidade social e pobreza.")
                        .endereco("Fortaleza - CE")
                        .contato("nao sei")
                        .build()
        ));
        return instituicoes;
    }
}
