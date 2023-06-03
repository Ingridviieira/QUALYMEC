package br.com.fiap.QualyaMec.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.QualyaMec.models.AlimentoDoado;
import br.com.fiap.QualyaMec.models.Doador;
import br.com.fiap.QualyaMec.models.Instituto;
import br.com.fiap.QualyaMec.models.SolicitacaoDoacao;
import br.com.fiap.QualyaMec.repository.AlimentoDoadoRepository;
import br.com.fiap.QualyaMec.repository.DoadorRepository;
import br.com.fiap.QualyaMec.repository.InstitutoRepository;
import br.com.fiap.QualyaMec.repository.SolicitacaoDoacaoRepository;


public class DatabaseSeeder implements CommandLineRunner{
    
    @Autowired
    DoadorRepository doadorRepository;

    @Autowired
    AlimentoDoadoRepository alimentoDoadoRepository;

    @Autowired
    InstitutoRepository institutoRepository;

    @Autowired
    SolicitacaoDoacaoRepository solicitacaoDoacaoRepository;

    @Override
    public void run(String... args) throws Exception {
        
        AlimentoDoado ad1 = new AlimentoDoado(1L, "Arroz", "10kl", 1L);
        AlimentoDoado ad2 = new AlimentoDoado(2L, "Feijão", "10kl", 2L);
        alimentoDoadoRepository.saveAll(List.of(ad1,ad2));

        doadorRepository.saveAll(List.of(
            Doador.builder().id(1L).nomeDoador("Marcos").endereco("R.Cortez 122").nomeDocumento("rg").nrDocumento(123123123).alimentoDoado(new AlimentoDoado(ad1.getId())).build(),
            Doador.builder().id(2L).nomeDoador("Oliver").endereco("R.Marques 303").nomeDocumento("rg").nrDocumento(456456456).alimentoDoado(new AlimentoDoado(ad2.getId())).build()
        ));

        SolicitacaoDoacao sd1 = new SolicitacaoDoacao(1L, "cesta basica","3 caixas");
        SolicitacaoDoacao sd2 = new SolicitacaoDoacao(2L, "Legumes", "10kl");
        solicitacaoDoacaoRepository.saveAll(List.of(sd1,sd2));

        institutoRepository.saveAll(List.of(
            Instituto.builder().id(1L).nrRegistro(17683322).endereco("r.das rosas").solicitacaoDoacao((sd1.getId())).build(),
            Instituto.builder().id(2L).nrRegistro(98233377).endereco("r.Oliveira").solicitacaoDoacao((sd2.getId())).build()
            ));
    }
}


